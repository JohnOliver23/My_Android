package com.example.novosomador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnOk: Button
    private lateinit var btnCancel: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etUsuario = findViewById(R.id.etLogin)
        this.etSenha = findViewById(R.id.etSenha)
        this.btnOk = findViewById(R.id.btnOk)
        this.btnCancel = findViewById(R.id.btnCancel)

        this.btnOk.setOnClickListener({salvar(it)})
        btnCancel.setOnClickListener({
            Log.e("APP_LOGIN","Clicou cancelar")
        })


    }
    fun salvar(view: android.view.View) {
        Log.i("APP_LOGIN", "CLICOU!!!!!!!!")
    }
}
