package com.firsttask.dog.di

import com.firsttask.dog.db.dao.UserDao
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.registration.FragmentRegistration
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class, ViewModelFactoryModule::class, RoomModule::class])
interface AppComponent {
    fun inject(fragmentRegistration: FragmentRegistration)

    var userDao: UserDao
    var appDatabase: AppDatabase
}