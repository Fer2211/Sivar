package lb.sivar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lb.sivar.frags.loginFragment;

public class genericActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_activity);
        String opc = getIntent().getExtras().getString("opc");
        android.support.v4.app.FragmentManager FragmentManager=getSupportFragmentManager();

        if(opc==null) opc="";
        switch (opc){
            case "login":
                FragmentManager.beginTransaction().replace(R.id.generic_fragment,new loginFragment()).commit();

                break;
            default:
        }
    }
}
