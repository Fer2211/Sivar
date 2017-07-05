package lb.sivar.frags;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import lb.sivar.R;
import lb.sivar.commons;
import lb.sivar.conec.conect;

public class loginFragment extends baseVolleyFragment{
    private static String TAG = "-------> login";

    private EditText lName;
    private EditText lPhone;
    private Button login;

    private conect c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        lName =  v.findViewById(R.id.login_name);
        lPhone =  v.findViewById(R.id.login_phone);
        login =  v.findViewById(R.id.login_sign_in);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = new conect(); if(c.c(getActivity())){
                    JSONObject j = new JSONObject();
                    try {
                        j.put("phone",lPhone.getText().toString());
                        j.put("name",lName.getText().toString());
                    }catch (JSONException e){
                        Toast.makeText(getActivity(),"Reingresa tus datos",Toast.LENGTH_LONG).show();
                    }
                    makeRequest(Request.Method.POST, commons.URL_U_REGISTER,j);
                }
            }
        });
    }

    @Override
    protected void onConnectionFinished(JSONObject j){
        Toast.makeText(getActivity(),"Bienvenido " + lName.getText().toString(),Toast.LENGTH_LONG).show();
        getActivity().onBackPressed();
    }
}