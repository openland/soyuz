package com.openland.soyuz

import com.openland.soyuz.gen.NormalizedCollection
import com.openland.soyuz.gen.Scope
import com.openland.soyuz.gen.normalizeRoomSearch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test

val data = """
            {
  "data": {
    "items": {
      "__typename": "RoomConnection",
      "edges": [
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "1",
          "node": {
            "__typename": "SharedRoom",
            "id": "Om49WwAPeyHKbEmlw4PDs9XBlq",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "kokok",
              "photo": null
            },
            "photo": "ph://5",
            "title": "kokok"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "2",
          "node": {
            "__typename": "SharedRoom",
            "id": "ej97zq4pPLIw51KxDnbKuEq03q",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "3",
          "node": {
            "__typename": "SharedRoom",
            "id": "5Xmd1J76WkiJE061l31rFmrBZL",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "JIT",
              "photo": null
            },
            "photo": "https://ucarecdn.com/f86f81c7-fa8c-46fb-b65f-4fe3ee712153/-/crop/212x212/35,0/",
            "title": "new api 3"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "4",
          "node": {
            "__typename": "SharedRoom",
            "id": "61MyVnm7YDfzqmJvMzR6Ul6RXb",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 10,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "ph://4",
            "title": "Remote Engineers"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "5",
          "node": {
            "__typename": "SharedRoom",
            "id": "4dmAE76OY0FR76MnwmBmCZlmAz",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://0",
            "title": "ddd"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "6",
          "node": {
            "__typename": "SharedRoom",
            "id": "ZYx4d9K6VmhljdO6qm5pi7ORMB",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 30,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Friends of Openland",
              "photo": "https://ucarecdn.com/49b8c721-fe48-4375-8801-2908a0774aaf/-/crop/510x510/0,0/"
            },
            "photo": "https://ucarecdn.com/277bd185-2266-4658-affa-c45e0d278f84/-/crop/510x510/0,0/",
            "title": "The Future of Messaging"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "7",
          "node": {
            "__typename": "SharedRoom",
            "id": "xwQxobvJ6nFwgAmrLMQaUg57pk",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 43,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Urban Planning",
              "photo": "https://ucarecdn.com/77ea9796-a94f-4ed4-9388-0ddd3c495177/-/crop/500x500/0,0/"
            },
            "photo": "https://ucarecdn.com/a1a269c4-04fe-438a-8cb5-4a03a144871b/-/crop/500x500/0,0/",
            "title": "Cities of the Future"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "8",
          "node": {
            "__typename": "SharedRoom",
            "id": "lQKjZMAvW4SwWmWWqX1wc99Lw3",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 15,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "https://ucarecdn.com/8309ec69-8529-4e0b-9b59-c74bfd65ac4e/-/crop/1024x1024/0,0/",
            "title": "rugamers"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "9",
          "node": {
            "__typename": "SharedRoom",
            "id": "ygJmWn5q7lcQnbzx1Az6Hkp4EL",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "ph://3",
            "title": "SJ Test community Room 2"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "10",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljY7xTd5n0EEwodS4Q5pk",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://1",
            "title": "Test GroupMeow3"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "11",
          "node": {
            "__typename": "SharedRoom",
            "id": "7Vd4aLWmZOSPP7jAwJvxhWewOM",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 5,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "ph://0",
            "title": "Test group for permissions SJ com !123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "12",
          "node": {
            "__typename": "SharedRoom",
            "id": "ygJmWn5q76in7PkxaREnfoBdeA",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Готовим рагу правильно",
              "photo": "https://ucarecdn.com/a6c3a155-4788-46a8-a4e7-bb206547a325/-/crop/190x190/1,0/"
            },
            "photo": "ph://5",
            "title": "123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "13",
          "node": {
            "__typename": "SharedRoom",
            "id": "ZYx4d9K6kVuDO1jq70YRC4yW3a",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 3,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Test community F13",
              "photo": "https://ucarecdn.com/183c27c4-626c-4749-aa2c-d69c29deb99f/-/crop/236x236/0,0/"
            },
            "photo": "ph://1",
            "title": "Test shared group M22"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "14",
          "node": {
            "__typename": "SharedRoom",
            "id": "M6Pl7R30rQIoZmMORgWLtdJWLn",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "https://ucarecdn.com/ff3dc30b-34cd-4704-be97-e25fbe4ce84e/-/crop/809x809/243,0/",
            "title": "C-c-c-c-crash room_"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "15",
          "node": {
            "__typename": "SharedRoom",
            "id": "orzRJa7oM6IkEnXY5xozIZPbjn",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 7,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Ava J perm test com",
              "photo": null
            },
            "photo": "ph://2",
            "title": "T O Group Permissions (Ava J)"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "16",
          "node": {
            "__typename": "SharedRoom",
            "id": "orzRJa7oMMFvE1WjOPXqTkKkqP",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://1",
            "title": "Genesis"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "17",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljY77f6lledepXWS4wvzX",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 4,
            "membership": "LEFT",
            "organization": {
              "__typename": "Organization",
              "name": "Fun",
              "photo": null
            },
            "photo": "ph://0",
            "title": " p"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "18",
          "node": {
            "__typename": "SharedRoom",
            "id": "jZVjLe3aW4Sog4WDpm7zUPZmeg",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 5,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Founders",
              "photo": "https://ucarecdn.com/d2343671-1104-4a3f-9fc4-02b2f0ec5c6d/-/crop/662x662/0,0/"
            },
            "photo": "ph://0",
            "title": "Yury Lifshits — Ask Me Anything"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "19",
          "node": {
            "__typename": "SharedRoom",
            "id": "LOLqoerb7Bc3DgroKVBDcERJ7n",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "20",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LaYwC6oreA1oWWh9odEW",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "asd",
              "photo": null
            },
            "photo": "ph://0",
            "title": "!"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "21",
          "node": {
            "__typename": "SharedRoom",
            "id": "7Vd4aLWmYyUZvvp9E5zDfPbZvp",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Openland (Old)",
              "photo": "https://ucarecdn.com/b012dd5f-7ba4-44ca-9439-0318a854b5d2/-/crop/1024x1024/0,0/"
            },
            "photo": "https://ucarecdn.com/e4f01b90-55ba-4f24-839d-e98b7b3a8ef3/-/crop/1024x1024/0,0/",
            "title": "Data Science"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "22",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LaODSWXZ3xJ0P5CybWBL",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 409,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Openland (Old)",
              "photo": "https://ucarecdn.com/b012dd5f-7ba4-44ca-9439-0318a854b5d2/-/crop/1024x1024/0,0/"
            },
            "photo": "https://ucarecdn.com/7591c8e4-d245-418e-a74a-36545457a76f/-/crop/1024x1024/0,0/",
            "title": "Openland Beta"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "23",
          "node": {
            "__typename": "SharedRoom",
            "id": "ygJmWn5q6pHYYO6XKM1EI10MQo",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 4,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "foo",
              "photo": null
            },
            "photo": "ph://0",
            "title": "foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "24",
          "node": {
            "__typename": "SharedRoom",
            "id": "jZVjLe3aqJhy6QYDpY4Dco34zY",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "25",
          "node": {
            "__typename": "SharedRoom",
            "id": "PWQJw7OZmpflnVzm14kBfKXJOK",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "26",
          "node": {
            "__typename": "SharedRoom",
            "id": "0DW7dl3rAPTmLD5zEzrocg96wm",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "27",
          "node": {
            "__typename": "SharedRoom",
            "id": "av6pa90njBSOMnZRbyokUdvjj3",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://1",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "28",
          "node": {
            "__typename": "SharedRoom",
            "id": "nqoZQV6zMOI0EQMZVj06Tdr4qd",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://2",
            "title": "N"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "29",
          "node": {
            "__typename": "SharedRoom",
            "id": "7Vd4aLWmOxiZWwAvD3qVImK7qj",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "С"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "30",
          "node": {
            "__typename": "SharedRoom",
            "id": "xwQxobvJ6Qs6e4Ydp69psgWLq3",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://1",
            "title": "ACME"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "31",
          "node": {
            "__typename": "SharedRoom",
            "id": "Y96dY7aO5nUdnolzJklzIwQ0qW",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 3,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "ph://5",
            "title": "🍳OvercookedParty"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "32",
          "node": {
            "__typename": "SharedRoom",
            "id": "nqoZQV6zYDiRLykApyaLiOKO14",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "LEFT",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://3",
            "title": "Булка с колой"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "33",
          "node": {
            "__typename": "SharedRoom",
            "id": "7Vd4aLWmZpsZRBeJrKzktWDlzZ",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Anonymous Bridge Builders",
              "photo": "https://ucarecdn.com/0c8d1491-43c8-49e6-830b-bbf3bb5d5d5d/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://5",
            "title": "test-group"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "34",
          "node": {
            "__typename": "SharedRoom",
            "id": "61MyVnm7PpsBO1EM7QlAslDAeO",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Test community F13",
              "photo": "https://ucarecdn.com/183c27c4-626c-4749-aa2c-d69c29deb99f/-/crop/236x236/0,0/"
            },
            "photo": "https://ucarecdn.com/6bec6798-64f2-4778-bb24-038aeeb1cfaf/-/crop/236x236/0,0/",
            "title": "Test com F13 Group"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "35",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljYd7UQYxMoawxZC03drR",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Cr"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "36",
          "node": {
            "__typename": "SharedRoom",
            "id": "vmZR69a41kcDV5dxR7Xmcpkk74",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://3",
            "title": "S"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "37",
          "node": {
            "__typename": "SharedRoom",
            "id": "9KkDvyowYQSj67nVWoyyTr97b1",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://5",
            "title": "test join"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "38",
          "node": {
            "__typename": "SharedRoom",
            "id": "3Ym4RrOAevComRYReVB9iwxAx5",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "JIT",
              "photo": null
            },
            "photo": "ph://0",
            "title": "test join"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "39",
          "node": {
            "__typename": "SharedRoom",
            "id": "lQKjZMAvWgTxRYKxnPEOUQaYVd",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "test join"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "40",
          "node": {
            "__typename": "SharedRoom",
            "id": "61MyVnm7WaIEykJ9zExDfzJme7",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "JIT",
              "photo": null
            },
            "photo": "ph://4",
            "title": "room"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "41",
          "node": {
            "__typename": "SharedRoom",
            "id": "nqoZQV6zYMH1wb5ZW7DzUd7Ojo",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 12,
            "membership": "LEFT",
            "organization": {
              "__typename": "Organization",
              "name": "Com for testing permissions",
              "photo": null
            },
            "photo": "https://ucarecdn.com/ac5ba940-ce8d-4e90-a984-659afc52e08b/-/crop/586x586/105,0/",
            "title": "T C G Permissions"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "42",
          "node": {
            "__typename": "SharedRoom",
            "id": "4dmAE76O0YiQxxLekLbpfZwQwX",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Готовим рагу правильно",
              "photo": "https://ucarecdn.com/a6c3a155-4788-46a8-a4e7-bb206547a325/-/crop/190x190/1,0/"
            },
            "photo": "ph://5",
            "title": "123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "43",
          "node": {
            "__typename": "SharedRoom",
            "id": "jZVjLe3aqJhL43w0glzncKYBrn",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 29,
            "membership": "LEFT",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "https://ucarecdn.com/e369b627-429d-4b00-81fc-842687d7234d/-/crop/1024x1024/0,0/",
            "title": "Foo_6"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "44",
          "node": {
            "__typename": "SharedRoom",
            "id": "ZYx4d9K6kzHRX5pELzdeH4qW5J",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 4,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Test channel for joins"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "45",
          "node": {
            "__typename": "SharedRoom",
            "id": "WD69pr1lK9UJXxZBmbkEcy4wPz",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "KICKED",
            "organization": {
              "__typename": "Organization",
              "name": "Jail",
              "photo": "https://ucarecdn.com/6e9c8dd9-4913-478e-915e-67106357a786/-/crop/378x378/11,0/"
            },
            "photo": "ph://2",
            "title": "Tesla News"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "46",
          "node": {
            "__typename": "SharedRoom",
            "id": "lQKjZMAv6RcY91pkoq6XiRyzDQ",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 154,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Friends of Openland",
              "photo": "https://ucarecdn.com/49b8c721-fe48-4375-8801-2908a0774aaf/-/crop/510x510/0,0/"
            },
            "photo": "https://ucarecdn.com/70a85beb-a4ff-4484-9bfc-29a44c2ecff9/-/crop/510x510/0,0/",
            "title": "Friends of Openland"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "47",
          "node": {
            "__typename": "SharedRoom",
            "id": "ZYx4d9K6kJFz6dmnRB3bSDJr6b",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 24,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://1",
            "title": "Test GroupMeow2"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "48",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljYdehQ9wR9MoLJIrLrLa",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 7,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "https://ucarecdn.com/0839d670-936b-45bb-8911-2437d13a4d44/-/crop/512x512/0,0/",
            "title": "Node JS"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "49",
          "node": {
            "__typename": "SharedRoom",
            "id": "WD69pr1lmkTXl7dvpPMbiyKL66",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://0",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "50",
          "node": {
            "__typename": "SharedRoom",
            "id": "WD69pr1lmkTaOymKbz3OCyJjqQ",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "51",
          "node": {
            "__typename": "SharedRoom",
            "id": "Y96dY7aO1DsL0B9rVQrrUml5q5",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 17,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "https://ucarecdn.com/1cf1d517-98cd-471b-8344-731c16307037/-/crop/300x300/0,0/",
            "title": "FoundationDB"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "52",
          "node": {
            "__typename": "SharedRoom",
            "id": "ZYx4d9K6zkU1Dn0BeQn7h45DB6",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "T"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "53",
          "node": {
            "__typename": "SharedRoom",
            "id": "PWQJw7OZmziKzDkRWOM3c3P3WK",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "acme boom"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "54",
          "node": {
            "__typename": "SharedRoom",
            "id": "ygJmWn5qKkUwp5AO1A3QUM45Qa",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 34,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "ph://4",
            "title": "International Jobs"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "55",
          "node": {
            "__typename": "SharedRoom",
            "id": "Rgq6MV7QmDImzyEKdyBJC7nE4p",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 14,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "ph://1",
            "title": "Когда Одерски позвонит"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "56",
          "node": {
            "__typename": "SharedRoom",
            "id": "xwQxobvJaBUdvQPZBw47hMl6B1",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 8,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "ph://4",
            "title": "React"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "57",
          "node": {
            "__typename": "SharedRoom",
            "id": "4dmAE76O09uAXMyEKOoZTJpDjn",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 11,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Designers",
              "photo": "https://ucarecdn.com/af68127f-107d-44fb-a075-86435c408dbe/-/crop/1200x1200/0,0/"
            },
            "photo": "https://ucarecdn.com/7ca256df-0efb-45e3-a103-ee8f828aa9f3/-/crop/1200x1200/0,0/",
            "title": "Designers"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "58",
          "node": {
            "__typename": "SharedRoom",
            "id": "QwJ9g164evFdK0nBBQbji1BKy6",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 33,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Founders",
              "photo": "https://ucarecdn.com/d2343671-1104-4a3f-9fc4-02b2f0ec5c6d/-/crop/662x662/0,0/"
            },
            "photo": "https://ucarecdn.com/dd32131e-bed2-4236-9146-654fdd7c544b/-/crop/500x500/0,0/",
            "title": "Fundraising Help"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "59",
          "node": {
            "__typename": "SharedRoom",
            "id": "ygJmWn5q7MIexwbbreDAHo5Ojp",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://2",
            "title": "Test GroupMeow"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "60",
          "node": {
            "__typename": "SharedRoom",
            "id": "Jlb4AOJBx1TVb6qVWVOrskjqdd",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://5",
            "title": "Frgrfgfrg"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "61",
          "node": {
            "__typename": "SharedRoom",
            "id": "av6pa90nyWC7QRR90PKBsdd5Ed",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "🤷‍♂️ 1",
              "photo": "https://ucarecdn.com/0e142e33-e329-4ee5-9ea2-f4cfde456de3/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://1",
            "title": "Meow22"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "62",
          "node": {
            "__typename": "SharedRoom",
            "id": "Rgq6MV7QABco4ny6rponinoAL5",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 9,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "https://ucarecdn.com/d6fbf8d5-3fb5-4e36-bda5-12fe41a819c8/-/crop/116x116/0,0/",
            "title": "SJ Test com group 123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "63",
          "node": {
            "__typename": "SharedRoom",
            "id": "PWQJw7OZAAhgQKjDrpZlcOBEPn",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Нксе"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "64",
          "node": {
            "__typename": "SharedRoom",
            "id": "lQKjZMAv77Cbb0RzM1pQcQDvaB",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://3",
            "title": "asd"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "65",
          "node": {
            "__typename": "SharedRoom",
            "id": "xwQxobvJ77t7vWnml7zYS9JQpY",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Готовим рагу правильно",
              "photo": "https://ucarecdn.com/a6c3a155-4788-46a8-a4e7-bb206547a325/-/crop/190x190/1,0/"
            },
            "photo": "ph://4",
            "title": "~"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "66",
          "node": {
            "__typename": "SharedRoom",
            "id": "7Vd4aLWmZOS14oLzbVqySW0Ooj",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Test community m13",
              "photo": null
            },
            "photo": "ph://2",
            "title": "T C Group Leave"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "67",
          "node": {
            "__typename": "SharedRoom",
            "id": "dB6k5PZDyyUJWQoB0xZ6soO4Oz",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "Genesis 4"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "68",
          "node": {
            "__typename": "SharedRoom",
            "id": "dB6k5PZDyJh77pon9zggsopOPj",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 11,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Test community F13",
              "photo": "https://ucarecdn.com/183c27c4-626c-4749-aa2c-d69c29deb99f/-/crop/236x236/0,0/"
            },
            "photo": "ph://2",
            "title": "T C Channel ap8"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "69",
          "node": {
            "__typename": "SharedRoom",
            "id": "nqoZQV6zWZTjlv5gp9LwTWEoKM",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Хата комьюнити",
              "photo": null
            },
            "photo": "ph://3",
            "title": "sad"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "70",
          "node": {
            "__typename": "SharedRoom",
            "id": "zoqLwdzrvLIVmoQK1nPYsQ1bg7",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Хата комьюнити",
              "photo": null
            },
            "photo": "ph://3",
            "title": "группа другого юзвера"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "71",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljYd0UvzPRpP9nVs4MXXe",
            "isChannel": true,
            "kind": "PUBLIC",
            "membersCount": 3,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Fun",
              "photo": null
            },
            "photo": "https://ucarecdn.com/0124fbf2-ebd5-4cb2-9bdc-103250cdbf6e/-/crop/1024x1024/0,0/",
            "title": "Tesla"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "72",
          "node": {
            "__typename": "SharedRoom",
            "id": "Rgq6MV7Qrou5y4e0gOqgh7XoyK",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "123"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "73",
          "node": {
            "__typename": "SharedRoom",
            "id": "vmZR69a4eDTkZ5nvryyVidje1Q",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 23,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Friends of Openland",
              "photo": "https://ucarecdn.com/49b8c721-fe48-4375-8801-2908a0774aaf/-/crop/510x510/0,0/"
            },
            "photo": "https://ucarecdn.com/f35a102c-cb77-4387-b937-e91bb9e12165/-/crop/693x693/0,0/",
            "title": "Openland alpha"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "74",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LaODS1WAAx65wVI3m55l",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 65,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Proptech",
              "photo": null
            },
            "photo": "https://ucarecdn.com/c6db9843-a3c9-4dae-83a7-14fe90eaea9a/-/crop/256x256/0,0/",
            "title": "Proptech"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "75",
          "node": {
            "__typename": "SharedRoom",
            "id": "QwJ9g164mETYv75mdemnH1VlKD",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "76",
          "node": {
            "__typename": "SharedRoom",
            "id": "Xp6B93VAwkTmbmZVzo0Duk7OAV",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Foo",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Foo"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "77",
          "node": {
            "__typename": "SharedRoom",
            "id": "lQKjZMAv69Cx1AaXX6nOU9dvBx",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Wat",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Wat"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "78",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LaOXCvjpzqaJvzHJrrXD",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Russian Founders",
              "photo": null
            },
            "photo": "ph://2",
            "title": "Russian Founders"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "79",
          "node": {
            "__typename": "SharedRoom",
            "id": "mJvq41O5YZhYlKMZ3Oj7he01ZD",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "JIT",
              "photo": null
            },
            "photo": "ph://2",
            "title": "dfgfdfdsdfdswd"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "80",
          "node": {
            "__typename": "SharedRoom",
            "id": "5Xmd1J76YLUzD3BlP6n5Umylje",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://2",
            "title": "R"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "81",
          "node": {
            "__typename": "SharedRoom",
            "id": "p3BPqKmjYzTKxwAyMR5puVxoMv",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 17,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Jail",
              "photo": "https://ucarecdn.com/6e9c8dd9-4913-478e-915e-67106357a786/-/crop/378x378/11,0/"
            },
            "photo": "https://ucarecdn.com/342393c9-7942-4745-8765-b126ff279e00/-/crop/255x255/358,0/",
            "title": "@leprosorium"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "82",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LaOxckB7mmJ7LyuJ51Zr",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 3,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://1",
            "title": "ACME humor"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "83",
          "node": {
            "__typename": "SharedRoom",
            "id": "BPV0ZljYOxCRrvm66qxBt4PrWd",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://0",
            "title": "Acme horror"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "84",
          "node": {
            "__typename": "SharedRoom",
            "id": "LOLqoerbg4uP5JZPpyryswbqMY",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Openland (Old)",
              "photo": "https://ucarecdn.com/b012dd5f-7ba4-44ca-9439-0318a854b5d2/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://2",
            "title": "Openland Journey"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "85",
          "node": {
            "__typename": "SharedRoom",
            "id": "M6Pl7R30oKhlZxj7dmPxIe3van",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://3",
            "title": "A"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "86",
          "node": {
            "__typename": "SharedRoom",
            "id": "AL1ZPXB9nxhJa0l94xOgudrdVr",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://4",
            "title": "A"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "87",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1Lampiov07Ebn16ijXeYR",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 1,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "test-community",
              "photo": null
            },
            "photo": "ph://1",
            "title": "room"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "88",
          "node": {
            "__typename": "SharedRoom",
            "id": "3Ym4RrOAeQIgO6bexoRWsPZP6z",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 3,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "JIT",
              "photo": null
            },
            "photo": "ph://2",
            "title": "asdasddd asdasd asd asd asd asd 1 123 asd asd asd asdas dase 123 asd asd 9"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "89",
          "node": {
            "__typename": "SharedRoom",
            "id": "D4KeQl0V7xhJYmRpqWABflZZWM",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 11,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Engineers",
              "photo": "https://ucarecdn.com/3d292aec-a1ae-4a19-96d6-bb6fe99951cf/-/crop/399x399/204,0/"
            },
            "photo": "https://ucarecdn.com/40bd7a8d-8652-4908-84b3-2041be83b044/-/crop/300x300/0,0/",
            "title": "Frontend"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "90",
          "node": {
            "__typename": "SharedRoom",
            "id": "vmZR69a4keU3XkdJeLoriwyo0Z",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Готовим рагу правильно",
              "photo": "https://ucarecdn.com/a6c3a155-4788-46a8-a4e7-bb206547a325/-/crop/190x190/1,0/"
            },
            "photo": "ph://4",
            "title": "hh"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "91",
          "node": {
            "__typename": "SharedRoom",
            "id": "4dmAE76O0eIvJx7XBwOrFJ5BVp",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 8,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "ph://5",
            "title": "Test group welcome m22"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "92",
          "node": {
            "__typename": "SharedRoom",
            "id": "mJvq41O573IQaXMKR9z5szwBjy",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "SJ Test community",
              "photo": null
            },
            "photo": "https://ucarecdn.com/e47a7775-85ca-4b0b-98ef-f326c9e3ed51/-/crop/225x225/0,0/",
            "title": "SJ test room by comm member"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "93",
          "node": {
            "__typename": "SharedRoom",
            "id": "EQvPJ1LammCLB6x91ev5c3grQv",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 0,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "ACME",
              "photo": null
            },
            "photo": "ph://1",
            "title": "Genesis"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "94",
          "node": {
            "__typename": "SharedRoom",
            "id": "b5RYKeLkwbcypnq1dl7Mhl1kvy",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Test community m13",
              "photo": null
            },
            "photo": "ph://5",
            "title": "T C Group Kick"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "95",
          "node": {
            "__typename": "SharedRoom",
            "id": "wW4975KQVzS17BDVOZojTMRK96",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 138,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Russian Startups",
              "photo": "https://ucarecdn.com/9cef3352-3010-4348-b0a2-4be689960c92/-/crop/451x450/175,0/"
            },
            "photo": "https://ucarecdn.com/c3a5e20c-5ca9-4bf1-b4a1-cf2195098ccb/-/crop/640x640/0,0/",
            "title": "42 – Russian Startups"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "96",
          "node": {
            "__typename": "SharedRoom",
            "id": "av6pa90nyZijyRBldd11UgA6O5",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 2,
            "membership": "NONE",
            "organization": {
              "__typename": "Organization",
              "name": "Openland (Old)",
              "photo": "https://ucarecdn.com/b012dd5f-7ba4-44ca-9439-0318a854b5d2/-/crop/1024x1024/0,0/"
            },
            "photo": "ph://1",
            "title": "Friends of Lightout"
          }
        },
        {
          "__typename": "RoomConnectionEdge",
          "cursor": "97",
          "node": {
            "__typename": "SharedRoom",
            "id": "4dmAE76OeWfR4qn9kdQKcZaQKy",
            "isChannel": false,
            "kind": "PUBLIC",
            "membersCount": 137,
            "membership": "MEMBER",
            "organization": {
              "__typename": "Organization",
              "name": "Founders",
              "photo": "https://ucarecdn.com/d2343671-1104-4a3f-9fc4-02b2f0ec5c6d/-/crop/662x662/0,0/"
            },
            "photo": "https://ucarecdn.com/da389cc8-1e81-4c45-a15d-69a3b6ed93f5/-/crop/712x712/0,0/",
            "title": "Investor Insights"
          }
        }
      ],
      "pageInfo": {
        "__typename": "PageInfo",
        "currentPage": 1,
        "hasNextPage": false,
        "hasPreviousPage": false,
        "itemsCount": 135,
        "openEnded": true,
        "pagesCount": 1
      }
    }
  }
}
        """.trimIndent()

class PokemonIntegrationTest {

    @Test
    fun testPokemonQuery() {
        val parsed = Json.parse(JsonElement.serializer(), data).jsonObject["data"] as JsonObject
        val start = Platform.currentTimeMilliSeconds()
        val normalized = NormalizedCollection()
        for (i in 0 until 10000) {
            val scope = Scope("ROOT_QUERY", normalized, parsed)
            normalizeRoomSearch(scope)
            println(scope.map.size)
        }
        val d = Platform.currentTimeMilliSeconds() - start
        print(d)
//        val normalized = normalizeResponse(Response(parsed), selection)
//        val store = RecordStore()
//        val keys = store.merge(normalized)
    }
}