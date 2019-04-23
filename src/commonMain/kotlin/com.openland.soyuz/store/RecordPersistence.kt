package com.openland.soyuz.store

interface RecordPersistence {
    fun write(data: Map<String, String>)
    fun read(keys: Set<String>): Map<String, String>
}

object EmptyPeristence : RecordPersistence {
    override fun read(keys: Set<String>): Map<String, String> {
        return emptyMap()
    }

    override fun write(data: Map<String, String>) {

    }
}