package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Walker(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walkersId", index = true)
    var walkersId: Long?,

    @ColumnInfo(name = "walkersDescription")
    var description: String?,

    @ColumnInfo(name = "walkersExperience")
    var experience: String?,

    @ForeignKey(
        entity = User::class,
        parentColumns = ["walkerId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "userId", index = true)
    var userId: Long?
)