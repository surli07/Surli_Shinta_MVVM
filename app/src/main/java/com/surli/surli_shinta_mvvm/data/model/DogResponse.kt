package com.surli.surli_shinta_mvvm.data.model

import com.google.gson.annotations.SerializedName

class DogResponse :  ArrayList<DogData>()


data class DogData(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)