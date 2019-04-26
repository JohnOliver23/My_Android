package com.example.ifbrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private  lateinit var voltar : ImageButton
    private  lateinit var avancar : ImageButton
    private  lateinit var atualizar : ImageButton
    private  lateinit var ir : ImageButton
    private  lateinit var url : EditText
    private  lateinit var pagina : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //instancia a classe que é responsável por manter a página no app


        voltar = findViewById(R.id.voltar)

        avancar = findViewById(R.id.avancar)

        atualizar = findViewById(R.id.atualizar)

        ir = findViewById(R.id.ir)

        url = findViewById(R.id.url)

        pagina = findViewById(R.id.pagina)
        //método responsável por fazer a página ser lida na própria aplicação

        pagina!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        //Habilita o JavaScript nas paginas web
        pagina.getSettings().setJavaScriptEnabled(true);

        //Habilita o zoom nas páginas
        pagina.getSettings().setSupportZoom(true);

        //Joga uma uma url no EditText
        url.setText("http://www.google.com");


        //fará o browser carregar a pagina de acordo com o Texto no EditText
        pagina.loadUrl(url.getText().toString());

        voltar.setOnClickListener({voltarPage(it) });

        avancar.setOnClickListener({avancarPage(it) });

        atualizar.setOnClickListener({atualizaPage(it)})

        ir.setOnClickListener({ carregarPage(it) })



    }
    fun voltarPage(view : View){
            pagina.goBack()

    }

    fun avancarPage(view : View){
            pagina.goForward()
    }

    fun atualizaPage(view : View){
            pagina.reload()
    }

    fun carregarPage(view : View){
        Log.e("APP_LOG","carregar")
        pagina!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        this.pagina.loadUrl(this.url.text.toString())

    }
}
