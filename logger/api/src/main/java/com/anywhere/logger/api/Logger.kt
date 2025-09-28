package com.anywhere.logger.api

import kotlin.reflect.KClass


interface Logger {
    fun debug(message: String)
    fun error(message: String, throwable: Throwable? = null)
    interface Factory {
        fun create(): Logger
    }
}

fun Logger.Factory.createLogger(): Logger = create()

