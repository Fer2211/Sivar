package lb.sivar.obj;

import android.content.ContentValues;
import android.database.Cursor;

import lb.sivar.db.chatContract.*;

public class objChat {
    private String id;
    private String idContact;
    private String contact;
    private Integer anonym;

    public objChat(){}
    public objChat(String Id,String idContact,String Contact,Integer Anonym){
        this.id=Id;
        this.idContact=idContact;
        this.contact=Contact;
        this.anonym=Anonym;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(chat.ID, id);
        values.put(chat.ID_CONTACT, idContact);
        values.put(chat.CONTACT, contact);
        values.put(chat.ANONYM, anonym);
        return values;
    }
    public objChat constructValues(Cursor c){
        //DatabaseUtils.dumpCursor(c);
        c.moveToPosition(0);
        this.id=c.getString(0);
        this.idContact=c.getString(1);
        this.contact=c.getString(2);
        this.anonym=c.getInt(3);
        return this;
    }

    public String getId(){ return this.id; }
    public String getIdContact(){ return this.idContact; }
    public String getCont(){ return this.contact; }
    public Integer getAnom(){ return this.anonym; }
}
