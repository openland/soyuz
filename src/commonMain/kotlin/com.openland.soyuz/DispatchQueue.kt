package com.openland.soyuz

expect class DispatchQueue() {
    fun async(handler: suspend () -> Unit)
}