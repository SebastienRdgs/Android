package rodrigues.medicament.Modele;

public class Composant {

    private int code;
    private String lib;
    private int codeMedic;

    public Composant(int code, String lib, int codeMedic) {
        this.code = code;
        this.lib = lib;
        this.codeMedic = codeMedic;
        }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public int getCodeMedic() {
        return codeMedic;
    }

    public void setCodeMedic(int codeMedic) {
        this.codeMedic = codeMedic;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "code=" + code +
                ", lib='" + lib + '\'' +
                ", codeMedic=" + codeMedic +
                '}';
    }
}