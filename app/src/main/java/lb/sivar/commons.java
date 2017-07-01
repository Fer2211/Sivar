package lb.sivar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by HP on 26/6/2017.
 */

public class commons {
    private static DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("K:mma");

    public String getCurrentTime() { return timeFormat.format(Calendar.getInstance().getTime()); }
    public String getCurrentDate() { return dateFormat.format(Calendar.getInstance().getTime()); }
    public long getTimeDate(){ return Calendar.getInstance().getTime().getTime(); }

    public String getTime(long dt){ return timeFormat.format(dt); }
    public String getDate(long dt){ return dateFormat.format(dt); }
}
