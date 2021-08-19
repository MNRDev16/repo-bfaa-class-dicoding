package com.mnrdev.android.latihanintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity() {
    private lateinit var btnPilih : Button
    private lateinit var rgNumber : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnPilih = findViewById(R.id.btn_choose_number)
        rgNumber = findViewById(R.id.rg_number)
        
    }
}