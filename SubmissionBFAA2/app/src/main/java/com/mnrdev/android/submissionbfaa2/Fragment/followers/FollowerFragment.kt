package com.mnrdev.android.submissionbfaa2.Fragment.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mnrdev.android.submissionbfaa2.Adapter.UserAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.databinding.FragmentFollowerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USERNAME = "username"

class FollowerFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        followersMainBinding = FragmentFollowerBinding.inflate(inflater,container,false)
        return followersMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)

        viewModel.followersData.observe(this.viewLifecycleOwner,{
            setFollowersData(it.response)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(username != null) viewModel.getFollowingData(username!!)
        else viewModel.getFollowingData("\"\"")
    }

    private fun setFollowersData(userFollowers : List<ItemsItem>){
        val adapter = UserAdapter(userFollowers)
        followersMainBinding.includeRecycleView.rvUserGithub.adapter = adapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FollowerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(username: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                }
            }
    }
}