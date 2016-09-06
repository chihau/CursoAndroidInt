package cl.chihau.holalogin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private static final String SERVER_URL = "http://charlas.no-ip.org/android_intermedio/login/login.php";

    private EditText userText;
    private EditText passwordText;
    private TextView msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userText = (EditText) findViewById(R.id.user);
        passwordText = (EditText) findViewById(R.id.password);
        msgText = (TextView) findViewById(R.id.msg);
    }

    public void ingresar(View view) {
        String stringUser = userText.getText().toString();
        String stringPassword = passwordText.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new LoginTask().execute(stringUser,stringPassword);
        } else {
            msgText.setText("Existen problemas con la conexión");
        }
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        // doInBackground ejecuta la tarea en segundo plano
        @Override
        protected String doInBackground(String... params) {

            try {
                return login(params[0],params[1]);
            } catch (IOException e) {
                return "Error3";
            }
        }

        // onProgressUpdate nos sirve para mostrar el progreso de la tarea

        // onPostExecute despliega los resultados del AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(result.equalsIgnoreCase("Error1")) {
                msgText.setText("Usuario y/o contraseña incorrectos");
            } else if(result.equalsIgnoreCase("Error2")) {
                msgText.setText("Ingrese usuario y contraseña");
            } else if(result.equalsIgnoreCase("Error3")) {
                msgText.setText("Error al conectarse con el servidor");
            } else {
                String results[] = result.split(";");

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("ID", results[0]);
                bundle.putString("NOMBRE", results[1]);
                bundle.putString("APELLIDO", results[2]);
                bundle.putString("FECHA", results[3]);
                intent.putExtras(bundle);
                startActivity(intent);

                // Esto sirve para destruir la ventana de login una vez que el
                // usuario logra entrar
                finish();
            }
        }
    }

    // Método que genera la conexión con el servidor y pasa el usuario y contraseña
    // vía POST al servidor retornando la respuesta del servidor
    private String login(String user, String password) throws IOException {
        URL url = new URL(SERVER_URL);
        String respuesta;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            String param = "usuario=" + URLEncoder.encode(user, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");

            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestMethod("POST");

            OutputStream out = urlConnection.getOutputStream();
            out.write(param.getBytes());

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            respuesta = inputStreamToString(in);

        } finally {
            urlConnection.disconnect();
        }

        return respuesta;
    }

    // Convierte un InputStream a String
    private String inputStreamToString(InputStream is) throws IOException {
        String s = "";
        String line = "";

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        while ((line = rd.readLine()) != null) {
            s += line;
        }

        return s;
    }
}
