package com.example.osalvador

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    private var id = 0;
    private lateinit var btnAdd : Button
    private lateinit var dao: PessoaDAO
    private lateinit var nameET : EditText
    private lateinit var ageET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        dao = PessoaDAO(this)
        this.nameET = findViewById(R.id.nameEt)
        this.ageET= findViewById(R.id.idadeEt)
        try{
            val bundle:Bundle = intent.extras
            id = bundle.getInt("id",0)
            if(id!=0){
                nameET.setText(bundle.getString("name"))
                ageET.setText(bundle.getInt("age").toString())
            }
        }catch(ex:Exception){ }
        this.btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener({salvar(it)})


    }
    fun salvar(view: View){
        if(id==0){
            var age = this.ageET.text.toString().toInt()
            var name = this.nameET.text.toString()
            dao.insert(Pessoa(name, age))
            Toast.makeText(this,"Contact is added", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            var name = this.nameET.text.toString()
            var age = this.ageET.text.toString().toInt()
            //Log.e("APP_PESSOA", id.toString())
            dao.update(Pessoa(id,name,age))
            Toast.makeText(this,"contact is updated",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
