package com.example.unikit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Converter : AppCompatActivity() {
    var unit = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        var button: Button = findViewById(R.id.button)
        val dropdown1 = resources.getStringArray(R.array.dropdown1)
        val spinner: Spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, dropdown1
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long,
                ) {
                    unit = dropdown1[position]
                    Toast.makeText(
                        this@Converter,
                        getString(R.string.selecteditem) + " " +
                                "" + dropdown1[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        button.setOnClickListener()
        {

            if(unit.equals("centimeters")){
                var inp:EditText=findViewById(R.id.inp) as EditText
                var inpv=inp.text.toString().toFloat()
                inpv=inpv/100
                var toe:TextView=findViewById(R.id.to) as TextView
                toe.setText(inpv.toString())
                Toast.makeText(applicationContext,"Displayed in METERS",Toast.LENGTH_LONG).show()
            }
            if(unit.equals("meters")){
                var inp:EditText=findViewById(R.id.inp) as EditText
                var inpv=inp.text.toString().toFloat()
                inpv=inpv*100
                var toe:TextView=findViewById(R.id.to) as TextView
                toe.setText(inpv.toString())
                Toast.makeText(applicationContext,"Displayed in CENTIMETERS",Toast.LENGTH_LONG).show()
            }
            if(unit.equals("celsius")){
                val f=0.5555
                var inp:EditText=findViewById(R.id.inp) as EditText
                var inpv=inp.text.toString().toFloat()
                inpv = (((inpv *9)/5)+32).toFloat()
                var toe:TextView=findViewById(R.id.to) as TextView
                toe.setText(inpv.toString())
                Toast.makeText(applicationContext,"Displayed in FAHRENHEIT",Toast.LENGTH_LONG).show()
            }
            if(unit.equals("fahrenheit")){
                val f=0.5555
                var inp:EditText=findViewById(R.id.inp) as EditText
                var inpv=inp.text.toString().toFloat()
                inpv = ((inpv -32)*f).toFloat()
                var toe:TextView=findViewById(R.id.to) as TextView
                toe.setText(inpv.toString())
                Toast.makeText(applicationContext,"Displayed in CELSIUS",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onBack(v: View?){
        var i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}