package com.example.autosizingform.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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
                // TODO: save !!!
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
