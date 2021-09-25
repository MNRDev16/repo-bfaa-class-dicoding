package com.mnrdev.android.submissionbfaa2.Fragment.main

import android.app.SearchManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.mnrdev.android.submissionbfaa2.Adapter.UserAdapter
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.MainActivity
import com.mnrdev.android.submissionbfaa2.R
import com.mnrdev.android.submissionbfaa2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentViewBinding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        mainFragmentViewBinding = MainFragmentBinding.inflate(inflater,container,false)
        return mainFragmentViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.searchData.observe(this.viewLifecycleOwner,{
            setSearchData(it.items)
        })
        viewModel.loading.observe(this.viewLifecycleOwner,{
            showLoading(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu,menu)

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        //val seacrh =
        //    (context as MainActivity).supportActionBar?.themedContext?.let { SearchView(it) }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getSearchData(query)
                }
                //Toast.makeText(this@MainActivity,query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.getSearchData(newText)
                }
                return true
            }

        })
    }

    private fun setSearchData(searchUsers: List<ItemsItem>) {
        val adapter = UserAdapter(searchUsers)
        mainFragmentViewBinding.includeRecycleView.rvUserGithub.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mainFragmentViewBinding.progressBar.visibility = View.VISIBLE
        } else {
            mainFragmentViewBinding.progressBar.visibility = View.GONE
        }
    }
}