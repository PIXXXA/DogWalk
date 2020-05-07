package com.firsttask.dog.di

import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.fragments.registration.FragmentRegistrationFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun getStartScreenViewModelFactory(
        resourceProvider: ResourceProvider,
        appDatabase: AppDatabase
    ): FragmentRegistrationFactory {
        return FragmentRegistrationFactory(
            resourceProvider,
            appDatabase
        )
    }
}