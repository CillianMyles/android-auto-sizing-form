package com.example.autosizingform.form

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.autosizingform.R

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormAdapter : RecyclerView.Adapter<FormHolder>(), FormListener {

    companion object {
        private const val EMPTY = ""
        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 3
    }

    private var list: MutableList<String> = emptyList()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FormHolder {
        val inflated = LayoutInflater.from(parent.context)
                .inflate(R.layout.form_list_item, parent, false)
        val holder = FormHolder(inflated)
        inflated.tag = holder
        return holder
    }

    override fun onBindViewHolder(holder: FormHolder, position: Int) {
        holder.listener = this
        holder.position = position
        holder.paused = true
        holder.show(list[position])
        holder.paused = false
    }

    override fun onItemCleared(layoutPosition: Int) {
        if (list.size > MIN_SIZE) {
            list.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun onItemRemoved(layoutPosition: Int) {
        if (list.size > MIN_SIZE) {
            list.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun onNewItemNeeded(generatedByPosition: Int) {
        if (list.size < MAX_SIZE) {
            val size = list.size
            val lastPosition = size - 1
            val newSize = size + 1
            val newLastPosition = newSize - 1
            list.add(EMPTY)
            notifyItemInserted(newLastPosition)
        }
    }

    /*
     * Private / Internal
     */

    private fun setListImpl(list: MutableList<String>?, maxSize: Int = MAX_SIZE) {
        this.list =
                if (list == null || list.isEmpty()) {
                    emptyList()
                } else if (list.size > maxSize) {
                    list.subList(0, maxSize)
                } else {
                    list
                }
    }

    private fun emptyList(size: Int = MIN_SIZE): MutableList<String> {
        return MutableList(size) { EMPTY }
    }
}
