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

    @Query("Select * from Walker where walkerDescription IS NOT NULL and walkerDescription!=:argString ")
    fun getWalker(argString: String): List<Walker>

    @Query("Select * from Walker where walkerName=:argName and walkerSurname=:argSurname and walkerMobileNumber=:argMobileNumber")
    fun getCurrentWalker(argName: String?, argSurname: String?, argMobileNumber: String?): Walker

    @Query("Select * from Walker where walkerDescription IS NOT NULL and walkerDescription!=:argValidator and walkerSurname Like:argSearchField or walkerDescription IS NOT NULL and walkerDescription!=:argValidator and walkerName Like :argSearchField or walkerDescription IS NOT NULL and walkerDescription!=:argValidator and walkerDescription Like :argSearchField or walkerDescription IS NOT NULL and walkerDescription!=:argValidator and walkerExperience Like :argSearchField or walkerDescription IS NOT NULL and walkerDescription!=:argValidator and walkerMobileNumber Like :argSearchField")
    fun searchWalker(argValidator: String?, argSearchField: String?): List<Walker>
}