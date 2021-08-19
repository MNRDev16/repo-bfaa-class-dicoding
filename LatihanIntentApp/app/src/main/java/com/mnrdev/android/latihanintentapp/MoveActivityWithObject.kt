package com.mnrdev.android.latihanintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveActivityWithObject : AppCompatActivity() {

    companion object{
        const val EXTRA_OBJECT = "extra_object"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvReceiveObject : TextView = findViewById(R.id.tv_received_object)

        val receiveObject = intent.getParcelableExtra<Person>(EXTRA_OBJECT) as Person
        val text = "Nama : ${receiveObject.name.toString()} /n" +
                "Email : ${receiveObject.Email}/n" +
                "Age : ${receiveObject.age.toString()} /n" +
                "Location : ${receiveObject.location}"
        tvReceiveObject.text = text
    }
}