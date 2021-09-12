package com.mnrdev.android.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mnrdev.android.mynavigation.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var categoryFragmentBinding : FragmentCategoryBinding? = null
    private val binding get() = categoryFragmentBinding!!

    companion object{
        val EXTRA_NAME = "extra name"
        val EXTRA_DESCRIPTION = "extra description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        categoryFragmentBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDetailCategory.setOnClickListener { view ->
            val mBundle = Bundle()
            mBundle.putString(EXTRA_NAME,"Lifestyle")
            mBundle.putLong(EXTRA_DESCRIPTION,7)
            view.findNavController().navigate(R.id.action_categoryFragment_to_detailCategoryFragment,mBundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        categoryFragmentBinding = null
    }

}