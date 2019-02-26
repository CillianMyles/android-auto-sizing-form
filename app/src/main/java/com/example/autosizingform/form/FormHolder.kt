package com.example.autosizingform.form

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.form_list_item.view.input
import kotlinx.android.synthetic.main.form_list_item.view.remove

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.remove.visibility = View.INVISIBLE
        itemView.input.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                itemView.remove.visibility =
                        if (s?.toString().isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
            }
        })
    }

    fun show(value: String?) {
        itemView.input.setText(value)
    }

    fun extract(): String {
        return itemView.input.text?.toString() ?: ""
    }
}
