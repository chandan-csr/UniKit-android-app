package com.example.unikit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCal(v: View?){
        var i = Intent(this,Scientificcalculator::class.java)
        startActivity(i)
    }

    fun onCon(v: View?) {
        var i = Intent(this, Converter::class.java)
        startActivity(i)
    }

    fun onTran(v: View?) {
        var i = Intent(this, Translator::class.java)
        startActivity(i)
    }

    fun onDat(v: View?) {
        var i = Intent(this, DateCounter::class.java)
        startActivity(i)
    }

    fun onNote(v: View?) {
        var i = Intent(this, Note::class.java)
        startActivity(i)
    }
}