package com.example.unikit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class Translator : AppCompatActivity() {
    var language = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)

        val editText: EditText = findViewById(R.id.editText)
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)

        val languages = resources.getStringArray(R.array.Languages)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long,
                ) {
                    language=languages[position]
                    Toast.makeText(this@Translator,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        button.setOnClickListener {
            if (!editText.text.isEmpty()) {
                var translationConfigs = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.fromLanguageTag(TranslateLanguage.ENGLISH))
                    .build()

                if(language.equals("Kannada")) {
                    //  Toast.makeText(applicationContext,language,Toast.LENGTH_LONG).show()

                    translationConfigs = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.fromLanguageTag(TranslateLanguage.KANNADA))
                        .build()
                }

                if(language.equals("Hindi")) {
                    //  Toast.makeText(applicationContext,language,Toast.LENGTH_LONG).show()

                    translationConfigs = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.fromLanguageTag(TranslateLanguage.HINDI))
                        .build()
                }

                if(language.equals("Tamil")) {
                    //  Toast.makeText(applicationContext,language,Toast.LENGTH_LONG).show()

                    translationConfigs = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.fromLanguageTag(TranslateLanguage.TAMIL))
                        .build()
                }

                if(language.equals("Telugu")) {
                    //  Toast.makeText(applicationContext,language,Toast.LENGTH_LONG).show()

                    translationConfigs = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.fromLanguageTag(TranslateLanguage.TELUGU))
                        .build()
                }

                var translator = Translation.getClient(translationConfigs)

                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {

                        Toast.makeText(this, "Translated Successful", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }


                translator.translate(editText.text.toString())
                    .addOnSuccessListener {
                        // Toast.makeText(applicationContext,language,Toast.LENGTH_LONG).show()

                        textView.setText(it)
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                    }
            }
        }
    }

    fun onBack(v: View?){
        var i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}