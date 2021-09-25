package com.mnrdev.android.submissionbfaa2.Fragment.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mnrdev.android.submissionbfaa2.Adapter.UserAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.R
import com.mnrdev.android.submissionbfaa2.databinding.FragmentFollowingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USERNAME = "username"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowingFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
    ): View? {
        followingBinding = FragmentFollowingBinding.inflate(inflater,container,false)
        return followingBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)

        viewModel.followingData.observe(this.viewLifecycleOwner,{
            setFollowingData(it.response)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(username != null) viewModel.getFollowingData(username!!)
        else viewModel.getFollowingData("\"\"")
    }

    private fun setFollowingData(userFollowers : List<ItemsItem>) {
        val adapter = UserAdapter(userFollowers)
        followingBinding.includeRecycleView.rvUserGithub.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment FollowingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                }
            }
    }
}