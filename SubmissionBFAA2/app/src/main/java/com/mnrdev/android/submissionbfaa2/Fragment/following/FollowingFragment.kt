package com.mnrdev.android.submissionbfaa2.Fragment.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnrdev.android.submissionbfaa2.Adapter.UserAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.databinding.FragmentFollowingBinding

private const val USERNAME = "username"

class FollowingFragment : Fragment() {
    private var username: String? = null
    private lateinit var viewModel: FollowingViewModel
    private lateinit var followingBinding: FragmentFollowingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        followingBinding = FragmentFollowingBinding.inflate(inflater,container,false)
        return followingBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)

        viewModel.followingData.observe(this.viewLifecycleOwner,{
            setFollowingData(it)
        })
        viewModel.login.observe(this.viewLifecycleOwner,{
            viewModel.getFollowingData(it)
        })
        viewModel.failed.observe(this.viewLifecycleOwner,{
            showFailedMessage(it,viewModel.message)
        })
        username?.let { viewModel.setUserLogin(it) }
    }

    private fun setFollowingData(userFollowing : List<ItemsItem>) {
        followingBinding.includeRecycleView.rvUserGithub.layoutManager = LinearLayoutManager(this.context)
        val adapter = UserAdapter(userFollowing)
        followingBinding.includeRecycleView.rvUserGithub.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun OnItemClicked(username: String) {
            }
        })
    }
    private fun showFailedMessage(failed : Boolean,message : String){
        if(failed){
            followingBinding.includeRecycleView.root.visibility = View.GONE
            followingBinding.tvFailed.visibility = View.VISIBLE
            followingBinding.tvFailed.text = message
        }else{
            followingBinding.includeRecycleView.root.visibility = View.VISIBLE
            followingBinding.tvFailed.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                }
            }
    }
}