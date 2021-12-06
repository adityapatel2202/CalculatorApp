package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.mariuszgromada.math.mxparser.*


class MainActivity : AppCompatActivity() {
    private var display: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.input)
        with(display) {
            this?.setShowSoftInputOnFocus(false)
            this?.setOnClickListener(View.OnClickListener {
                if (getString(R.string.display).equals(
                        getText().toString()
                    )
                ) setText("")
            })
        }
    }

    private fun upadateText(strToAdd: String) {
        val oldStr = display!!.text.toString()
        val cursorPos = display!!.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        if (getString(R.string.display).equals(display!!.text.toString())) {
            display!!.setText(strToAdd)
            display!!.setSelection(cursorPos + 1)
        } else {
            display!!.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
            display!!.setSelection(cursorPos + 1)
        }
    }

    fun zero(view: View?) {
        upadateText("0")
    }

    fun one(view: View?) {
        upadateText("1")
    }

    fun two(view: View?) {
        upadateText("2")
    }

    fun three(view: View?) {
        upadateText("3")
    }

    fun four(view: View?) {
        upadateText("4")
    }

    fun five(view: View?) {
        upadateText("5")
    }

    fun six(view: View?) {
        upadateText("6")
    }

    fun seven(view: View?) {
        upadateText("7")
    }

    fun eight(view: View?) {
        upadateText("8")
    }

    fun nine(view: View?) {
        upadateText("9")
    }

    fun multiply(view: View?) {
        upadateText("×")
    }

    fun divide(view: View?) {
        upadateText("÷")
    }

    fun Subtract(view: View?) {
        upadateText("-")
    }

    fun add(view: View?) {
        upadateText("+")
    }

    fun clear(view: View?) {
        upadateText("")
    }

    fun parBTN(view: View?) {
        val cursorPos = display!!.selectionStart
        var openPar = 0
        var closedPar = 0
        val textLen = display!!.text.length
        for (i in 0 until cursorPos) {
            if (display!!.text.toString().substring(i, i + 1) == "(") {
                openPar += 1
            }
            if (display!!.text.toString().substring(i, i + 1) == ")") {
                closedPar += 1
            }
        }
        if (openPar == closedPar || display!!.text.toString()
                .substring(textLen - 1, textLen) == "("
        ) {
            upadateText("(")
            display!!.setSelection(cursorPos + 1)
        }
        if (closedPar < openPar && display!!.text.toString()
                .substring(textLen - 1, textLen) != "("
        ) {
            upadateText(")")
        }
        display!!.setSelection(cursorPos + 1)
    }

    fun expBTN(view: View?) {
        upadateText("^")
    }

    fun plusminus(view: View?) {
        upadateText("-")
    }

    fun point(view: View?) {
        upadateText(".")
    }

    fun equal(view: View?) {
        var userExp = display!!.text.toString()
        userExp = userExp.replace("÷".toRegex(), "/")
        userExp = userExp.replace("×".toRegex(), "*")
        val exp = Expression(userExp)
        val result: String = java.lang.String.valueOf(exp.calculate())
        display!!.setText(result)
        display!!.setSelection(result.length)
    }

    fun backspace(view: View?) {
        val cursorPos = display!!.selectionStart
        val textLen = display!!.text.length
        if (cursorPos != 0 && textLen != 0) {
            val selection = display!!.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            display!!.setText(selection)
            display!!.setSelection(cursorPos - 1)
        }
    }
}




