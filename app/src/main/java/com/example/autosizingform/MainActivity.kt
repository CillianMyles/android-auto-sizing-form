package com.example.autosizingform

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.autosizingform.form.FormActivity
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.decrement
import kotlinx.android.synthetic.main.content_main.increment
import kotlinx.android.synthetic.main.content_main.max_form_fields

/**
 * Created by Cillian Myles on 25/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class MainActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MAX_SIZE = "max_size"
        private const val MAX_SIZE_DEFAULT = 3
        private const val MAX_SIZE_MIN = 1
        private const val MAX_SIZE_MAX = 9
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val maxSize = savedInstanceState?.getInt(EXTRA_MAX_SIZE)
                ?: intent?.getIntExtra(EXTRA_MAX_SIZE, MAX_SIZE_DEFAULT)
                ?: MAX_SIZE_DEFAULT

        setMaxValue(maxSize)

        increment.setOnClickListener { max_form_fields.increment() }

        decrement.setOnClickListener { max_form_fields.decrement() }

        fab.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt(EXTRA_MAX_SIZE, getMaxValue())
    }

    /*
     * Private / Internal
     */

    private fun setMaxValue(value: Int) {
        max_form_fields.setIntValue(value)
    }

    private fun getMaxValue(): Int {
        return max_form_fields.getIntValue()
    }

    private fun TextView.setIntValue(value: Int) {
        this.text = "$value"
    }

    private fun TextView.getIntValue(): Int {
        return this.text.toString().toInt()
    }

    private fun TextView.increment() {
        val size = this.getIntValue()
        if (size < MAX_SIZE_MAX) {
            this.setIntValue(size + 1)
        }
    }

    private fun TextView.decrement() {
        val size = this.getIntValue()
        if (size > MAX_SIZE_MIN) {
            this.setIntValue(size - 1)
        }
    }
}
