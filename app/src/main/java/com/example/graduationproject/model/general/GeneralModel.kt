package com.example.graduationproject.model.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GeneralModel(
    val title: String,
    val description: String,
    val category: String,
    val country: String,
    val city: String,
    val images: List<GeneralItem>
):Parcelable


