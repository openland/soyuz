package com.openland.soyuz.resolver

import com.openland.soyuz.store.Record
import com.openland.soyuz.store.RecordSet
import com.openland.soyuz.store.RecordValue
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

private fun normalizeValue(
    key: String,
    selection: SelectionField,
    v: JsonElement?,
    records: MutableMap<String, MutableMap<String, RecordValue>>
): RecordValue {
    if (v != null && !v.isNull) {
        if (v is JsonObject) {
            val ref = normalizeResponse(key, v, selection.fields, records)
            return RecordValue.Reference(ref)
        } else if (v is JsonLiteral) {
            val b = v.booleanOrNull
            if (b != null) {
                return RecordValue.Boolean(b)
            } else {
                val fl = v.floatOrNull
                if (fl != null) {
                    return RecordValue.Number(fl)
                } else {
                    return RecordValue.String(v.body as String)
                }
            }
        } else if (v is JsonArray) {
            val res = mutableListOf<RecordValue>()
            for (i in 0 until v.size) {
                res.add(normalizeValue("$key.$i", selection, v[i], records))
            }
            return RecordValue.List(res)
        } else {
            throw Error("")
        }
    } else {
        return RecordValue.Null
    }
}

private fun normalizeResponse(
    root: String,
    response: JsonObject,
    selection: List<SelectionField>,
    records: MutableMap<String, MutableMap<String, RecordValue>>
): String {
    val cacheKey = response["id"]?.primitive?.contentOrNull ?: root

    if (!records.containsKey(cacheKey)) {
        records[cacheKey] = mutableMapOf()
    }
    val res = records[cacheKey]!!

    for (s in selection) {
        val v = response[s.requestKey]
        res[s.storeKey] = normalizeValue(cacheKey + "." + s.storeKey, s, v, records)
    }
    return cacheKey
}

fun normalizeResponse(response: Response, selection: Selection): RecordSet {
    val records = mutableMapOf<String, MutableMap<String, RecordValue>>()
    for (s in selection.fields) {
        val res =
            normalizeResponse(s.storeKey + "\$res", response.response[s.requestKey]!!.jsonObject, s.fields, records)
        records[s.storeKey] = mutableMapOf("$" to (RecordValue.Reference(res) as RecordValue))
    }
    return RecordSet(records.mapValues { Record(it.key, it.value) })
}