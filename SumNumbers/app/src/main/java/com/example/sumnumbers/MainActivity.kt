package com.example.sumnumbers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var btSomar: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.number1 = findViewById(R.id.number1)
        this.number2 = findViewById(R.id.number2)
        this.tvResultado = findViewById(R.id.texto)
        this.btSomar = findViewById(R.id.btSomar)

        btSomar.setOnClickListener(View.OnClickListener {
            var resultado = this.number1.text.toString().toInt() + this.number2.text.toString().toInt()
            this.tvResultado.setText(resultado.toString())
            //Toast.makeText(context: this, text: "result is"+result, Toast.LENGTH_LONG).show()
        })
    }
}
