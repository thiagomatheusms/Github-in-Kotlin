package com.example.githubKotlin

import android.app.Application
import com.example.githubKotlin.util.koinInjection


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        koinInjection()
    }

}