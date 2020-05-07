package com.firsttask.dog

import android.app.Application
import com.firsttask.dog.di.AppComponent
import com.firsttask.dog.di.AppModule
import com.firsttask.dog.di.DaggerAppComponent
import com.firsttask.dog.di.RoomModule

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}