package com.openland.soyuz

import com.openland.soyuz.store.Record
import com.openland.soyuz.store.RecordStore
import com.openland.soyuz.store.RecordValue
import kotlin.test.Test
import kotlin.test.assertEquals

class RecordStoreTests {

    @Test
    fun testRecordMerge() {
        val store = RecordStore()
        val record1 = Record(
            "key1", mapOf(
                "field1" to RecordValue.String("value1"),
                "field2" to RecordValue.Boolean(true),
                "field3" to RecordValue.Boolean(false),
                "field4" to RecordValue.String("value3"),
                "field5" to RecordValue.Number(0.0f),
                "field5" to RecordValue.Null
            )
        )
        val record2 = Record(
            "key1", mapOf(
                "field1" to RecordValue.String("value2")
            )
        )

        val record3 = Record(
            "key1", mapOf(
                "field1" to RecordValue.Null
            )
        )

        // Initial merge
        var changes = store.merge(record1)
        assertEquals(1, changes.size)
        assertEquals(setOf("field1", "field2", "field2", "field3", "field4", "field5"), changes.getValue("key1").fields)

        // Double merge
        changes = store.merge(record1)
        assertEquals(0, changes.size)

        // Update string
        changes = store.merge(record2)
        assertEquals(1, changes.size)
        assertEquals(setOf("field1"), changes.getValue("key1").fields)

        // Update string to null
        changes = store.merge(record3)
        assertEquals(1, changes.size)
        assertEquals(setOf("field1"), changes.getValue("key1").fields)

        // Update null to string
        changes = store.merge(record1)
        assertEquals(1, changes.size)
        assertEquals(setOf("field1"), changes.getValue("key1").fields)
    }
}