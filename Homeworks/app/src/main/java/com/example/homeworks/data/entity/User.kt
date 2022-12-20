package com.example.homeworks.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    var login: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
