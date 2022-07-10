package com.boratuzun.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boratuzun.todoapp.data.entity.Isler

@Database(entities = [Isler::class], version = 1)
abstract class Veritabani : RoomDatabase(){
    abstract fun getIslerDao() : IslerDao
}