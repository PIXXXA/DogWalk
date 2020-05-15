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

    @Query("Select * from User where email=:argEmail and password=:argPassword")
    fun getLoginValidation(argEmail: String, argPassword: String): User

}