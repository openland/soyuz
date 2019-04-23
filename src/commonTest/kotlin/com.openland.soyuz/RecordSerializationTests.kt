package com.openland.soyuz

import com.openland.soyuz.store.Record
import com.openland.soyuz.store.RecordValue
import com.openland.soyuz.store.parseRecord
import com.openland.soyuz.store.serializeRecord
import kotlin.test.Test
import kotlin.test.assertEquals

class RecordSerializationTests {

    @Test
    fun testRecordSerialization() {
        val record1 = Record(
            "key1", mapOf(
                "field1" to RecordValue.String("value1"),
                "field2" to RecordValue.Boolean(true),
                "field3" to RecordValue.Boolean(false),
                "field4" to RecordValue.String("valu\"'!#23🦄e3"),
                "field5" to RecordValue.Number(0.0f),
                "field5" to RecordValue.Null,
                "field6" to RecordValue.Reference("key2")
            )
        )
        val serialized = serializeRecord(record1)
        val expected = "{field1:value1,field2:true,field3:false,field4:\"valu\\\"'!#23\uD83E\uDD84e3\"," +
                "field5:null,field6:{key:key2}}"
        assertEquals(expected, serialized)

        val res = parseRecord("key1", serialized)
        assertEquals(record1, res)
    }
}
