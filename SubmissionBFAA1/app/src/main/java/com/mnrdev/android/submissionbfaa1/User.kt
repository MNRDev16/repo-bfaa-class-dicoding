package com.mnrdev.android.submissionbfaa1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val userID : String,
    val userName :String,
    val following : String,
    val follower : String,
    val repository : String,
    val location : String,
    val company : String,
    val avatar : Int
) : Parcelable