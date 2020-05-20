package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Walker

@Dao
interface OwnerDao {
    @Insert
    fun insert(walker: Walker)

    @Update
    fun update(walker: Walker)

    @Delete
    fun delete(walker: Walker)

//    @Query("Select name,surname,description,experience from Walker join User on User.userId=userId where description!=null or experience!=null")
//    fun getOwner(): Walker

}