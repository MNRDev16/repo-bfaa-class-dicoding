package com.mnrdev.android.submissionbfaa1

import android.content.Context
import android.content.res.TypedArray

object UserData {
    private lateinit var listUserId : Array<String>
    private lateinit var listUserName : Array<String>
    private lateinit var listFollower : Array<String>
    private lateinit var listFollowing : Array<String>
    private lateinit var listRepository : Array<String>
    private lateinit var listLocation : Array<String>
    private lateinit var listCompany : Array<String>
    private lateinit var listAvatar : TypedArray

    private fun userDataCreated(context : Context){
        listUserId = context.resources.getStringArray(R.array.username)
        listUserName = context.resources.getStringArray(R.array.name)
        listRepository = context.resources.getStringArray(R.array.repository)
        listLocation = context.resources.getStringArray(R.array.location)
        listFollowing = context.resources.getStringArray(R.array.following)
        listFollower = context.resources.getStringArray(R.array.followers)
        listCompany = context.resources.getStringArray(R.array.company)
        listAvatar = context.resources.obtainTypedArray(R.array.avatar)
    }

    fun getUserData(context: Context) : ArrayList<User>{
        var userDataArray : ArrayList<User> = ArrayList()
        userDataCreated(context)


        for(it in listUserId.indices){
            val user = User(
                userID = listUserId[it],
                userName = listUserName[it],
                follower = listFollower[it],
                following = listFollowing[it],
                repository = listRepository[it],
                location = listLocation[it],
                company = listCompany[it],
                avatar = listAvatar.getResourceId(it,0)
            )
            userDataArray.add(user)
        }
        return userDataArray
    }

}