package com.example.unikit

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.joda.time.PeriodType
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateCounter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_counter)
    }

    var dateSetListener1: OnDateSetListener? = null
    var dateSetListener2: OnDateSetListener? = null

    fun fd(v: View?) {
        var sd = findViewById<View>(R.id.st_date) as TextView

        val textView: TextView = findViewById(R.id.st_date)
        sd.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        dateSetListener1 = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            sd.text = sdf.format(cal.time)
        }

        textView.setOnClickListener {
            val d = DatePickerDialog(
                this@DateCounter, dateSetListener1,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    fun ld(v: View?) {
        var ed = findViewById<View>(R.id.en_date) as TextView

        val textView: TextView = findViewById(R.id.en_date)
        ed.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        dateSetListener2 = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            ed.text = sdf.format(cal.time)
        }

        textView.setOnClickListener {
            DatePickerDialog(
                this@DateCounter, dateSetListener2,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    fun calculate(v: View?){
        var sd = findViewById<View>(R.id.st_date) as TextView
        var ed = findViewById<View>(R.id.en_date) as TextView
        var tvResult = findViewById<View>(R.id.tv_result) as TextView
        var cal = Calendar.getInstance()
        cal.get(Calendar.YEAR)
        cal.get(Calendar.MONTH)
        cal.get(Calendar.DAY_OF_MONTH)

        // converting the inputted date to string
        val sDate: String = sd.text.toString()
        val eDate: String = ed.text.toString()
        val simpleDateFormat1 = SimpleDateFormat("dd/MM/yyyy")
        try {
            // converting it to date format
            val date1 = simpleDateFormat1.parse(sDate)
            val date2 = simpleDateFormat1.parse(eDate)
            val startDate = date1.time
            val endDate = date2.time

            if(startDate == endDate){
                Toast.makeText(
                    this@DateCounter,
                    "Both the dates are same...",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // condition
            if (startDate <= endDate) {
                val period: org.joda.time.Period =
                    org.joda.time.Period(startDate, endDate, PeriodType.yearMonthDay())
                val years: Int = period.years
                val months: Int = period.months
                val days: Int = period.days

                // show the final output
                var res = tvResult
                res.text = "$years Years\n\n$months Months\n\n$days Days"
            } else {
                // show message
                Toast.makeText(
                    this@DateCounter,
                    "Start date exceeds the end date...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun onBack(v: View?){
        var i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}