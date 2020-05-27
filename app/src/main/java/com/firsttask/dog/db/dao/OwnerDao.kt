package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Owner

@Dao
interface OwnerDao {
    @Insert
    fun insert(owner: Owner)

    @Update
    fun update(owner: Owner)

    @Delete
    fun delete(owner: Owner)

    @Query("Select * from Owner where ownerName=:argName and ownerSurname=:argSurname and ownerMobileNumber=:argMobileNumber")
    fun getCurrentOwner(argName: String?, argSurname: String?, argMobileNumber: String?): Owner
}