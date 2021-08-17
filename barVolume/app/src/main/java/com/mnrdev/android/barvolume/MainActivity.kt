package com.mnrdev.android.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtPan : EditText
    private lateinit var edtLeb : EditText
    private lateinit var edtTin : EditText
    private lateinit var btnHit : Button
    private lateinit var textHas : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPan = findViewById(R.id.edText_long)
        edtLeb = findViewById(R.id.edText_height)
        edtTin = findViewById(R.id.edText_tinggi)
        btnHit = findViewById(R.id.btn_countVolume)
        textHas = findViewById(R.id.text_result)

        btnHit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}