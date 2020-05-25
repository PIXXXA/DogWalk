package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Pet

@Dao
interface PetDao {
    @Insert
    fun insert(pet: Pet)

    @Update
    fun update(pet: Pet)

    @Delete
    fun delete(pet: Pet)

    @Query("Select petName,petDescription,petId,petAge,petSize,ownerFK from Pet join Owner on ownerFK=ownerId where ownerMobileNumber=:argMobileNumber")
    fun getAllPet(argMobileNumber : String?): Pet
}