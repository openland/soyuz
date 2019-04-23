package com.openland.soyuz

import com.openland.soyuz.parser.parseDocument
import kotlin.test.Test

class Benchmarks {
    @Test
    fun benchmarkDocumentParser() {
        val query = """
           {
             pokemon(name: "Pikachu") {
               id
               number
               name
               attacks {
                 special {
                   name
                   type
                   damage
                 }
               }
               evolutions {
                 id
                 number
                 name
                 weight {
                   minimum
                   maximum
                 }
                 attacks {
                   fast {
                     name
                     type
                     damage
                   }
                 }
               }
             }
           }
        """.trimIndent()
        val start = Platform.currentTimeMilliSeconds()
        for (i in 0 until 10000) {
            parseDocument(query)
        }
        println("Parsed in ${(Platform.currentTimeMilliSeconds() - start) / 10000.0}")
    }
}