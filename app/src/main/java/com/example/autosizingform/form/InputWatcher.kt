package com.example.autosizingform.form

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class InputWatcher(private val listener: InputListener) : TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (s?.toString().isNullOrEmpty()) {
            listener.onEmpty()
        } else {
            listener.onNonEmpty()
        }
    }
}
