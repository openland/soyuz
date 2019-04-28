package com.openland.soyuz

import com.openland.soyuz.gen.Operations
import com.openland.soyuz.store.RecordSet
import com.openland.soyuz.transport.TransportOperationCallback
import com.openland.soyuz.transport.WebSocketTransport
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test

class WSTest {

    @Test
    fun testWebSocketConnection() {
        val client = SoyuzClient("wss://api.openland.com/api")
        client.query(Operations.Account, object : OperationCallback {
            override fun onResult(result: RecordSet) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(result: JsonObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        runBlocking {
            delay(10000)
        }
    }
}