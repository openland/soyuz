package com.openland.soyuz

actual object Platform {
    actual val type: PlatformType = PlatformType.JVM
    actual fun currentTimeMilliSeconds(): Long = System.currentTimeMillis()
}