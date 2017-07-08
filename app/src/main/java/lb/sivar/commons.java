package lb.sivar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class commons {

    /**
     * Variables globales
     */
    private static final String URL = "https://sivar111.000webhostapp.com/sivar/v1/";
    //public static final String URL = "http://192.168.1.3/s/v1/";
    //private static final String URL = "http://192.168.0.21/s/v1/";

    public static final String URL_U_REGISTER = URL + "users/register";


    /**
     * Conversiones de fechas
     */
    private static DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("K:mma");

    public String getCurrentTime() { return timeFormat.format(Calendar.getInstance().getTime()); }
    public String getCurrentDate() { return dateFormat.format(Calendar.getInstance().getTime()); }
    public long getTimeDate(){ return Calendar.getInstance().getTime().getTime(); }

    public String getTime(long dt){ return timeFormat.format(dt); }
    public String getDate(long dt){ return dateFormat.format(dt); }
}
