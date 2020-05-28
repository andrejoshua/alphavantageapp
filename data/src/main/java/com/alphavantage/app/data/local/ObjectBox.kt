package com.alphavantage.app.data.local

import android.content.Context
import com.alphavantage.app.data.local.`object`.forex.MyObjectBox
import io.objectbox.BoxStore

class ObjectBox {

    companion object {

        lateinit var boxStore: BoxStore
            private set

        fun init(context: Context) {
            boxStore = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
        }
    }
}