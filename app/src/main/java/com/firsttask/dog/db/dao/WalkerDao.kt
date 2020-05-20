package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.db.entity.sdfg

@Dao
interface WalkerDao {
    @Insert
    fun insert(walker: Walker)

    @Update
    fun update(walker: Walker)

    @Delete
    fun delete(walker: Walker)

    @Query("Select name, surname, walkersDescription, walkersExperience from Walker join User on Walker.userId=User.userId where walkersDescription!=null or walkersExperience!=null")
    fun getAllWalkers(): sdfg
}