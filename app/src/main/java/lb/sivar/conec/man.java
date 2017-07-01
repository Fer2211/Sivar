package lb.sivar.conec;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class man extends AsyncTask<Context, Boolean, String> {
    private static String TAG = "-------> MAN";
    Context cnt = null;
    Integer ii = 0;


    private Gson gson = new Gson();

    @Override
    public String doInBackground(Context... p) {
        cnt = p[0];
        conect cone = new conect();
        HttpURLConnection con = null;
        publishProgress(true);

        Request r = new JsonObjectRequest(
                Request.Method.GET,
                "http://192.168.1.3/s/index.php",
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Procesar la respuesta Json
                        procesarRespuesta(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("------->Error Volley: ","-> " + error.toString());
                    }
                }

        );

        /*if(cone.c(p[0])) {
            try {
                publishProgress(true);
                // Establecer la conexión
                URL url = new URL("http://192.168.1.5/sivar/man.php");
                con = (HttpURLConnection) url.openConnection();
                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();
                if(statusCode!=200) {
                    return "El recurso no está disponible";
                }
                else{
                    publishProgress(true);
                    //InputStream in = new BufferedInputStream(con.getInputStream());
                    //GSONParser gson = new GSONParser();
                    //objUser user = new objUser();
                    //user = gson.leerGson(in);
                    //Log.d("Formato Json", user.getName());
                    //Log.d("Pruebas: ", in.toString());
                    //Toast.makeText(cnt,"-> "+in.toString(),Toast.LENGTH_LONG).show();
                }
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        return "Si";
    }
    @Override
    public void onProgressUpdate(Boolean... b){
        Toast.makeText(cnt,"Coneccion "+b[0]+" "+ii,Toast.LENGTH_SHORT).show();;
        ii++;
    }
    @Override
    public void onPostExecute(String s){
        Toast.makeText(cnt, "R// "+s, Toast.LENGTH_SHORT).show();
    }


    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO
                    // Obtener array "metas" Json
                    JSONArray mensaje = response.getJSONArray("datos");
                    Log.i(TAG,mensaje.toString());
                    // Parsear con Gson
                    //Meta[] metas = gson.fromJson(mensaje.toString(), Meta[].class);
                    // Inicializar adaptador
                    //adapter = new MetaAdapter(Arrays.asList(metas), getActivity());
                    // Setear adaptador a la lista
                    //lista.setAdapter(adapter);
                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    //Toast.makeText(getActivity(),mensaje2,Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d("e -> ", e.getMessage());
        }

    }
}