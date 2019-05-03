package com.openland.soyuz

import com.openland.soyuz.gen.Operations
import com.openland.soyuz.store.RecordStore
import com.openland.soyuz.store.readFromStore
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SelectorReaderTest {

    @Test
    fun testSelectorReader() {
        val data = """
            {"me":null,"myPermissions":{"__typename":"Permissions","roles":[]},"sessionState":{"__typename":"SessionState","isAccountActivated":false,"isAccountExists":false,"isAccountPicked":false,"isBlocked":false,"isCompleted":false,"isLoggedIn":false,"isProfileCreated":false}}
        """.trimIndent()
        val parsed = Json.parse(JsonObject.serializer(), data)
        val normalized = Operations.Account.normalizeResponse(parsed)
        val store = RecordStore()

        // Should not resolve
        var res = readFromStore("ROOT_QUERY", store, Operations.Account.selector!!)
        assertFalse(res.first)

        // Write data to store
        store.merge(normalized)

        // Read from store
        res = readFromStore("ROOT_QUERY", store, Operations.Account.selector!!)
        assertTrue(res.first)

        assertEquals(parsed, res.second!!)
    }
}