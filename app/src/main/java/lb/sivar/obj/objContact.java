package lb.sivar.obj;

import android.content.ContentValues;
import android.database.Cursor;

import lb.sivar.db.chatContract.*;

/**
 * Created by HP on 28/6/2017.
 */

public class objContact {
    private String idPhone;
    private String name;

    public objContact(){}
    public objContact(String Id,String Contact){

        this.idPhone =Id;
        this.name=Contact;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(contact.ID_PHONE, idPhone);
        values.put(contact.NAME, name);
        return values;
    }
    public objContact constructValues(Cursor c){
        //DatabaseUtils.dumpCursor(c);
        c.moveToPosition(0);
        this.idPhone =c.getString(0);
        this.name=c.getString(1);
        return this;
    }

    public String getIdPhone(){ return this.idPhone; }
    public String getCont(){ return this.name; }
}
