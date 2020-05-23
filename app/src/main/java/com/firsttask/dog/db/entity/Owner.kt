package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Owner(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ownerId" ,index = true)
    var ownerId: Long?,

    @ColumnInfo(name = "ownerName")
    var name: String?,

    @ColumnInfo(name = "ownerSurname")
    var surname: String?,

    @ColumnInfo(name = "ownerMobileNumber")
    var mobileNumber: String?
)