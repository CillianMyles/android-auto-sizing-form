package com.example.autosizingform.form

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.form_list_item.view.input
import kotlinx.android.synthetic.main.form_list_item.view.remove

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormHolder(itemView: View) : RecyclerView.ViewHolder(itemView), InputListener, RemoveListener {

    var listener: FormListener? = null
    private var paused: Boolean = true

    private val listening: Boolean
        get() = listener != null && !paused

    init {
        itemView.remove.visibility = View.INVISIBLE
        itemView.input.text = null
        itemView.remove.setOnClickListener(RemoveWatcher(this))
        itemView.input.addTextChangedListener(InputWatcher(this))
    }

    fun show(value: String?) {
        paused = true
        itemView.input.setText(value)
        paused = false
    }

    fun extract(): String {
        return itemView.input.text?.toString() ?: ""
    }

    override fun onEmpty() {
        itemView.remove.visibility = View.INVISIBLE
        if (listening) {
            listener!!.onItemCleared(layoutPosition)
        }
    }

    override fun onNonEmpty() {
        itemView.remove.visibility = View.VISIBLE
        if (listening) {
            listener!!.onNewItemNeeded(layoutPosition)
        }
    }

    override fun onRemove() {
        itemView.input.text = null
    }
}
