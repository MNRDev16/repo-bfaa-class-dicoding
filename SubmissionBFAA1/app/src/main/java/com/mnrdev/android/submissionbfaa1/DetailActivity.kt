package com.mnrdev.android.submissionbfaa1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        detailViewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailViewBinding.root)

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
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = user?.userID
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_share){
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            var shareBody =" Your body"
            var shareSub = "Your Sub"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody)
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareSub)
            startActivity(Intent.createChooser(shareIntent,"Sahe using"))
        }
        return super.onOptionsItemSelected(item)
    }
}