package com.mnrdev.android.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

    companion object{
        private const val STATE_RESULT = "state_result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPan = findViewById(R.id.edText_long)
        edtLeb = findViewById(R.id.edText_height)
        edtTin = findViewById(R.id.edText_tinggi)
        btnHit = findViewById(R.id.btn_countVolume)
        textHas = findViewById(R.id.text_result)

        btnHit.setOnClickListener(this)
        if(savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            textHas.text = result
        }

    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_countVolume){
            val inputPanjang = edtPan.text.toString().trim()
            val inputlebar = edtLeb.text.toString().trim()
            val inputTinggi = edtTin.text.toString().trim()

            var isEmptyField = false
            if(inputPanjang.isEmpty()){
                isEmptyField = true
                edtPan.error = "This Field can not empty"
            }
            if(inputlebar.isEmpty()){
                isEmptyField = true
                edtLeb.error = "This Field can not empty"
            }
            if(inputTinggi.isEmpty()){
                isEmptyField = true
                edtTin.error = "This Field can not empty"
            }
            if(!isEmptyField) {
                val resultVolume =
                    inputPanjang.toDouble() * inputlebar.toDouble() * inputTinggi.toDouble()
                textHas.text = resultVolume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,textHas.text.toString())
    }
}