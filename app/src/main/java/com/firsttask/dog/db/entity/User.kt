package com.firsttask.dog.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long?,

    var name: String?,

    var surname: String?,

    var email: String?,

    var password: String?,

    var mobileNumber: String?,

    var homeAddress: String?,

    var accountType: Boolean? = null
)