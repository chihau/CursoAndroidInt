package cl.chihau.holalogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by chihau on 06-09-16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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
