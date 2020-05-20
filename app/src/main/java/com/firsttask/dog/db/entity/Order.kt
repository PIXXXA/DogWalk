package com.firsttask.dog.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Order(

    @PrimaryKey(autoGenerate = true)
    var orderId: Long?,

    var timeZone: String?,

    @ForeignKey(
        entity = Owner::class,
        parentColumns = ["orderId"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    var ownerId: Long?,

    @ForeignKey(
        entity = Pet::class,
        parentColumns = ["orderId"],
        childColumns = ["petId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    var petId: Long?
)