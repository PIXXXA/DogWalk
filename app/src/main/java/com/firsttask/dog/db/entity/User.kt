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

    @ColumnInfo(name = "userName")
    var name: String?,

    @ColumnInfo(name = "userSurname")
    var surname: String?,

    @ColumnInfo(name = "userEmail")
    var email: String?,

    @ColumnInfo(name = "userPassword")
    var password: String?,

    @ColumnInfo(name = "userMobileNumber")
    var mobileNumber: String?,

    @ColumnInfo(name = "userHomeAddress")
    var homeAddress: String?,

    @ColumnInfo(name = "userAccountType")
    var accountType: Boolean? = null
)