/*
 * http://developer.android.com/training/basics/actionbar/styling.html
 * http://blog.stylingandroid.com/archives/1249
 * http://developer.android.com/design/downloads/index.html#action-bar-icon-pack
 * http://developer.android.com/design/patterns/actionbar.html
 */

package cl.chihau.helloactionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HelloActionBarActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Actividad 1 (Main)");

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            Intent intent = new Intent(this, SearchActivity.class);
	            startActivity(intent);
	            return true;
	        case R.id.action_settings:
	            Intent intent2 = new Intent(this, SettingsActivity.class);
	            startActivity(intent2);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
