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

    @Query("Select * From User where description!= null and experience!=null")
    fun getAllWalkers(): List<User>

    @Query("Select * From User ")
    fun getSearch(): List<User>

    @Query("Select * from User where email=:argEmail and password=:argPassword")
    fun getLoginValidation(argEmail: String, argPassword: String): User

}