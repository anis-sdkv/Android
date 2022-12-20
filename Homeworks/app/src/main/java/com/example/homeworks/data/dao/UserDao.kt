package com.example.homeworks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homeworks.data.entity.User
import com.example.homeworks.data.entity.UserAndSettings

@Dao
interface UserDao {
    @Insert
    fun save(user: User): Long

    @Query("SELECT * FROM users WHERE login = :login")
    fun getByLogin(login: String): User?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserWithSettings(id: Int): UserAndSettings?

    @Update
    fun updateUser(user: User)
}