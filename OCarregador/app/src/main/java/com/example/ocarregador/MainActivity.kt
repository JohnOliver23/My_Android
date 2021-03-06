package com.example.ocarregador

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var status: TextView
    private lateinit var receiver : TelaDesbloqueadaReceiver
    private lateinit var it : IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.status = findViewById(R.id.tvStatus)
        this.receiver = TelaDesbloqueadaReceiver()
        this.it = IntentFilter()
        this.it.addAction(Intent.ACTION_USER_PRESENT)

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(this.receiver, this.it)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(this.receiver)
    }

    inner class TelaDesbloqueadaReceiver() : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            status.text = "Desbloqueou!"
            Toast.makeText(context, "Desbloqueou", Toast.LENGTH_SHORT).show()

        }
    }
}
