package cl.chihau.hellocontacts;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Equivalente a 
        // Uri allContacts = Uri.parse("content://contacts/people");
        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;

        Cursor c; 
        if (android.os.Build.VERSION.SDK_INT <11) {
        	// menor a Honeycomb
            c = getContentResolver().query(
            		allContacts, 
            		null, 
            		ContactsContract.Contacts.DISPLAY_NAME + " LIKE '%Chau'", 
            		null, 
            		ContactsContract.Contacts._ID + " DESC");
            startManagingCursor(c); 
            
        } else {
        	// mayor a Honeycomb
            CursorLoader cursorLoader = new CursorLoader(
            		this, 
            		allContacts, 
            		null, 
                    ContactsContract.Contacts.DISPLAY_NAME + " LIKE '%Chau'",
                    null, 
                    ContactsContract.Contacts._ID + " DESC");
            c = cursorLoader.loadInBackground();        	
        }

        String[] columns = new String[] {
        		ContactsContract.Contacts.DISPLAY_NAME,
        		ContactsContract.Contacts._ID
        };
        
        int[] views = new int[] {R.id.nombre_contacto, R.id.id_contacto};
        
        SimpleCursorAdapter adapter;
        
        if(android.os.Build.VERSION.SDK_INT < 11) {
        	adapter = new SimpleCursorAdapter(this, R.layout.list_item, c, columns, views);
        } else {
        	adapter = new SimpleCursorAdapter(this, R.layout.list_item, c, columns, views,
        			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        			// Este flag permite que cuando existan cambios en los datos
        			// se le informe al adaptador al respecto.
        }
        
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        
	}

}
