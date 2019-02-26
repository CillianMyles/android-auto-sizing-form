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
import kotlinx.android.synthetic.main.activity_form.toolbar
import kotlinx.android.synthetic.main.content_form.recycler

/**
 * Created by Cillian Myles on 25/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class FormActivity : AppCompatActivity() {

    companion object {
        private val TAG = FormActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_form)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler.apply {
            layoutManager = LinearLayoutManager(this@FormActivity)
            adapter = FormAdapter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val list = List(recycler.childCount) {
                    recycler.findViewHolderForLayoutPosition(it) as FormHolder
                }.map { it.extract() }
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
}
