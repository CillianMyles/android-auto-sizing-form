package com.example.autosizingform.form

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.autosizingform.R

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormAdapter : RecyclerView.Adapter<FormHolder>() {

    companion object {
        private const val MIN_SIZE = 1
        private const val EMPTY = ""
    }

    var list: List<String> = emptyList()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FormHolder {
        val inflated = LayoutInflater.from(parent.context)
                .inflate(R.layout.form_list_item, parent, false)
        val holder = FormHolder(inflated)
        inflated.tag = holder
        return holder
    }

    override fun onBindViewHolder(holder: FormHolder, position: Int) {
        holder.show(list[position])
    }

    private fun emptyList(size: Int = MIN_SIZE): List<String> {
        return List(size) { EMPTY }
    }
}
