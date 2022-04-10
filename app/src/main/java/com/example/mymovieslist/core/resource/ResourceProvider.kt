package com.example.mymovieslist.core.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {
    fun getStringResource(@StringRes stringId: Int): String {
        return context.resources.getString(stringId)
    }
}