package com.mnrdev.android.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetailCategoryFragment : Fragment() {

    lateinit var tvCategoryName : TextView
    lateinit var tvCategoryDescription : TextView
    lateinit var btnProfil : Button
    lateinit var btnShowDialog : Button

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
    }
    
}