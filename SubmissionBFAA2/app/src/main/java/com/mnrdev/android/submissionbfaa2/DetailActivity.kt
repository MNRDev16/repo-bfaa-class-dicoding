package com.mnrdev.android.submissionbfaa2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mnrdev.android.submissionbfaa2.Adapter.SectionsPagerAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.DetailResponse
import com.mnrdev.android.submissionbfaa2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        username = DetailActivityArgs.fromBundle(intent.extras!!).username
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        viewModel.getDetailUser(username)

        viewModel.detailUser.observe(this,{detailUser ->
            setDetailData(detailUser)
        })
        createTabLayout()
        viewModel.isLoading.observe(this,{isLoading ->
            showLoading(isLoading)
        })

        viewModel.failureText.observe(this,{
            it.getContentIfNotHandled()?.let { text ->
                Snackbar.make(window.decorView.rootView,text,Snackbar.LENGTH_SHORT).show()
            }
        })

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = username
        }
    }

    private fun createTabLayout(){
        detailBinding.includeViewPager.apply {
            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailActivity,username)
            val viewPager : ViewPager2 = viewPager
            viewPager.adapter = sectionsPagerAdapter
            val tabLayout : TabLayout = tabs
            TabLayoutMediator(tabLayout,viewPager){tab,position ->
                tab.text = resources.getString(TAB_TITLE[position])
            }.attach()
        }
    }

    private fun setDetailData(userDetail : DetailResponse){
        detailBinding.includeDetail.apply {
            Glide.with(root)
                .load(userDetail.avatarUrl)
                .apply(RequestOptions().override(100,100))
                .into(imageUser)
            tvName.text = userDetail.name
            tvNumFollower.text = userDetail.followers.toString()
            tvNumFollowing.text = userDetail.following.toString()
            tvNumRepository.text = userDetail.publicRepos.toString()
            tvLocation.text = userDetail.location
            tvCompany.text = userDetail.company
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailBinding.progressBarDetail.visibility = View.VISIBLE
        } else {
            detailBinding.progressBarDetail.visibility = View.GONE
        }
    }

    companion object{

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }
}