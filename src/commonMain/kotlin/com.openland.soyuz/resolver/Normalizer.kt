package com.openland.soyuz.resolver

import com.openland.soyuz.store.Record
import com.openland.soyuz.store.RecordSet
import com.openland.soyuz.store.RecordValue
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

private fun normalizeResponse(
    root: String,
    response: JsonObject,
    selection: Array<SelectionField>,
    records: MutableMap<String, MutableMap<String, RecordValue>>
): String {
    val cacheKey = response["id"]?.primitive?.contentOrNull ?: root

    if (!records.containsKey(cacheKey)) {
        records[cacheKey] = mutableMapOf()
    }
    val res = records[cacheKey]!!

    for (s in selection) {
        val v = response[s.requestKey]
        if (v != null && !v.isNull) {
            if (v is JsonObject) {
                val ref = normalizeResponse(cacheKey + "." + s.storeKey, v, s.fields, records)
                res[s.storeKey] = RecordValue.Reference(ref)
            } else if (v is JsonLiteral) {
                val b = v.booleanOrNull
                if (b != null) {
                    res[s.storeKey] = RecordValue.Boolean(b)
                } else {
                    val fl = v.floatOrNull
                    if (fl != null) {
                        res[s.storeKey] = RecordValue.Number(fl)
                    } else {
                        res[s.storeKey] = RecordValue.String(v.body as String)
                    }
                }
            }
        } else {
            res[s.storeKey] = RecordValue.Null
        }
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