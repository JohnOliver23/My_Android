package com.example.ocarregador

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CarregadorReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {


        if(intent.action == Intent.ACTION_POWER_CONNECTED){
            Toast.makeText(context, "Que bom", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Eita", Toast.LENGTH_SHORT).show()
        }

    }
}
