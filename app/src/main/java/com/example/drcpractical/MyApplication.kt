package com.example.drcpractical

import android.app.Application
import com.example.drcpractical.database.DatabaseHelper

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseHelper.initDatabaseInstance(this)
    }

}
