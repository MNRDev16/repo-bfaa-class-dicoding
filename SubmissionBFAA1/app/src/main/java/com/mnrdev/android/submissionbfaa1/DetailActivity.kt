package com.mnrdev.android.submissionbfaa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mnrdev.android.submissionbfaa1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewBinding : ActivityDetailBinding

    companion object{
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)

        Glide.with(this)
            .load(user?.avatar)
            .apply(RequestOptions().override(100,100))
            .into(detailViewBinding.imageUser)
        detailViewBinding.apply {
            tvUserName.text = user?.userName
            tvCompany.text = user?.company
            tvLocation.text = user?.location
            tvNumFollower.text = user?.follower
            tvNumFollowing.text = user?.following
            tvNumRepository.text = user?.repository
        }
    }
}