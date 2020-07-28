package com.daya.android.recyclerview.util

import android.util.Log
import com.daya.android.recyclerview.BuildConfig

object Logger {
    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }
}