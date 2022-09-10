package dev.conslate.workoutlog.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name")var lastName: String,
    var email: String,
    @SerializedName("phone_number") var phone_number: String,
    var userid:String,

    )
