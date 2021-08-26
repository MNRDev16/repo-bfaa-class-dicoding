package com.mnrdev.android.submissionbfaa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mnrdev.android.submissionbfaa1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainVewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainVewBinding.root)



    }
}