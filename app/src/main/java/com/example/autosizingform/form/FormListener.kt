package com.example.autosizingform.form

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

interface FormListener {

    fun onItemCleared(layoutPosition: Int)

    fun onItemRemoved(layoutPosition: Int)

    fun onNewItemNeeded(generatedByPosition: Int)
}
