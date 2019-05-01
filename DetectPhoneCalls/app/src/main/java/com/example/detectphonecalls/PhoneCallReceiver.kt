package com.example.detectphonecalls

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class PhoneCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Toast.makeText(context, "Recebendo ligação", Toast.LENGTH_SHORT).show();
        }
        if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
            Toast.makeText(context, "Chamada recebida", Toast.LENGTH_SHORT).show();
        }
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Toast.makeText(context, "Chamada encerrada", Toast.LENGTH_SHORT).show();
        }
    }

}