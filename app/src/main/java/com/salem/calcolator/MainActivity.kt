package com.salem.calcolator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNum : Double = 0.0
    var currentOp : Operation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCallBacks()
    }
    private fun addCallBacks() {
        c.setOnClickListener {
            clearDigit()
        }
        add.setOnClickListener {
            operation(Operation.Add)
        }
        sub.setOnClickListener {
            operation(Operation.Sub)
        }
        mul.setOnClickListener {
            operation(Operation.Mul)
        }
        div.setOnClickListener {
            operation(Operation.Div)
        }
        percentage.setOnClickListener {
            operation(Operation.Percent)
        }
        delete.setOnClickListener {
            var perdeleted = equation.text.toString()
            var deleted = perdeleted.dropLast(1)
            equation.text = deleted.toString()
        }
        equal.setOnClickListener {
            val resault = doCurrOp()
            equation.text = resault.toString()
        }
    }
    fun doCurrOp(): Double{
        val finalNum = equation.text.toString().toDouble()
        return when(currentOp){
            Operation.Add -> lastNum + finalNum
            Operation.Sub -> lastNum - finalNum
            Operation.Mul -> lastNum * finalNum
            Operation.Div -> lastNum / finalNum
            Operation.Percent -> lastNum % finalNum
            null -> 0.0
        }
    }
    fun operation(operation: Operation){
        lastNum = equation.text.toString().toDouble()
        clearDigit()
        currentOp = operation
    }

    fun clearDigit(){
        equation.text=""
    }
    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigit =  equation.text.toString()
        val newText =oldDigit+newDigit
        equation.text = newText
    }
}