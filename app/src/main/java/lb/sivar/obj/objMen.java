package lb.sivar.obj;

import android.content.ContentValues;
import android.database.Cursor;

import lb.sivar.db.chatContract.*;

import java.util.UUID;

public class objMen {
    private String id;
    private String idChat;
    private Integer who;//1 yo 0 el
    private String message;
    private long date;//unix format

    public objMen(){}
    public objMen(String IdChat,Integer Who,String Message,long Date){
        this.id="M-" + UUID.randomUUID().toString();
        this.idChat=IdChat;
        this.who=Who;
        this.message=Message;
        this.date=Date;
    }
    public objMen(String id,String IdChat,Integer Who,String Message,long Date){
        this.id=id;
        this.idChat=IdChat;
        this.who=Who;
        this.message=Message;
        this.date=Date;
    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(mens.ID, id);
        values.put(mens.ID_CHAT, idChat);
        values.put(mens.WHO, who);
        values.put(mens.MESSAGE, message);
        values.put(mens.DATE, date);
        return values;
    }
    public objMen constructValues(Cursor c){
        //DatabaseUtils.dumpCursor(c);
        c.moveToPosition(0);
        this.id=c.getString(0);
        this.idChat=c.getString(1);
        this.who=c.getInt(2);
        this.message=c.getString(3);
        this.date=c.getLong(4);
        return this;
    }

    public String getId(){ return this.id; }
    public String getIdChat(){ return this.idChat; }
    public Boolean getWho(){ if (this.who==1){ return true; }else{ return false; }}
    public String getMessage(){ return this.message; }
    public long getDate(){ return this.date; }
}
