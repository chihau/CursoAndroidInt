package cl.chihau.hellosmsreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class SMSActivity extends Activity {
    IntentFilter intentFilter;
    
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView tv = (TextView) findViewById(R.id.tv_mensaje);
            tv.setText(intent.getExtras().getString("sms"));
        }
    };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("cl.chihau.accion.SMS_RECIBIDO");
		
		registerReceiver(intentReceiver, intentFilter);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(intentReceiver);
	}

}