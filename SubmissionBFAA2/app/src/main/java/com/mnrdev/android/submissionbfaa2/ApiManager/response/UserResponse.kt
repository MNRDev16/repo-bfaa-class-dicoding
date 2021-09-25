package com.mnrdev.android.submissionbfaa2.ApiManager.response

import com.google.gson.annotations.SerializedName
import com.mnrdev.android.submissionbfaa2.ItemsItem

data class UserResponse(

	@field:SerializedName("Response")
	val response: List<ItemsItem?>? = null
)
