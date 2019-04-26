package com.example.ochamadormaster

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var btHtml : Button
    private lateinit var btDiscar : Button
    private lateinit var btLigar : Button
    private lateinit var btEmail : Button
    private lateinit var btSms : Button
    private lateinit var btCompartilharTexto : Button
    private lateinit var btPonto : Button
    private lateinit var btRota : Button
    private lateinit var btYoutube : Button
    private lateinit var btFoto : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.btHtml = findViewById(R.id.btHtml)
        this.btDiscar = findViewById(R.id.btDiscar)
        this.btLigar = findViewById(R.id.btLigar)
        this.btEmail = findViewById(R.id.btEmail)
        this.btSms = findViewById(R.id.btSms)
        this.btCompartilharTexto = findViewById(R.id.btCompartilharTexto)
        this.btPonto = findViewById(R.id.btPonto)
        this.btRota = findViewById(R.id.btRota)
        this.btYoutube = findViewById(R.id.btYoutube)
        this.btFoto = findViewById(R.id.btFoto)
        this.btHtml.setOnClickListener({html(it)})
        this.btDiscar.setOnClickListener({discar(it)})
        this.btLigar.setOnClickListener({ligar(it)})
        this.btEmail.setOnClickListener({email(it)})
        this.btSms.setOnClickListener({sms(it)})
        this.btCompartilharTexto.setOnClickListener({compartilharTexto(it)})
        this.btPonto.setOnClickListener({ponto(it)})
        this.btRota.setOnClickListener({rota(it)})
        this.btYoutube.setOnClickListener({youtube(it)})
        this.btFoto.setOnClickListener({foto(it)})
    }
    fun html(view: View){
        val uri = Uri.parse("http://pdm.valeriacavalcanti.com.br")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun discar(view: View){
        val uri = Uri.parse("tel:932248673")
        val it = Intent(Intent.ACTION_DIAL, uri)
        startActivity(it);

    }

    fun ligar(view: View){
        Log.e("APP_MASTER","ei")
        val uri = Uri.parse("tel:32248673")
        val it = Intent(Intent.ACTION_CALL, uri)
        val call = Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED
        if (ContextCompat.checkSelfPermission(this, call) == granted){
            Log.e("APP_MASTER","oi")
            startActivity(it)
        }
        Log.e("APP_MASTER","xii")
    }

    fun email(view: View){
        val uri = Uri.parse("mailto:valeria.cavalcanti@ifpb.edu.br")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra(Intent.EXTRA_SUBJECT, "Assunto")
        it.putExtra(Intent.EXTRA_TEXT, "Texto")
        startActivity(it)

    }

    fun sms(view: View){
        val uri = Uri.parse("sms:36121392")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body"
            , "Mensagem")
        startActivity(it)
    }

    fun compartilharTexto(view: View){
        val it = Intent(Intent.ACTION_SEND)
        it.setType("text/plain")
        it.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
//it.setPackage("com.whatsapp")
        startActivity(Intent.createChooser(it, "Compartilhar"))

    }


    fun ponto(view: View){
        val uri = Uri.parse("geo:-7.1356496,-34.8760932")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)

    }

    fun rota(view: View){
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"
        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)

    }

    fun youtube(view: View){
        val uri = Uri.parse("vnd.youtube://H0Z7ewOXCKw")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun foto(view: View){
        var intent = Intent(this, FotoMainActivity::class.java)
        startActivity(intent)



    }
}
