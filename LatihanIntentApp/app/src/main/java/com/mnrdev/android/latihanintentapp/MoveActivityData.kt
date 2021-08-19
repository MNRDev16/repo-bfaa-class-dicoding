package com.mnrdev.android.latihanintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoveActivityData : AppCompatActivity() {

    companion object{
        const val STATE_NAME = "state_name"
        const val STATE_AGE = "state_age"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_data)
    }
}