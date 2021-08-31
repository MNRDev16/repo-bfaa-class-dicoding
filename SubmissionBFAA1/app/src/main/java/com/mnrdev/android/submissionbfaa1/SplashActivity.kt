package com.mnrdev.android.submissionbfaa1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable(){
            fun run(){
                val home = Intent(this,MainActivity::class.java)
                startActivity(home)
                finish()
            }
        },4000)
    }
}