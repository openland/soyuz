package com.openland.soyuz

import platform.Foundation.NSDate

actual object Platform {
    actual val type: PlatformType = PlatformType.IOS
    actual fun currentTimeMilliSeconds(): Long = (NSDate().timeIntervalSinceReferenceDate * 1000).toLong()
}