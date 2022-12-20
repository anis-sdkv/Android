package com.example.homeworks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeworks.data.dao.UserDao
import com.example.homeworks.data.dao.UserSettingsDao
import com.example.homeworks.data.entity.User
import com.example.homeworks.data.entity.UserSettings

@Database(entities = [User::class, UserSettings::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getUserSettingsDao(): UserSettingsDao

    companion object {
        const val DATABASE_NAME = "homework10.db"
    }
}