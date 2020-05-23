package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Walker

@Dao
interface WalkerDao {
    @Insert
    fun insert(walker: Walker)

    @Update
    fun update(walker: Walker)

    @Delete
    fun delete(walker: Walker)

    @Query("Select walkerName,walkerSurname,walkerDescription,walkerExperience from Walker where walkerDescription!=null or walkerExperience!=null")
    fun getWalker(): Walker
}