package com.mnrdev.android.myactionbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.mnrdev.android.myactionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_1 ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_container,MenuFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }
            R.id.menu_2 -> {
                val intent = Intent(this,MenuActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }
}