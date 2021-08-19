package com.mnrdev.android.latihanintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult :TextView

    companion object{
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity : Button = findViewById(R.id.btn_moveActivity)
        val btnMoveData : Button = findViewById(R.id.btn_move_with_data)
        val btnMoveObject : Button = findViewById(R.id.btn_move_with_object)
        val btnDial : Button = findViewById(R.id.btn_dialphone)
        val btnResultFromActivity : Button = findViewById(R.id.btn_move_for_result)

        tvResult = findViewById(R.id.tv_result_for_activity)

        btnMoveActivity.setOnClickListener(this)
        btnMoveData.setOnClickListener(this)
        btnMoveObject.setOnClickListener(this)
        btnDial.setOnClickListener(this)
        btnResultFromActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_moveActivity ->{
                val moveIntent = Intent(this@MainActivity,MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_with_data -> {
                val moveDataIntent = Intent(this,MoveActivityData::class.java)
                moveDataIntent.putExtra(MoveActivityData.STATE_NAME,"MNR Dev")
                moveDataIntent.putExtra(MoveActivityData.STATE_AGE,1)
                startActivity(moveDataIntent)
            }
            R.id.btn_move_with_object -> {
                val dataObject = Person(
                    "MNR Dev", 1,"Kediri", "mnrdev@gmail.com",
                )
                val moveObjectIntent = Intent(this,MoveActivityWithObject::class.java)
                moveObjectIntent.putExtra(MoveActivityWithObject.EXTRA_OBJECT,dataObject)
                startActivity(moveObjectIntent)
            }
            R.id.btn_dialphone -> {
                val phoneNumber = "085234729654"
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }
            R.id.btn_move_for_result -> {
                val moveResult = Intent(this,MoveForResultActivity::class.java)
                startActivityForResult(moveResult, REQUEST_CODE)
            }
        }
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if(requestCode == REQUEST_CODE){
                if(resultCode == MoveForResultActivity.RESULT_CODE){
                    val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_NUMBER,0)
                    tvResult.text = "Hasil : $selectedValue"
                }
            }
        }
    }
}