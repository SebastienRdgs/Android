package rodrigues.medicament.Modele;

public class Famille {

    private int _id;
    private String lib;

    public Famille(int _id, String LIB) {
    this._id = _id;
    this.lib = LIB;
}
    public Famille(String LIB) {
        this._id = -1;
        this.lib = LIB;
    }

    public long getId() {
        return this._id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getLib() {
        return this.lib;
    }

    public void setLib(String LIB) {
        this.lib = LIB;
    }

    public String toString(){
        return "id="+_id+" ,lib="+lib;
    }
}
