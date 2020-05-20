package com.firsttask.dog.di

import android.content.Context
import androidx.room.Room
import com.firsttask.dog.NAME_OF_DB
import com.firsttask.dog.db.dao.*
import com.firsttask.dog.db.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(context: Context) {
    private val appDatabase: AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NAME_OF_DB)
            .build()

    @Provides
    @Singleton
    fun getAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun getWalkersDao(appDatabase: AppDatabase): WalkerDao {
        return appDatabase.walkersDao()
    }

    @Provides
    @Singleton
    fun getOrderDao(appDatabase: AppDatabase): OrderDao {
        return appDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun getOwnerDao(appDatabase: AppDatabase): OwnerDao {
        return appDatabase.ownerDao()
    }

    @Provides
    @Singleton
    fun getPetDao(appDatabase: AppDatabase): PetDao {
        return appDatabase.petDao()
    }
}