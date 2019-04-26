package com.example.obuscadorlogador

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val LOGADOR = 1
    private lateinit var label : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.label = findViewById(R.id.etLogin)
        val it = Intent("O_LOGADOR")
        startActivityForResult(it, LOGADOR)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == LOGADOR){
                var text = data?.getStringExtra("LOGIN")
                Log.e("APP_LOGADOR",text)
                this.label.text = text

            }
        }
    }
}
