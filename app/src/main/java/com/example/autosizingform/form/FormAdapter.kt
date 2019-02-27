package com.example.autosizingform.form

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.autosizingform.R

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormAdapter(private val listener: FormListener)
    : RecyclerView.Adapter<FormHolder>(), FormListener {

    companion object {
        private val TAG = FormAdapter::class.java.simpleName
        private const val EMPTY = ""
        private const val MIN_SIZE = FormActivity.MIN_SIZE
        private const val MAX_SIZE = FormActivity.MAX_SIZE
    }

    private var list: MutableList<String> = listOfEmpties()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FormHolder {
        val inflated = LayoutInflater.from(parent.context)
                .inflate(R.layout.form_list_item, parent, false)
        val holder = FormHolder(inflated)
        inflated.tag = holder
        return holder
    }

    override fun onBindViewHolder(holder: FormHolder, position: Int) {
        holder.listener = listener
        holder.show(list[position])
    }

    override fun onItemCleared(layoutPosition: Int) {
        Log.e(TAG, "onItemCleared - layoutPosition: $layoutPosition") // TODO: remove
        if (size > MIN_SIZE) {
            list.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun onItemRemoved(layoutPosition: Int) {
        Log.e(TAG, "onItemRemoved - layoutPosition: $layoutPosition") // TODO: remove
        if (size > MIN_SIZE) {
            list.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun onNewItemNeeded(generatedByPosition: Int) {
        Log.e(TAG, "onNewItemNeeded - generatedByPosition: $generatedByPosition") // TODO: remove
        if (size < MAX_SIZE) {
            val newSize = size + 1
            val newLastIndex = newSize - 1
            list.add(EMPTY)
            notifyItemInserted(newLastIndex)
        }
    }

    /*
     * Private / Internal
     */

    private val size
        get() = list.size

    private val lastIndex
        get() = size - 1

    private fun setListImpl(list: MutableList<String>?, maxSize: Int = MAX_SIZE) {
        this.list =
                if (list == null || list.isEmpty()) {
                    listOfEmpties()
                } else if (list.size > maxSize) {
                    list.subList(0, maxSize)
                } else {
                    list
                }
    }

    private fun listOfEmpties(size: Int = MIN_SIZE): MutableList<String> {
        return MutableList(size) { EMPTY }
    }
}
