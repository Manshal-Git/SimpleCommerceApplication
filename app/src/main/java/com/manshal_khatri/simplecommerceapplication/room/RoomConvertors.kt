package com.manshal_khatri.simplecommerceapplication.room

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class RoomConvertors {

    @TypeConverter
    fun intListToString(list: List<Int>):String{
        var s = ""
        for(int in list){
            s+= "$int "
        }
        return s
    }
    @TypeConverter
    fun stringToIntList(s:String):List<Int>{
        var sb = s
        val list = mutableListOf<Int>()
        while(sb.length > 0){
            list.add(sb.substringBefore(" ").toInt())
            sb = sb.substringAfter(" ")
        }
        return list
    }
    @TypeConverter
    fun floatListToString(list: List<Float>):String{
        var s = ""
        for(int in list){
            s+= "$int:"
        }
        return s
    }
    @TypeConverter
    fun stringToFloatList(s:String):List<Float>{
        var sb = s
        val list = mutableListOf<Float>()
        while(sb.length > 0){
            list.add(sb.substringBefore(":").toFloat())
            sb = sb.substringAfter(" ")
        }
        return list
    }
}