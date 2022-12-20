package com.example.homeworks.data

import android.content.Context
import androidx.room.Room
import com.example.homeworks.data.entity.User
import com.example.homeworks.data.entity.UserAndSettings
import com.example.homeworks.data.entity.UserSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(context: Context) {

    private val db by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DATABASE_NAME).build()
    }

    private val userDao by lazy {
        db.getUserDao()
    }

    private val settingsDao by lazy {
        db.getUserSettingsDao()
    }

    suspend fun saveUser(user: User): Long = withContext(Dispatchers.IO) {
        val id = userDao.save(user)
        settingsDao.save(UserSettings(id.toInt()))
        return@withContext id
    }

    suspend fun getUserWithSettings(id: Int): UserAndSettings? =
        withContext(Dispatchers.IO) { userDao.getUserWithSettings(id) }

    suspend fun getUserByLogin(login: String): User? =
        withContext(Dispatchers.IO) { userDao.getByLogin(login) }

    suspend fun updateUser(user: User) = withContext(Dispatchers.IO) { userDao.updateUser(user) }

    suspend fun updateSettings(settings: UserSettings) = withContext(Dispatchers.IO) { settingsDao.update(settings) }
}