package com.mnrdev.android.submissionbfaa2.Fragment.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnrdev.android.submissionbfaa2.Adapter.UserAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.Fragment.main.MainFragmentDirections
import com.mnrdev.android.submissionbfaa2.databinding.FragmentFollowerBinding

private const val USERNAME = "username"

class FollowerFragment : Fragment() {
    private var username: String? = null
    private lateinit var viewModel: FollowerViewModel
    private lateinit var followersMainBinding : FragmentFollowerBinding

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
        followersMainBinding = FragmentFollowerBinding.inflate(inflater,container,false)
        return followersMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)
        username?.let { viewModel.setUserLogin(it) }
        viewModel.login.observe(this.viewLifecycleOwner,{
            viewModel.getFollowingData(it)
        })
        viewModel.followersData.observe(this.viewLifecycleOwner,{ data ->
            setFollowersData(data)
        })
        viewModel.loading.observe(this.viewLifecycleOwner,{
            showLoading(it)
        })
        viewModel.failed.observe(this.viewLifecycleOwner,{
            showMessage(it,viewModel.message)
        })
    }

    private fun showMessage(failed : Boolean,message : String){
        if(failed){
            followersMainBinding.includeRecycleView.root.visibility = View.GONE
            followersMainBinding.tvFailed.text = message
            followersMainBinding.tvFailed.visibility = View.VISIBLE
        }else{
            followersMainBinding.includeRecycleView.root.visibility = View.VISIBLE
            followersMainBinding.tvFailed.visibility = View.GONE
        }
    }
    private fun setFollowersData(userFollowers : List<ItemsItem>){
        val adapter = UserAdapter(userFollowers)
        followersMainBinding.includeRecycleView.rvUserGithub.layoutManager = LinearLayoutManager(this.context)
        followersMainBinding.includeRecycleView.rvUserGithub.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun OnItemClicked(username: String) {
            }
        })
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            followersMainBinding.progressBar.visibility = View.VISIBLE
        } else {
            followersMainBinding.progressBar.visibility = View.GONE
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(username: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                }
            }
    }
}