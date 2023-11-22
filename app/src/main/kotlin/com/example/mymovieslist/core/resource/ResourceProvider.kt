package com.example.mymovieslist.core.resource

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getStringResource(@StringRes stringId: Int): String {
        return context.resources.getString(stringId)
    }
}