package com.example.autosizingform

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.autosizingform.form.FormActivity
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar

/**
 * Created by Cillian Myles on 25/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }
}
