package com.example.ologador

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etLogin : EditText
    private lateinit var etSenha : EditText
    private lateinit var btnOk : Button
    private lateinit var btnCancel : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.etLogin = findViewById(R.id.etLogin)
        this.etSenha = findViewById(R.id.etSenha)
        this.btnOk = findViewById(R.id.btnOk)
        this.btnCancel = findViewById(R.id.btnCancel)

        this.btnCancel.setOnClickListener({cancelar(it)})
        this.btnOk.setOnClickListener({logar(it)})


    }

    fun cancelar(view : View){
        finish()
    }

    fun logar(view: View){
        var login = this.etLogin.text.toString()
        if(login.equals("admin") && this.etSenha.text.toString().equals("123456")){
            val it = Intent()
            it.putExtra("LOGIN", login)
            setResult(Activity.RESULT_OK, it)
            finish()
        }else{
            Toast.makeText(this, "ERROU_CANALHA",Toast.LENGTH_SHORT).show()
        }

    }
}
