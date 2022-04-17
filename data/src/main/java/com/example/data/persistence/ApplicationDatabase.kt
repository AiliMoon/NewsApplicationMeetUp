package com.example.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.persistence.dao.NewsDao
import com.example.data.persistence.entity.ArticleRoomEntity

@Database(entities = [ArticleRoomEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}