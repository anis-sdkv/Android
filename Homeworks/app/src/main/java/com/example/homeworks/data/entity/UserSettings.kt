package com.example.homeworks.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_settings",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class UserSettings(
    @PrimaryKey
    @ColumnInfo(name = "user_id") var userId: Int
) {
    var setting1: Boolean = true
    var setting2: Boolean = true
    var setting3: Boolean = true
}