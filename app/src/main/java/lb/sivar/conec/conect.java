package lb.sivar.conec;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class conect {

    private String Phone = "";
    public String getPhone(){ return this.Phone; }
    public void setPhone(String s){ this.Phone=s; }

    public boolean c(Context c){
        ConnectivityManager cM = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nI = cM.getActiveNetworkInfo();
        return (nI != null && nI.isConnected());
    }
}
