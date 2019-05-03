package com.openland.soyuz.store

private class Subscription(val callback: () -> Unit, val ids: Set<String>)

class RecordStoreBus {
    private var nextId = 1
    private val subsciptions = mutableMapOf<Int, Subscription>()

    fun publish(changes: Map<String, ChangedRecord>) {
        val subskeys = mutableSetOf<String>()
        for (e in changes) {
            for (k in e.value.fields) {
                subskeys.add(e.key + "." + k)
            }
        }
        for (cb in subsciptions) {
            if (cb.value.ids.any { subskeys.contains(it) }) {
                cb.value.callback()
            }
        }
    }

    fun subscribe(keys: Map<String, Set<String>>, onChanged: () -> Unit): () -> Unit {
        val id = nextId++
        val subskeys = mutableSetOf<String>()
        for (e in keys) {
            for (k in e.value) {
                subskeys.add(e.key + "." + k)
            }
        }
        subsciptions[id] = Subscription(onChanged, subskeys)
        return {
            subsciptions.remove(id)
        }
    }
}