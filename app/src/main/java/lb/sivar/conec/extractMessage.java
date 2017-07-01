package lb.sivar.conec;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class extractMessage extends AsyncTask<Context, Boolean, String> {
    private static String TAG = "-------> extract";
    Context cnt = null;
    Integer ii = 0;

    private Gson gson = new Gson();

    @Override
    public String doInBackground(Context... p) {
        cnt = p[0];
        conect cone = new conect();
        HttpURLConnection con = null;
        publishProgress(true);

        RequestQueue queue = Volley.newRequestQueue(cnt);

        Request r = new JsonObjectRequest(
                Request.Method.GET,
                "http://192.168.1.3/s/index.php?ID=" + cone.getPhone(),
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
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO
                    JSONArray mensaje = response.getJSONArray("datos");
                    Log.i(TAG,mensaje.getString(0));
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