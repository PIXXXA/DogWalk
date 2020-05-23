package com.firsttask.dog.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.firsttask.dog.db.entity.Announcement

@Dao
interface AnnouncementDao {
    @Insert
    fun insert(announcement: Announcement)

    @Update
    fun update(announcement: Announcement)

    @Delete
    fun delete(announcement: Announcement)

//    @Query("Select * from `Order` join Pet on petId=Pet.petId join Owner on Owner.ownerId=ownerId ")
//    fun getAllOrder(): Order
}