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

    @Query("Select orderDate,petName,petAge,petDescription,petSize,ownerName,ownerSurname,ownerMobileNumber from `Order` join Pet on petFK=petId join Owner on ownerFK=ownerId")
    fun getOrder(): List<OrderModel>

    @Query(
        "Select orderDate,petName,petAge,petDescription,petSize,ownerName,ownerSurname,ownerMobileNumber from `Order` join Pet on petFK=petId join Owner on ownerFK=ownerId Where orderDate Like :argSearchString or petName Like :argSearchString or petAge Like :argSearchString or petDescription Like :argSearchString or ownerName Like :argSearchString or ownerSurname Like :argSearchString or ownerMobileNumber Like :argSearchString"
    )
    fun searchOrder(argSearchString: String?): List<OrderModel>

    @Query(
        "Select orderDate,petName,petAge,petDescription,petSize,ownerName,ownerSurname,ownerMobileNumber from `Order` join Pet on petFK=petId join Owner on ownerFK=ownerId Where petSize Like :argFilterString"
    )
    fun filterOrder(argFilterString: String?): List<OrderModel>

    @Query("Select * From `Order` Where petFK=:argId and orderDate IS NOT NULL and orderDate!=:argDate")
    fun getCurrentOrder(argId: Long?, argDate: String): Order
}