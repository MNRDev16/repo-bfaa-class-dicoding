package com.mnrdev.android.submissionbfaa2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mnrdev.android.submissionbfaa2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)


    }
}