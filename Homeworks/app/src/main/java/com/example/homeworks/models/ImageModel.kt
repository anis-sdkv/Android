package com.example.homeworks.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ImageModel(
    val url: String,
    var status: ImageStatus = ImageStatus.NOT_LOADED
) : Parcelable

