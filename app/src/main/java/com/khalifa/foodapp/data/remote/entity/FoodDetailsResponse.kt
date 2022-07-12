package com.khalifa.foodapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class FoodDetailsResponse(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("response")
    val response: Response?
)

data class Meta(
    @SerializedName("code")
    val code: Int?
)

data class Response(

    @SerializedName("unsaturatedfat")
    val unSaturatedFat: Double?,

    @SerializedName("fiber")
    val fiber: Double?,

    @SerializedName("potassium")
    val potassium: Double?,

    @SerializedName("carbs")
    val carbs: Double?,

    @SerializedName("calories")
    val calories: Double?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("gramsperserving")
    val gramsPerServing: Double?,

    @SerializedName("saturatedfat")
    val saturatedFat: Double?,

    @SerializedName("sodium")
    val sodium: Double?,

    @SerializedName("protein")
    val protein: Double?,

    @SerializedName("fat")
    val fat: Double?,

    @SerializedName("cholesterol")
    val cholesterol: Double?,

    @SerializedName("pcstext")
    val pcsText: String?,

    @SerializedName("sugar")
    val sugar: Double?
)
