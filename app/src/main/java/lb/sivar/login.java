package lb.sivar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import lb.sivar.baseVolleyActivity;
import lb.sivar.conec.conect;

import static lb.sivar.main.REQUEST_CODE;

public class login extends baseVolleyActivity {
    private static String TAG = "-------> login";

    private EditText lName;
    private EditText lPhone;
    private Button login;

    private conect c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File f = new File("/data/data/" + getPackageName() +  "/shared_prefs/" + getString(R.string.preferences) + ".xml");
        SharedPreferences sharedPref = getApplication().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        if (sharedPref.contains("register") && sharedPref.getInt("register",0)==1){
            Intent i = new Intent(getApplicationContext(),main.class);
            startActivity(i);
        } else {
            setContentView(R.layout.login_fragment);
            lName = (EditText) findViewById(R.id.login_name);
            lPhone = (EditText) findViewById(R.id.login_phone);
            login =  (Button) findViewById(R.id.login_sign_in);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c = new conect();
                    if(c.c(getApplication())) {
                        attemptLogin();
                    }
                }
            });
        }
    }

    @Override
    protected void onConnectionFinished(JSONObject j){
        Toast.makeText(getApplication(),"Bienvenido " + lName.getText().toString(),Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),main.class);
        startActivity(i);
    }
    @Override
    protected void onConnectionFailed(String s){
        super.onConnectionFailed("Error de Ingreso");
    }

    protected void attemptLogin(){
        // Reset errors.
        lPhone.setError(null);
        lName.setError(null);

        // Store values at the time of the login attempt.
        String phone = lPhone.getText().toString();
        String name = lName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(name) && !isNameValid(name)) {
            lName.setError(getString(R.string.error_invalid_password));
            focusView = lName;
            cancel = true;
        } else if (TextUtils.isEmpty(phone) && !isPhoneValid(phone)) {
            lPhone.setError(getString(R.string.error_invalid_email));
            focusView = lPhone;
            cancel = true;
        }

        if (cancel) { focusView.requestFocus();
        } else {
            JSONObject j = new JSONObject();
            try {
                j.put("phone",phone);
                j.put("name",name);
            }catch (JSONException e){
                Toast.makeText(getApplication(),"Reingresa tus datos",Toast.LENGTH_LONG).show();
            }
            makeRequest(Request.Method.POST, commons.URL_U_REGISTER,j);
        }
    }

    private boolean isPhoneValid(String s) { return (s.length() > 8); }
    private boolean isNameValid(String s) { return (s.length() > 4 || s.length() < 40); }
}
