package com.mnrdev.android.latihanintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnPilih : Button
    private lateinit var rgNumber : RadioGroup

    companion object{
        const val RESULT_CODE = 110
        const val EXTRA_SELECTED_NUMBER = "extra_selected_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnPilih = findViewById(R.id.btn_choose_number)
        rgNumber = findViewById(R.id.rg_number)

        btnPilih.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_choose_number){
            if(rgNumber.checkedRadioButtonId > 0){
                var value = 0
                when (rgNumber.checkedRadioButtonId){
                    R.id.rb_50 -> value = 50

                    R.id.rb_100 -> value = 100

                    R.id.rb_150 -> value = 150

                    R.id.rb_200 -> value = 200
                }
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_NUMBER,value)
                setResult(RESULT_CODE,resultIntent)
                finish()
            }
        }
    }
}