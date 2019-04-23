package com.openland.soyuz

import com.openland.soyuz.parser.DocumentArgumentType
import com.openland.soyuz.parser.DocumentType
import com.openland.soyuz.parser.parseDocument
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ParserTests {
    @Test
    fun testParser() {
        var doc = parseDocument("{ field { some } }")
        assertEquals(DocumentType.QUERY, doc.type)
        doc = parseDocument("mutation { field { some } }")
        assertEquals(DocumentType.MUTATION, doc.type)
        doc = parseDocument("query { field { some } }")
        assertEquals(DocumentType.QUERY, doc.type)
        doc = parseDocument("subscription { field { some } }")
        assertEquals(DocumentType.SUBSCRIPTION, doc.type)
    }

    @Test
    fun testSelectionSet() {
        val doc = parseDocument("{ query1 query2 { alias1: field1 } }")
        assertEquals(DocumentType.QUERY, doc.type)
        assertEquals(2, doc.selection.fields.size)
        assertEquals(null, doc.selection.fields[0].alias)
        assertEquals("query1", doc.selection.fields[0].name)
        assertEquals(null, doc.selection.fields[1].alias)
        assertEquals("query2", doc.selection.fields[1].name)
        assertEquals(1, doc.selection.fields[1].selection!!.fields.size)
        assertEquals("field1", doc.selection.fields[1].selection!!.fields[0].name)
        assertEquals("alias1", doc.selection.fields[1].selection!!.fields[0].alias)
    }

    @Test
    fun testArguments() {
        val doc = parseDocument(
            "query Name(" +
                    "\$id: String, \$ids: [String],\$input: [SomeInputType!]!, " +
                    "\$float: Float, \$int: Int, \$boolean: Boolean) " +
                    "{ query }"
        )
        assertNotNull(doc.arguments)
        assertEquals(6, doc.arguments!!.size)
        assertEquals("\$id", doc.arguments!![0].name)
        assertEquals(DocumentArgumentType.String, doc.arguments!![0].type)
        assertEquals("\$ids", doc.arguments!![1].name)
        assertEquals(DocumentArgumentType.List(DocumentArgumentType.String), doc.arguments!![1].type)
        assertEquals("\$input", doc.arguments!![2].name)
        assertEquals(
            DocumentArgumentType.NotNull(
                DocumentArgumentType.List(
                    DocumentArgumentType.NotNull(
                        DocumentArgumentType.Named("SomeInputType")
                    )
                )
            ),
            doc.arguments!![2].type
        )
        assertEquals("\$float", doc.arguments!![3].name)
        assertEquals(DocumentArgumentType.Float, doc.arguments!![3].type)
        assertEquals("\$int", doc.arguments!![4].name)
        assertEquals(DocumentArgumentType.Int, doc.arguments!![4].type)
        assertEquals("\$boolean", doc.arguments!![5].name)
        assertEquals(DocumentArgumentType.Boolean, doc.arguments!![5].type)
    }
}