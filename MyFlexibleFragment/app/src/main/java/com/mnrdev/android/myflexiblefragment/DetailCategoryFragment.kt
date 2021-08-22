package com.mnrdev.android.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailCategoryFragment : Fragment() {

    lateinit var tvCategoryName : TextView
    lateinit var tvCategoryDescription : TextView
    lateinit var btnProfil : Button
    lateinit var btnShowDialog : Button
    var description : String? = null

    companion object{
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        tvCategoryName = view.findViewById(R.id.tv_category_name)
        btnProfil = view.findViewById(R.id.btn_profil)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        btnShowDialog.setOnClickListener {
            val mOptionDialogFragment = OptionDialogFragment()

            val mFragment = childFragmentManager
            mOptionDialogFragment.show(mFragment,OptionDialogFragment::class.java.simpleName)
        }
        btnProfil.setOnClickListener {
            val profilIntent = Intent(activity,ProfilActivity::class.java)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(savedInstanceState != null){
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }
        if (arguments != null){
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDescription.text = description
        }
    }

    internal var optionDialogFragment : OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener{

        override fun onOptionChosen(text: String?) {
            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
        }
    }
}