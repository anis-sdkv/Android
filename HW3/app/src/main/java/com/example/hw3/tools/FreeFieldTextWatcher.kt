package com.example.hw3.tools

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class FreeFieldTextWatcher(private val editText: EditText, private val dependentBtn:Button) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        p0?.let {
            dependentBtn.isEnabled = it.toString().trim().length >= 5
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}