package com.firsttask.dog.fragments.login

import com.firsttask.dog.db.entity.User

interface LoginCallback {
    fun setData(user: User?)
}