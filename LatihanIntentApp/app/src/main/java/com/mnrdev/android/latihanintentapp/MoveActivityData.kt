package com.mnrdev.android.latihanintentapp

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveActivityData : AppCompatActivity() {

    companion object{
        const val STATE_NAME = "state_name"
        const val STATE_AGE = "state_age"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_data)

        val tvReceived : TextView = findViewById(R.id.tv_received_data)

        var name = intent.getStringExtra(STATE_NAME)
        val age = intent.getIntExtra(STATE_AGE,0)

        val textReceived = "Name : $name and Age : $age"
        tvReceived.text = textReceived

    }
}