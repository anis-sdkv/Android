package com.example.homeworks.Models

import android.os.Parcel
import android.os.Parcelable

data class PlantModel(
    val plantName: String?,
    val botanicalName: String?,
    val plantType: String?,
    val imgUrl: String?,
    val species: String?,
    val otherNames: String?,
    val description: String?,
    var visited: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(plantName)
        parcel.writeString(botanicalName)
        parcel.writeString(plantType)
        parcel.writeString(imgUrl)
        parcel.writeString(species)
        parcel.writeString(otherNames)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlantModel> {
        override fun createFromParcel(parcel: Parcel): PlantModel {
            return PlantModel(parcel)
        }

        override fun newArray(size: Int): Array<PlantModel?> {
            return arrayOfNulls(size)
        }

        const val PLANT_KEY = "PLANT_KEY"
    }
}
