package lb.sivar.obj;

public class objUser {
    private String name = "";
    private String mail = "";
    private Boolean anom = true;

    public objUser(String n, String m, Boolean a) {
        this.name = n;
        this.mail = m;
        this.anom = a;
    }

    public void setName(String s){ this.name=s; }
    public void setMail(String s){ this.mail=s; }
    public void setAnom(Boolean s){ this.anom=s; }

    public String getName(){ return name; }
    public String getMail(){ return mail; }
    public Boolean getAnom(){ return anom; }
}
