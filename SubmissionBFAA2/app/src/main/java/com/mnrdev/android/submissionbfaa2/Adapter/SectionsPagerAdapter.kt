package com.mnrdev.android.submissionbfaa2.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mnrdev.android.submissionbfaa2.Fragment.followers.FollowerFragment
import com.mnrdev.android.submissionbfaa2.Fragment.following.FollowingFragment

class SectionsPagerAdapter(activity : AppCompatActivity,var username : String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when (position){
             0 -> fragment = FollowingFragment.newInstance(username)
             1 -> fragment = FollowerFragment.newInstance(username)
        }
        return fragment as Fragment
    }

}
