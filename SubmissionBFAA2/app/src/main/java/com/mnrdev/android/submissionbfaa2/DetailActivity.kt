package com.mnrdev.android.submissionbfaa2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mnrdev.android.submissionbfaa2.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        getDetailUser(username)
        createTabLayout()

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = username
        }
    }

    private fun createTabLayout(){
        detailBinding.includeViewPager.apply {
            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailActivity)
            val viewPager : ViewPager2 = viewPager
            viewPager.adapter = sectionsPagerAdapter
            val tabLayout : TabLayout = tabs
            TabLayoutMediator(tabLayout,viewPager){tabLayout,position ->
                tabLayout.text = resources.getString(TAB_TITLE[position])

            }.attach()
        }
    }
    private fun getDetailUser(username : String?){
        var id = "\"\""
        if(username != null && username.isNotEmpty()) id = username
        showLoading(true)
        val client = ApiConfig.getApiService().getDetail(id)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setDetailData(responseBody)
                    }
                } else {
                    Log.e(ControlsProviderService.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                showLoading(false)
                Log.e(ControlsProviderService.TAG, "onFailure: ${t.message}")
            }
        })
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
        const val EXTRA_USERNAME = "extra username"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }
}