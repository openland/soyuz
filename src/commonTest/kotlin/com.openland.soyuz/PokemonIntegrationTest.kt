package com.openland.soyuz

import com.openland.soyuz.parser.parseDocument
import com.openland.soyuz.resolver.Response
import com.openland.soyuz.resolver.createSelection
import com.openland.soyuz.resolver.normalizeResponse
import com.openland.soyuz.store.RecordStore
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test

class PokemonIntegrationTest {
    @Test
    fun testPokemonQuery() {
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
        val document = parseDocument(query)
        val selection = createSelection(document)
        val data = """
            {
              "data": {
                "pokemon": {
                  "id": "UG9rZW1vbjowMjU=",
                  "number": "025",
                  "name": "Pikachu",
                  "attacks": {
                    "special": [
                      {
                        "name": "Discharge",
                        "type": "Electric",
                        "damage": 35
                      },
                      {
                        "name": "Thunder",
                        "type": "Electric",
                        "damage": 100
                      },
                      {
                        "name": "Thunderbolt",
                        "type": "Electric",
                        "damage": 55
                      }
                    ]
                  },
                  "evolutions": [
                    {
                      "id": "UG9rZW1vbjowMjY=",
                      "number": "026",
                      "name": "Raichu",
                      "weight": {
                        "minimum": "26.25kg",
                        "maximum": "33.75kg"
                      },
                      "attacks": {
                        "fast": [
                          {
                            "name": "Spark",
                            "type": "Electric",
                            "damage": 7
                          },
                          {
                            "name": "Thunder Shock",
                            "type": "Electric",
                            "damage": 5
                          }
                        ]
                      }
                    }
                  ]
                }
              }
            }
        """.trimIndent()
        val parsed = Json.parse(JsonElement.serializer(), data).jsonObject["data"] as JsonObject
        val normalized = normalizeResponse(Response(parsed), selection)
        val store = RecordStore()
        val keys = store.merge(normalized)
    }
}