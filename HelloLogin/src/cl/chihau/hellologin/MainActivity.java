package cl.chihau.hellologin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView id = (TextView) findViewById(R.id.id);
		id.setText("ID: " + getIntent().getStringExtra("ID"));
		
		TextView nombre = (TextView) findViewById(R.id.nombre);
		nombre.setText("Nombre: " + getIntent().getStringExtra("NOMBRE"));
		
		TextView apellido = (TextView) findViewById(R.id.apellido);
		apellido.setText("Apellido: " + getIntent().getStringExtra("APELLIDO"));
		
		TextView fecha = (TextView) findViewById(R.id.fecha);
		fecha.setText("Fecha: " + getIntent().getStringExtra("FECHA"));
	}

}
