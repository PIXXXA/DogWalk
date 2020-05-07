package com.firsttask.dog.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    var mobileNumber: String,
    var homeAddress: String
)