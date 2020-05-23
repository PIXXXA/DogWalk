package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Announcement(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "announcementId", index = true)
    var announcementId: Long?,

    @ColumnInfo(name = "announcementCurrentDate")
    var currentDate: String,

    @ColumnInfo(name = "announcementDescription")
    var description: String,

    @ForeignKey(
        entity = Order::class,
        parentColumns = ["announcementId"],
        childColumns = ["orderFK"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "orderFK", index = true)
    var orderFK: Long?,

    @ForeignKey(
        entity = Pet::class,
        parentColumns = ["announcementId"],
        childColumns = ["petFK"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "petFK", index = true)
    var petFK: Long?
)