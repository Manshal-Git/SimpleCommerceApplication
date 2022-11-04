package com.manshal_khatri.simplecommerceapplication.repository

import android.content.Context
import com.manshal_khatri.simplecommerceapplication.room.SimpleCommerceDB

class Repository(context: Context) {
    private val localDataSource = SimpleCommerceDB.getDatabase(context)
}