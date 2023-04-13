package com.razzaghi.shopkmm01

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform