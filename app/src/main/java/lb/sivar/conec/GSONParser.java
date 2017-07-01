package lb.sivar.conec;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lb.sivar.obj.objUser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by HP on 22/6/2017.
 */

public class GSONParser {
    public objUser leerGson(InputStream in) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        reader.beginArray();
        objUser user = gson.fromJson(reader, objUser.class);

        reader.endArray();
        reader.close();
        return user;
    }
}
