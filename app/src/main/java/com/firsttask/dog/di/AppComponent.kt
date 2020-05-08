package com.firsttask.dog.di

import com.firsttask.dog.db.dao.UserDao
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.login.LoginFragment
import com.firsttask.dog.fragments.registration.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class, ViewModelFactoryModule::class, RoomModule::class])
interface AppComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(loginFragment: LoginFragment)

    var userDao: UserDao
    var appDatabase: AppDatabase
}