package com.example.autosizingform.form

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.autosizingform.R
import com.example.autosizingform.form.StringExt.notNullOrEmpty
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.content_form.*

/**
 * Created by Cillian Myles on 25/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormActivity : AppCompatActivity(), FormListener {

    companion object {
        private val TAG = FormActivity::class.java.simpleName
        const val MIN_SIZE = 1
        const val MAX_SIZE = 3
    }

    private val layoutManager by lazy { LinearLayoutManager(this) }
    private val adapter by lazy { FormAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_form)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler.apply {
            layoutManager = this@FormActivity.layoutManager
            adapter = this@FormActivity.adapter
        }
    }

    override fun onItemCleared(layoutPosition: Int) {
        Log.e(TAG, "onItemCleared - layoutPosition: $layoutPosition") // TODO: remove
        onItemClearedImpl(layoutPosition)
    }

    override fun onItemRemoved(layoutPosition: Int) {
        Log.e(TAG, "onItemRemoved - layoutPosition: $layoutPosition") // TODO: remove
        onItemClearedImpl(layoutPosition)
    }

    override fun onNewItemNeeded(generatedByPosition: Int) {
        Log.e(TAG, "onNewItemNeeded - generatedByPosition: $generatedByPosition") // TODO: remove
        if (isEmpty(lastIndex)) return // Don't add any more
        adapter.onNewItemNeeded(generatedByPosition)
    }

    private fun onItemClearedImpl(layoutPosition: Int) {
        when {
            layoutPosition == lastIndex -> { // Last one
                // Don't remove the last one
            }
            size == MAX_SIZE -> { // All spots full
                // Remove current, add empty at end
                adapter.onItemCleared(layoutPosition)
                this.onNewItemNeeded(layoutPosition)
            }
            else -> adapter.onItemCleared(layoutPosition)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val list = extract()
                val printable = list.map { "\"$it\"" }
                val joined = TextUtils.join(", ", printable)
                val msg = "Saved: $joined"
                Log.d(TAG, msg)
                Snackbar.make(recycler, msg, Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
     * Private / Internal
     */

    private val size
        get() = recycler.childCount

    private val lastIndex
        get() = size - 1

    private fun isEmpty(position: Int): Boolean {
        return extract(position).isEmpty()
    }

    private fun isNonEmpty(position: Int): Boolean {
        return extract(position).notNullOrEmpty()
    }

    private fun extract(): List<String> {
        return List(recycler.childCount) { extract(it) }
    }

    private fun extract(position: Int): String {
        return holder(position).extract()
    }

    private fun holder(position: Int): FormHolder {
        return recycler.findViewHolderForLayoutPosition(position) as FormHolder
    }
}
