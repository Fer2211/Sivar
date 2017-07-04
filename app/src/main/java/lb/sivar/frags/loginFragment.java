package lb.sivar.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import lb.sivar.R;
import lb.sivar.conec.conect;

public class loginFragment extends baseVolleyFragment {
    private static String TAG = "-------> login";

    private EditText lName;
    private EditText lPhone;
    private Button login;


    private conect c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        lName = (EditText) v.findViewById(R.id.login_name);
        lPhone = (EditText) v.findViewById(R.id.login_phone);
        login = (Button) v.findViewById(R.id.login_sign_in);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest(){
        c = new conect(); if(c.c(getActivity())) {
            String url = "http://192.168.1.3/s/pruebas.php";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject j) {
                    lName.setText(j.toString());
                    Log.d(TAG, "onResponse: "+j.toString());
                    onConnectionFinished();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    Log.d(TAG, "onErrorResponse: "+e.toString());
                    onConnectionFailed(e.toString());
                }
            });
            addToQueue(request);
        }
    }
}
