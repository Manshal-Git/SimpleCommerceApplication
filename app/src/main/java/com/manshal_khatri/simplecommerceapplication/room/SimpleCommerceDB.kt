package com.manshal_khatri.simplecommerceapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manshal_khatri.simplecommerceapplication.model.Basket
import com.manshal_khatri.simplecommerceapplication.model.Product
import com.manshal_khatri.simplecommerceapplication.model.StoreDetail


@Database(entities = [Product::class,Basket::class,StoreDetail::class], version = 1)
abstract class SimpleCommerceDB : RoomDatabase() {

    abstract fun storeDao() : StoreDao
    abstract fun productsDao() : ProductsDao
    abstract fun OrderDao() : OrderDao

    companion object{

        @Volatile
        private var instance : SimpleCommerceDB? = null

        fun getDatabase(context: Context) : SimpleCommerceDB {

            if(instance ==null) {
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SimpleCommerceDB::class.java,
                        "simple_commerce_app_db"
                    ).build()
                }
            }
            return instance!!
        }
    }

}