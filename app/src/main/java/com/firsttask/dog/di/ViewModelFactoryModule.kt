package com.firsttask.dog.di

import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.fragments.startscreen.FragmentStartScreenFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun getStartScreenViewModelFactory(
        resourceProvider: ResourceProvider
    ): FragmentStartScreenFactory {
        return FragmentStartScreenFactory(
            resourceProvider
        )
    }
}