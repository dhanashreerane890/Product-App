package com.anywhere.logger.impl

import android.util.Log
import com.anywhere.logger.api.Logger


class LoggerImpl(private val tag: String = "ProductAppLogger") : Logger {

    class Factory : Logger.Factory {
        override fun create(): Logger = LoggerImpl()
    }

    override fun debug(message: String) {
        Log.d(tag, message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}