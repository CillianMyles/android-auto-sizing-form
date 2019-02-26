package com.example.autosizingform.form

/**
 * Created by Cillian Myles on 26/02/2019.
 * Copyright (c) 2019 Cillian Myles. All rights reserved.
 */

object StringExt {

    fun String?.notNullOrEmpty(): Boolean = this?.isNotEmpty() ?: false

}
