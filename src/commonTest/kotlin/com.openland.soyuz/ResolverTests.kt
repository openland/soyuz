package com.openland.soyuz

import com.openland.soyuz.parser.parseDocument
import com.openland.soyuz.resolver.createSelection
import kotlin.test.Test
import kotlin.test.assertEquals


class ResolverTests {

    @Test
    fun testSelectionBuilder() {
        val doc = parseDocument("{ field(id: \"String\") { some(offset: [1,2,3],keys: []) } }")
        val selection = createSelection(doc)
        assertEquals(1, selection.fields.size)
        assertEquals("field", selection.fields[0].requestKey)
        assertEquals("ROOT_QUERY.field(id:\"String\")", selection.fields[0].storeKey)
        assertEquals("some", selection.fields[0].fields[0].requestKey)
        assertEquals("some(keys:[],offset:[1,2,3])", selection.fields[0].fields[0].storeKey)
    }
}