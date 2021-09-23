package com.mnrdev.android.submissionbfaa2

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("Response")
	val response: List<ItemsItem?>? = null
)
