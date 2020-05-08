package com.firsttask.dog.di

import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.login.LoginViewModelFactory
import com.firsttask.dog.fragments.registration.RegistrationFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun getRegistrationViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): RegistrationFactory {
        return RegistrationFactory(
            resourceProvider,
            appDatabase
        )
    }

    @Provides
    @Singleton
    fun getLoginViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): LoginViewModelFactory {
        return LoginViewModelFactory(
            resourceProvider,
            appDatabase
        )
    }
}