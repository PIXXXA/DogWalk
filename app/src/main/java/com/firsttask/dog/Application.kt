package com.firsttask.dog

import android.app.Application
import com.firsttask.dog.di.component.AppComponent
import com.firsttask.dog.di.AppModule
import com.firsttask.dog.di.RoomModule
import com.firsttask.dog.di.component.DaggerAppComponent

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