package com.openland.soyuz

actual object Platform {
    actual val type: PlatformType = PlatformType.WASM
    actual fun currentTimeMilliSeconds(): Long {
        return 0
    }
}