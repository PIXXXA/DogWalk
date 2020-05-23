package com.firsttask.dog.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firsttask.dog.db.dao.*
import com.firsttask.dog.db.entity.*

@Database(
    entities = [Order::class, Owner::class, Pet::class, User::class, Walker::class, Announcement::class],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun walkersDao(): WalkerDao

    abstract fun orderDao(): OrderDao

    abstract fun petDao(): PetDao

    abstract fun ownerDao(): OwnerDao

    abstract fun announcementDao(): AnnouncementDao
}