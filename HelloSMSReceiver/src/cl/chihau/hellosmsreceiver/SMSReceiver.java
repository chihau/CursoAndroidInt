package cl.chihau.hellosmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
    	// Obtenemos el mensaje SMS
    	Bundle bundle = intent.getExtras();
        String str = "Nœmero: ";
        if (bundle != null)
        {
            // El formato del SMS lo almacenamos con el formato PDU
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if (i==0) {
                	// Obtenemos el nœmero de telŽfono del emisor
                	str += msgs[i].getOriginatingAddress();
                	str += "\n";
                } 
                // Obtenemos el texto del mensaje
                str += msgs[i].getMessageBody().toString();                	
            }
            
            // mostramos la actividad (en caso que se encuentre en segundo plano)
            Intent mainActivityIntent = new Intent(context, SMSActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
            
            // creamos un intent para enviarle a la actividad el sms
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("cl.chihau.accion.SMS_RECIBIDO");
            broadcastIntent.putExtra("sms", str);
            context.sendBroadcast(broadcastIntent);
            
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            
            // Para que a otras aplicaciones de mensajer’a no reciban el mensaje
            this.abortBroadcast();
        }
    }
}

