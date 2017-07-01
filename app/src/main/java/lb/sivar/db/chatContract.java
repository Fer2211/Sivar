package lb.sivar.db;

public class chatContract {
    interface contactEntry {
        String TABLE_NAME = "contact";
        String ID_PHONE = "idphone";
        String NAME = "name";
    }
    interface chatEntry {
        String TABLE_NAME = "chat";
        String ID = "id";
        String ID_CONTACT = "idcontact";
        String CONTACT = "contact";
        String ANONYM = "anonym";
    }
    interface menEntry {
        String TABLE_NAME = "men";
        String ID = "id";
        String ID_CHAT = "idchat";
        String WHO = "who";
        String MESSAGE = "message";
        String DATE = "date";
    }

    public static class contact implements contactEntry {}
    public static class chat implements chatEntry {}
    public static class mens implements menEntry {}
}
