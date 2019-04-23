package com.openland.soyuz.resolver

import com.openland.soyuz.store.RecordStore

class Resolver(val store: RecordStore) {

    fun merge(response: Response, selection: Selection) {
        val normalized = normalizeResponse(response, selection)
        store.merge(normalized)
    }
}