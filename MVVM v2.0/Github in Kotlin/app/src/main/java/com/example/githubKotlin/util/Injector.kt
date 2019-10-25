package com.example.githubKotlin.util

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.koinInjection() {

    startKoin {
        androidLogger()

        androidContext(this@koinInjection)

        modules(
            listOf(
                githubModule
            )
        )
    }

}