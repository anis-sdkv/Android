package com.example.hw3.tools

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.widget.EditText
import kotlin.math.min


class NumberFieldTextWatcher(private val editText: EditText, private val dependentET: EditText) : TextWatcher {
    private var _ignore = false

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (_ignore) return

        var cleanNumbers = getNumbers(s.toString())
        var formattedNumbers = "+7 (9"

        if (cleanNumbers.length > 1) {
            formattedNumbers += cleanNumbers.substring(
                2,
                min(cleanNumbers.length, 4)
            )
        }
        if (cleanNumbers.length >= 5) {
            formattedNumbers += ") - " + cleanNumbers.substring(
                4,
                min(cleanNumbers.length, 7)
            )
        }
        if (cleanNumbers.length >= 8) {
            formattedNumbers += " - " + cleanNumbers.substring(
                7,
                min(cleanNumbers.length, 9)
            )
        }
        if (cleanNumbers.length >= 10) {
            formattedNumbers += " - " + cleanNumbers.substring(
                9,
                min(cleanNumbers.length, 11)
            )
        }

        _ignore = true
        editText.setText(formattedNumbers)
        Selection.setSelection(editText.text, editText.text!!.length)
        _ignore = false

        dependentET.isEnabled = formattedNumbers.length == 24
    }

    private fun getNumbers(input: String): String {
        return Regex("""\D""").replace(input, "")
    }
}

//                    Старая реализация, решил переделать
//
//                    if (before < count)
//                        when (s?.length) {
//                            6 -> insertSymbols(etNumber, s.toString(), ")-")
//                            11 -> insertSymbols(etNumber, s.toString(), "-")
//                            14 -> insertSymbols(etNumber, s.toString(), "-")
//                        }
//                    else
//                        when (s?.length) {
//                            7 -> removeSymbols(etNumber, s.toString(), 2)
//                            11 -> removeSymbols(etNumber, s.toString(), 1)
//                            14 -> removeSymbols(etNumber, s.toString(), 1)
//                        }

//                    var inputNumbers = getNumbers(s.toString())
//                    var formattedNumber = ""
//                    if (inputNumbers.length == 0)
//                        formattedNumber = "+7 "
//                    etNumber.setText(formattedNumber)
//                    Selection.setSelection(etNumber.text, etNumber.text!!.length)
//
//                    tvTest.setText(inputNumbers)
//
//                }

//
//        private fun insertSymbols(et: EditText, text: String, symbols: String) {
//            et.setText(text + symbols)
//            Selection.setSelection(et.text, text.length + symbols.length)
//        }
//
//        private fun removeSymbols(et: EditText, text: String, count: Int) {
//            if (count > text.length) throw Exception()
//            et.setText(text.substring(0, text.length - count))
//            Selection.setSelection(et.text, text.length - count)
//        }
//    })
