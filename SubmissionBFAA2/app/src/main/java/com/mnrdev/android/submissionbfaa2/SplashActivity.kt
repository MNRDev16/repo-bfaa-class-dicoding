package com.mnrdev.android.submissionbfaa2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mnrdev.android.submissionbfaa2.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewBinding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashViewBinding.root)

        supportActionBar?.hide()

        Handler(mainLooper).postDelayed({
            val home = Intent(this,MainActivity::class.java)
            startActivity(home)
            finish()
        },4000)
    }
}