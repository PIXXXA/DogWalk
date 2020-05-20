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

    var description: String?,

    @ForeignKey(
        entity = User::class,
        parentColumns = ["ownerId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "userId", index = true)
    var userId: Long?
)