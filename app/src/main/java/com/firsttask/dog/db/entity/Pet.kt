package com.firsttask.dog.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pet(
    @PrimaryKey(autoGenerate = true)
    var petId: Long?,

    var name: String?,

    var age: Int?,

    var size: String?
)