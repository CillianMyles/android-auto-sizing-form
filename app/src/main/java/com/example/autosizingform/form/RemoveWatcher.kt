package com.example.autosizingform.form

import android.view.View

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

class RemoveWatcher(private val listener: RemoveListener) : View.OnClickListener {

    override fun onClick(v: View?) {
        listener.onRemove()
    }
}
