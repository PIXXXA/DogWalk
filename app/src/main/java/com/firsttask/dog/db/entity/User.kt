package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId", index = true)
    var userId: Long?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "surname")
    var surname: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "password")
    var password: String?,

    @ColumnInfo(name = "mobileNumber")
    var mobileNumber: String?,

    @ColumnInfo(name = "homeAddress")
    var homeAddress: String?,

    @ColumnInfo(name = "accountType")
    var accountType: Boolean? = null
)