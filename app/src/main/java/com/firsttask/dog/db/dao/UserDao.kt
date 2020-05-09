package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.User


@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("Select * From User")
    fun getAll(): List<User>

    @Query("Select * from User where email=:argEmail and password=:argPassword")
    fun getLoginValidation(argEmail: String, argPassword: String): User

    @Query("SELECT * FROM User WHERE User.name LIKE :username")
    fun getAccount(username: String): User
}