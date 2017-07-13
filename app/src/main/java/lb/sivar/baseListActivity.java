package lb.sivar;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import lb.sivar.adap.adapterContacts;
import lb.sivar.adap.adapterMen;
import lb.sivar.db.dbHelper;
import lb.sivar.obj.objChat;
import lb.sivar.obj.objContact;
import lb.sivar.obj.objMen;

public class baseListActivity extends AppCompatActivity {
    private static String TAG = "------->BaseListActivity";

    private dbHelper db;
    private objChat c;
    private objContact m;
    private adapterContacts adapC;
    private commons commons;

    private ArrayList<objContact> contacts;
    private ListView msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        commons = new commons();
        db = new dbHelper(this);
        c = new objChat();

        msgList = (ListView) findViewById(R.id.contact_list);

        // ----Set autoscroll of listview when a new message arrives----//
        msgList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        contacts = new ArrayList<objContact>();
        adapC = new adapterContacts(this, contacts);
        msgList.setAdapter(adapC);

        DatabaseUtils.dumpCursor(db.getAllContacts());
        if(db.getAllContacts()!=null){
            adapC.init(db.getAllContacts());
            adapC.notifyDataSetChanged();
        } else Toast.makeText(this,"Aún no tienes mensajes inicia hoy!!",Toast.LENGTH_LONG).show();
    }
}
