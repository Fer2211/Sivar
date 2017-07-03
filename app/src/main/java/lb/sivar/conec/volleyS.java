package lb.sivar.conec;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleyS {
        private static volleyS mVolleyS = null;
        private RequestQueue mRequestQueue;

        private volleyS(Context context) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        public static volleyS getInstance(Context context) {
            if (mVolleyS == null) {
                mVolleyS = new volleyS(context);
            }
            return mVolleyS;
        }

        public RequestQueue getRequestQueue() {
            return mRequestQueue;
        }

}
