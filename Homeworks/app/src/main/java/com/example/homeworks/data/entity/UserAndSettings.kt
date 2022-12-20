package com.example.homeworks.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndSettings(
    @Embedded var user: User,
    @Relation(parentColumn = "id", entityColumn = "user_id") var settings: UserSettings
)
