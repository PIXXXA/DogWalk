package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Order

@Dao
interface OrderDao {
    @Insert
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    @Delete
    fun delete(order: Order)

//    @Query("Select * from `Order` join Pet on petId=Pet.petId join Owner on Owner.ownerId=ownerId ")
//    fun getAllOrder(): Order

}