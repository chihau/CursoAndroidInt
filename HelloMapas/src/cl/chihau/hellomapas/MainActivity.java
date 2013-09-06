package cl.chihau.hellomapas;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends android.support.v4.app.FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		GoogleMap mapa = null;
		
		if (mapa == null) {
			mapa = ((SupportMapFragment) getSupportFragmentManager()
	                .findFragmentById(R.id.map)).getMap();
	        if (mapa != null) {
	        	// Agregamos una capa simple de localizaci—n (MyLocation Layer)
	        	// Para hacer algo m‡s preciso y personalizable utilizar la 
	        	// Location API
	        	// http://developer.android.com/google/play-services/location.html
	    		mapa.setMyLocationEnabled(true);
	    		
	    		//mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	        } else {
	        	Log.e("HelloMaps", "No est‡n disponibles los mapas");
			}
	    }
    }
}
