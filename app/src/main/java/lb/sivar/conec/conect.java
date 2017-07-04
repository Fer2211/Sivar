package lb.sivar.conec;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class conect {

    private String Phone = "";
    public String getPhone(){ return this.Phone; }
    public void setPhone(String s){ this.Phone=s; }

    public boolean c(Context c){
        ConnectivityManager cM = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nI = cM.getActiveNetworkInfo();

        if(nI != null && nI.isConnected()){ return true; }
        else{
            Toast.makeText(c, "Compruebe conexion con Internet", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
