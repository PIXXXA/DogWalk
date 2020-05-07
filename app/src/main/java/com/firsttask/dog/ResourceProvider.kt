package com.firsttask.dog

import android.content.Context
import android.graphics.drawable.Drawable

class ResourceProvider(private val context: Context) {
    fun getString(resId: Int): String? {
        return context.getString(resId)
    }

    fun getDrawable(id: Int): Drawable? {
        return context.getDrawable(id)
    }
}