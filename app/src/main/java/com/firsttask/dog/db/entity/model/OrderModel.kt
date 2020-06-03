package com.firsttask.dog.db.entity.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class OrderModel(

    var orderDate: String?,

    var petName: String?,

    var petAge: String?,

    var petDescription: String?,

    var petSize: String?,

    var ownerName: String?,

    var ownerSurname: String?,

    var ownerMobileNumber: String?
)