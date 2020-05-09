package com.firsttask.dog.db.entity

import androidx.annotation.IntegerRes
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long,

    var name: String?,

    var surname: String?,

    var email: String?,

    var password: String?,

    var mobileNumber: String?,

    var homeAddress: String?,

    var experience: String? = "",

    var description: String? = ""
)