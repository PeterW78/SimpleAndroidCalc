package com.example.simplecalculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var sign : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.op)
        val calculate: Button = findViewById(R.id.compute)
        val result: TextView = findViewById(R.id.result)
        val firstNum: EditText = findViewById(R.id.firstNum)
        val secondNum: EditText = findViewById(R.id.secondNum)
        sign = "+"
        spinner.onItemSelectedListener = this

        // Example source:
        // https://developer.android.com/develop/ui/views/components/spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.op,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }



        calculate.setOnClickListener { view: View ->
            if (firstNum.text.toString() == "" || secondNum.text.toString() == "" || sign == "") {
                Toast.makeText(
                    this,
                    "MISSING VALUES",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var fNum = firstNum.text.toString().toDouble()
                var sNum = secondNum.text.toString().toDouble()
                if (sign == "/") {
                    if (sNum == 0.0) {
                        Toast.makeText(
                            this,
                            "DIVISION BY ZERO NOT ALLOWED",
                            Toast.LENGTH_LONG
                        ).show()
                    } else
                        result.text = (fNum / sNum).toString()
                } else if (sign == "*")
                    result.text = (fNum * sNum).toString()
                else if (sign == "-")
                    result.text = (fNum - sNum).toString()
                else if (sign == "+")
                    result.text = (fNum + sNum).toString()
                else if (sign == "%")
                    result.text = (fNum % sNum).toString()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item is selected. You can retrieve the selected item using

        sign = parent.getItemAtPosition(pos) as String
        Log.w("LEGIBLE","SOMETHINGESLE")
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

}

