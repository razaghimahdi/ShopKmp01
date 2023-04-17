package com.example.shopkmm01.common

import app.cash.sqldelight.db.SqlDriver


expect class DriverFactory {
    fun createDriver(): SqlDriver
}
