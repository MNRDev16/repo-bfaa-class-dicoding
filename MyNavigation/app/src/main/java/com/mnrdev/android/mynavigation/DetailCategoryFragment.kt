package com.mnrdev.android.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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

        /* mengambil data dengan bundle
        val exName = arguments?.getString(CategoryFragment.EXTRA_NAME)
        val exDescription = arguments?.getLong(CategoryFragment.EXTRA_DESCRIPTION)
        */
        val exName = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val exDescription = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stock
        binding.tvCategoryName.text = exName
        binding.tvCategoryDescription.text = "stock : $exDescription"

        binding.btnHome.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        detailCategoryBinding = null
    }
}