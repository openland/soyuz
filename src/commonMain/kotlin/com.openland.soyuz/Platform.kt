package com.openland.soyuz

enum class PlatformType {
    WEB,
    WASM,
    IOS,
    JVM
}

expect object Platform {
    val type: PlatformType
    fun currentTimeMilliSeconds(): Long
}