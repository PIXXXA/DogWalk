package com.firsttask.dog.db.dao

import androidx.room.*
import com.firsttask.dog.db.entity.Order
import com.firsttask.dog.db.entity.model.OrderModel

@Dao
interface OrderDao {
    @Insert
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("Select orderTime,orderDate,petName,petAge,petDescription,petSize,ownerName,ownerSurname,ownerMobileNumber from `Order` join Pet on petFK=petId join Owner on ownerFK=ownerId")
    fun getOrder(): List<OrderModel>
}