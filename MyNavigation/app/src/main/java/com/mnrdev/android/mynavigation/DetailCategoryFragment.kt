package com.mnrdev.android.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mnrdev.android.mynavigation.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {

    private var detailCategoryBinding :FragmentDetailCategoryBinding? = null
    private val binding get() = detailCategoryBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailCategoryBinding = FragmentDetailCategoryBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exName = arguments?.getString(CategoryFragment.EXTRA_NAME)
        val exDescription = arguments?.getLong(CategoryFragment.EXTRA_DESCRIPTION)

        binding.tvCategoryName.text = exName
        binding.tvCategoryDescription.text = "stock : ${exDescription}"

    }

    override fun onDestroy() {
        super.onDestroy()
        detailCategoryBinding = null
    }
}