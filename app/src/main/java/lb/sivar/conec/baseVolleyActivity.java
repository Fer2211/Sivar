package lb.sivar.conec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import lb.sivar.conec.volleyS;

public class baseVolleyActivity extends AppCompatActivity {
    private static String TAG = "-------->BasevActivity";

    private volleyS volley;
    protected RequestQueue fRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volley = volleyS.getInstance(getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fRequestQueue != null) {
            fRequestQueue.cancelAll(this);
        }
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(60000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            fRequestQueue.add(request);
        }
    }

    protected void makeRequest(int metodo, String url, JSONObject params){
        JsonObjectRequest request = new JsonObjectRequest(metodo,url,params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject j) {
                        Log.d(TAG, "onResponse: " + j.toString());
                        onConnectionFinished(j);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                // As of f605da3 the following should work
                NetworkResponse response = e.networkResponse;
                if (e instanceof ServerError && response != null) {
                    JSONObject obj;
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        obj = new JSONObject(res);
                        Log.d(TAG, "onEResponse: " + res);
                        onConnectionSolution(obj);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        onConnectionFailed("Problemas con el servidor.");
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                        onConnectionFailed("Problemas con el servidor, reintente en un momento.");
                    }
                }
            }
        });
        addToQueue(request);
    }

    protected void onConnectionSolution(JSONObject json) {
        String s = "";
        try {
            switch (json.getInt("code")){
                case 420: s = "Problemas con la transferencia de datos"; break;
                case 4201: s = "Reingresa el telefono e intenta"; break;
                case 4202: s = "Reingresa el nombre e intenta"; break;

                default:
                    s = "Problemas con el servidor, espera una actualizacion pronto";
            }
        } catch (JSONException j){
            s = "Error en la transferencia";
        } finally {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }


    protected void onConnectionFinished(JSONObject json) {
        //getActivity().setProgressBarIndeterminateVisibility(false);
        try{
            Toast.makeText(getApplicationContext(), json.getString("message"), Toast.LENGTH_LONG).show();
        } catch (JSONException j){
            Log.d(TAG, "onConnectionFinished: Problemas con el JSON");
        }
    }

    protected void onConnectionFailed(String error) {
        //getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
