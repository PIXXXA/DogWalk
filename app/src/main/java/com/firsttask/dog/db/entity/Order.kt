package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId", index = true)
    var orderId: Long?,

    @ColumnInfo(name = "orderTime")
    var time: String?,

    @ColumnInfo(name = "orderDate")
    var date: String?,

    @ForeignKey(
        entity = Walker::class,
        parentColumns = ["orderId"],
        childColumns = ["walkerFK"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "walkerFK", index = true)
    var walkerFK: Long?
)