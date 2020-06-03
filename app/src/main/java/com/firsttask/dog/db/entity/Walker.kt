package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Walker(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walkerId", index = true)
    var walkersId: Long?,

    @ColumnInfo(name = "walkerName")
    var name: String?,

    @ColumnInfo(name = "walkerSurname")
    var surname: String?,

    @ColumnInfo(name = "walkerMobileNumber")
    var mobileNumber: String?,

    @ColumnInfo(name = "walkerDescription")
    var description: String? = null,

    @ColumnInfo(name = "walkerExperience")
    var experience: String? = null
)