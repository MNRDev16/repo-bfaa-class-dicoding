package com.mnrdev.android.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {

    private lateinit var rgOption :RadioGroup
    private lateinit var rbSaf : RadioButton
    private lateinit var rbMou : RadioButton
    private lateinit var rbDav : RadioButton
    private lateinit var rbLvg : RadioButton
    private lateinit var btnChoose : Button
    private lateinit var btnClose : Button
    private var optionDialogListener : OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChosen(text : String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rgOption = view.findViewById(R.id.rg_option)
        rbDav = view.findViewById(R.id.rb_dav)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbSaf = view.findViewById(R.id.rb_saf)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOption.checkedRadioButtonId
            if(checkedRadioButtonId != -1){
                var coach : String? = null
                when (checkedRadioButtonId){
                    R.id.rb_saf -> coach = rbSaf.text.toString().trim()
                    R.id.rb_mou -> coach = rbMou.text.toString().trim()
                    R.id.rb_lvg -> coach = rbLvg.text.toString().trim()
                    R.id.rb_dav -> coach = rbDav.text.toString().trim()
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is DetailCategoryFragment){
            val detailCategoryFragment = DetailCategoryFragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }
}