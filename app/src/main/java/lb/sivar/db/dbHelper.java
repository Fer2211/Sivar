package lb.sivar.db;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import lb.sivar.db.chatContract.*;
import lb.sivar.obj.objChat;
import lb.sivar.obj.objContact;
import lb.sivar.obj.objMen;

public class dbHelper extends SQLiteOpenHelper {
    public static String TAG = "------>DB";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Chat.db";

    public dbHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    private String CONTACT_CHAT = "REFERENCES " + contact.TABLE_NAME + "(" + contact.ID_PHONE + ")";
    private String ID_CHAT = "REFERENCES " + chat.TABLE_NAME + "(" + chat.ID + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + contact.TABLE_NAME + " ("
                + contact.ID_PHONE + " TEXT PRIMARY KEY,"
                + contact.NAME + " TEXT NOT NULL,"
                + "UNIQUE (" + contact.ID_PHONE + "))");

        db.execSQL("CREATE TABLE " + chat.TABLE_NAME + " ("
                + chat.ID + " TEXT PRIMARY KEY,"
                + chat.ID_CONTACT + " TEXT NOT NULL "+ CONTACT_CHAT +","
                + chat.CONTACT + " TEXT NOT NULL,"
                + chat.ANONYM + " INTEGER DEFAULT 1,"
                + "UNIQUE (" + chat.ID + "))");

        db.execSQL("CREATE TABLE " + mens.TABLE_NAME + " ("
                + mens.ID + " TEXT PRIMARY KEY,"
                + mens.ID_CHAT + " TEXT NOT NULL "+ ID_CHAT +","
                + mens.WHO + " TEXT NOT NULL,"
                + mens.MESSAGE + " TEXT NOT NULL,"
                + mens.DATE + " INTEGER DEFAULT 1,"
                + "UNIQUE (" + mens.ID + "))");

        objContact c1 = new objContact("7623-4567","Sophie");
        objContact c2 = new objContact("9881-7243","Elizabeth");
        objContact c3 = new objContact("6578-2345","Patricia");

        db.insert(contact.TABLE_NAME,null,c1.toContentValues());
        db.insert(contact.TABLE_NAME,null,c2.toContentValues());
        db.insert(contact.TABLE_NAME,null,c3.toContentValues());

        objChat o1 = new objChat("C001","7623-4567","A001",1);
        objChat o2 = new objChat("C002","9881-7243","A004",0);
        objChat o3 = new objChat("C003","6578-2345","A005",1);

        db.insert(chat.TABLE_NAME,null,o1.toContentValues());
        db.insert(chat.TABLE_NAME,null,o2.toContentValues());
        db.insert(chat.TABLE_NAME,null,o3.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ contact.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ mens.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ chat.TABLE_NAME);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
    public void dump(){
        DatabaseUtils.dumpCursor(getAllContacts());
        DatabaseUtils.dumpCursor(getAllChats());
        DatabaseUtils.dumpCursor(getAllMens());
    }
    public void savePhone(String p){
        String var[] = {"Yo"};
        objContact c = new objContact();
        c.constructValues(getPhone());
        SQLiteDatabase s = getWritableDatabase();
        s.update(contact.TABLE_NAME,
                c.toContentValues(),
                contact.NAME + "=?",var);
    }
    public Cursor getPhone(){
        String var[] = {"Yo"};
        return getReadableDatabase().query(
                contact.TABLE_NAME,null,
                contact.NAME + "=?",
                var,null,null,null);
    }
    public Cursor getAllContacts() {
        try {
            return getReadableDatabase().query(
                    contact.TABLE_NAME,
                    null,null,null,null,null,null);
        }catch (Exception e){ return null; }
    }
    public long saveChat(objChat o) {
        SQLiteDatabase s = getWritableDatabase();
        return s.insert(
                chat.TABLE_NAME,
                null,
                o.toContentValues());
    }
    public void saveMen(objMen o) {
        SQLiteDatabase s = getWritableDatabase();
        s.insert(mens.TABLE_NAME, null,
                o.toContentValues());
    }

    public Cursor getChat(String id) {
        String var[] = {id};
        return getReadableDatabase().query(
                        chat.TABLE_NAME,null,
                        chat.ID + "=?",
                        var,null,null,null);
    }
    public Cursor getAllChats() {
        try {
            return getReadableDatabase().query(
                    chat.TABLE_NAME,
                    null,null,null,null,null,null);
        }catch (Exception e){ return null; }
    }
    public Cursor getMen_Chat(String id) {
        String var[] = {id};
        try {
            return getReadableDatabase().query(
                    mens.TABLE_NAME,null,
                    mens.ID_CHAT + "=?",
                    var,null,null,null);
        }catch (Exception e){ return null; }
    }
    public Cursor getAllMens() {
        try {
            return getReadableDatabase().query(
                    mens.TABLE_NAME,
                    null,null,null,null,null,null);
        }catch (Exception e){ return null; }
    }
}
