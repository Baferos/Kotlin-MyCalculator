package com.baf.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        var tvValue = tvInput?.text.toString()
        if (tvValue.startsWith("0")){
            tvInput?.text = ""}
        tvInput?.append((view as TextView).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput?.text = "0"
    }

    fun onDot(view: View){
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if (!lastNumeric) {
            return
        }
        var tvValue = tvInput?.text.toString()

            var prefix = ""
            if (tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            if (tvValue.contains("-")){
                try {
                    val splitValue = tvValue.split("-")
                    val one : String = prefix + splitValue[0]
                    tvInput?.text = (one.toDouble() - splitValue[1].toDouble()).toString()
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
            if (tvValue.contains("+")){
                try {
                    val splitValue = tvValue.split("+")
                    val one : String = prefix + splitValue[0]
                    tvInput?.text = (one.toDouble() + splitValue[1].toDouble()).toString()
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
            if (tvValue.contains("*")){
                try {
                    val splitValue = tvValue.split("*")
                    val one : String = prefix + splitValue[0]
                    tvInput?.text = (one.toDouble() * splitValue[1].toDouble()).toString()
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
            if (tvValue.contains("/")){
                try {
                    val splitValue = tvValue.split("/")
                    val one : String = prefix + splitValue[0]
                    if (splitValue[1].toDouble() == 0.0){
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    tvInput?.text = (one.toDouble() / splitValue[1].toDouble()).toString()
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun onZero(view: View){
        var tvValue = tvInput?.text.toString()
        if (tvValue.startsWith("0")){
            tvInput?.text = ""}
        tvInput?.append((view as TextView).text)
        lastNumeric = true
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as TextView).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        } else {
            value.contains("-") ||
            value.contains("+") ||
            value.contains("*") ||
            value.contains("/")
        }
    }

}