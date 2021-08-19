package com.mnrdev.android.latihanintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity : Button = findViewById(R.id.btn_moveActivity)
        val btnMoveData : Button = findViewById(R.id.btn_move_with_data)
        val btnMoveObject : Button = findViewById(R.id.btn_move_with_object)

        btnMoveActivity.setOnClickListener(this)
        btnMoveData.setOnClickListener(this)
        btnMoveObject.setOnClickListener(this)
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
        }
    }
}