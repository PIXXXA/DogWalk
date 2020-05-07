package com.firsttask.dog.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firsttask.dog.db.dao.UserDao
import com.firsttask.dog.db.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}