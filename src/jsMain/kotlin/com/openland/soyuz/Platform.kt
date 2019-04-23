package com.openland.soyuz

import kotlin.js.Date

actual object Platform {
    actual val type: PlatformType = PlatformType.WEB
    actual fun currentTimeMilliSeconds(): Long {
        return Date().getTime().toLong()
    }
}