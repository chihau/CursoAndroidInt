package cl.chihau.hellopreferencias;

import java.util.Set;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferenciasFragment extends PreferenceFragment {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferencias);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
     
            Log.i("HelloPreferencias", "Opci—n 1: " + pref.getBoolean("opcion1", false));
            Log.i("HelloPreferencias", "Opci—n 2: " + pref.getString("opcion2", ""));
            Log.i("HelloPreferencias", "Opci—n 3: " + pref.getString("opcion3", ""));
            
            Set<String> a = null;
            Log.i("HelloPreferencias", "Opci—n 4: " + pref.getStringSet("opcion4", a));
            Log.i("HelloPreferencias", "Opci—n 5: " + pref.getBoolean("opcion5", true));

    }

}
