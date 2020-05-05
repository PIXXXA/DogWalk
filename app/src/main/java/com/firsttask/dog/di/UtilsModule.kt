package com.firsttask.dog.di

import android.content.Context
import com.firsttask.dog.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun getResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

}