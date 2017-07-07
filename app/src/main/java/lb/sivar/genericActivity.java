package lb.sivar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class genericActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_activity);
        String opc = getIntent().getExtras().getString("opc");
        android.support.v4.app.FragmentManager FragmentManager=getSupportFragmentManager();

        if(opc==null) opc="";
        switch (opc){
            case "":
                break;
            default:
        }
    }
}
