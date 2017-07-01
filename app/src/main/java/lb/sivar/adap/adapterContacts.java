package lb.sivar.adap;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import lb.sivar.R;
import lb.sivar.commons;
import lb.sivar.db.dbHelper;
import lb.sivar.obj.objContact;

import java.util.ArrayList;

public class adapterContacts extends BaseAdapter {

    private static commons commons;
    private static dbHelper db;

    private static LayoutInflater inflater = null;
    ArrayList<objContact> contactList;

    public adapterContacts(Activity activity, ArrayList<objContact> list) {
        db = new dbHelper(activity);
        contactList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return contactList.size(); }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        objContact contacts = contactList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.contact_design, null);

        commons = new commons();
        TextView msg = vi.findViewById(R.id.contact_name);
        TextView dt = vi.findViewById(R.id.contact_phone);
        msg.setText(contacts.getCont());
        dt.setText(contacts.getIdPhone());

        return vi;
    }
    public void init(Cursor c){
        for (int i=0;c.moveToPosition(i);i++){
            c.moveToPosition(i);
            objContact con = new objContact(c.getString(0),c.getString(1));
            contactList.add(con);
        }
    }
    public void add(objContact object) { contactList.add(object); }
}
