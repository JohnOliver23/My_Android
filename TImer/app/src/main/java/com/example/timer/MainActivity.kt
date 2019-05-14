package com.example.timer

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var view_timer : Chronometer
    private lateinit var btnCLick : Button
    private lateinit var myDialog : Dialog
    private lateinit var txtDialog : TextView
    private lateinit var btnOkDialog : Button
    private lateinit var btnCancelDialog : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_timer = findViewById(R.id.view_timer)

       // view_timer.setCountDown(true)
        view_timer.base = SystemClock.elapsedRealtime()
        view_timer.start()

        btnCLick = findViewById(R.id.btnClick)
        btnCLick.setOnClickListener({showDialog(it)})

    }

    fun showDialog(view: View){
        view_timer.stop()
        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.dialog_activity)
        myDialog.setTitle("My first dialog box")
        txtDialog = myDialog.findViewById(R.id.text_content)
        txtDialog.setText("Wellcome, User !")
        /*
        this.btnOkDialog = findViewById(R.id.btnOk)
        this.btnCancelDialog = findViewById(R.id.btnCancel)
        this.btnOkDialog.setOnClickListener({
            Toast.makeText(this,"you confirmed", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        })
        this.btnCancelDialog.setOnClickListener({
            Toast.makeText(this,"you canceled", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        })*/
        myDialog.show()






    }
}
