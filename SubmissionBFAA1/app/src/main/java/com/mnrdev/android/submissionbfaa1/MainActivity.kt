package com.mnrdev.android.submissionbfaa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnrdev.android.submissionbfaa1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainVewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainVewBinding.root)

        val userAdapter = UserAdapter()
        userAdapter.setAllData(UserData.getUserData(this))

        mainVewBinding.apply {
            rvUserGithub.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserGithub.adapter = userAdapter
        }
    }
}