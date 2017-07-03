package lb.sivar.conec;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleyS {
    private static lb.sivar.conec.volleyS mVolleyS = null;
    private RequestQueue mRequestQueue;

    private volleyS(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static lb.sivar.conec.volleyS getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new lb.sivar.conec.volleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
