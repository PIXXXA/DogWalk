package com.firsttask.dog.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.firsttask.dog.db.entity.Owner

@Dao
interface OwnerDao {
    @Insert
    fun insert(owner: Owner)

    @Update
    fun update(owner: Owner)

    @Delete
    fun delete(owner: Owner)
}