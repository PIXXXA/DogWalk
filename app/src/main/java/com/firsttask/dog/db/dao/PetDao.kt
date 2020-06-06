package com.firsttask.dog.db.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    @Query("Select * from Pet where ownerFK=:argOwnerFK")
    fun getAllPet(argOwnerFK : Long?): LiveData<List<Pet>>
}