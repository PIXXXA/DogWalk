package com.firsttask.dog.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Pet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "petId", index = true)
    var petId: Long?,

    @ColumnInfo(name = "petName")
    var name: String?,

    @ColumnInfo(name = "petAge")
    var age: String?,

    @ColumnInfo(name = "petDescription")
    var description: String?,

    @ColumnInfo(name = "petSize")
    var size: String?,

    @ForeignKey(
        entity = Owner::class,
        parentColumns = ["petId"],
        childColumns = ["ownerFK"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo(name = "ownerFK", index = true)
    var ownerFK: Long?
)