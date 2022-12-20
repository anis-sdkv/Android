package com.example.homeworks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.homeworks.data.entity.UserSettings

@Dao
interface UserSettingsDao {
    @Insert(onConflict = REPLACE)
    fun save(settings: UserSettings)

    @Update
    fun update(settings: UserSettings)
}