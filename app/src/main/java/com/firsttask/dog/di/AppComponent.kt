package com.firsttask.dog.di

import com.firsttask.dog.fragments.startscreen.FragmentStartScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilsModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(fragmentStartScreen: FragmentStartScreen)
}