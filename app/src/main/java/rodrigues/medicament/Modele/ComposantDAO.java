package rodrigues.medicament.Modele;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ComposantDAO {

    private static String base = "Medicament";
    private static int version = 1;
    private rodrigues.medicament.Modele.BdSQLiteOpenHelper accesBD;

    public ComposantDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);

    }

    public long addComposant(Composant unComposant){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("code", unComposant.getCode());
        value.put("libelle", unComposant.getLib());
        value.put("codeMedic", unComposant.getCodeMedic());
        ret = bd.insert("Composant", null, value);

        return ret;
    }
    public void suprComposant(String code){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("Composant","code LIKE '%"+code+"'",null);
    }


    public Composant getComposant(int code){
        Composant leComposant = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Composant where code="+code+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leComposant = new Composant(curseur.getInt(0),curseur.getString(2),curseur.getInt(1));

        }
        return leComposant;
    }

    public Composant getComposant(String lib){
        Composant leComposant = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Composant where libelle='"+lib+"';",null);

        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leComposant = new Composant(curseur.getInt(0),curseur.getString(2),curseur.getInt(1));

        }
        return leComposant;
    }

    public ArrayList<Composant> getComposants(String famille){
        Cursor curseur;
        String req = "select * from Composant where codeMedic = '"+famille+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToComposantArrayList(curseur);
    }


    private ArrayList<Composant> cursorToComposantArrayList(Cursor curseur){
        ArrayList<Composant> listeComposant = new ArrayList<Composant>();
        int _id;
        String libelle;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            listeComposant.add(new Composant(curseur.getInt(0),curseur.getString(2),curseur.getInt(1)));
            curseur.moveToNext();
        }

        return listeComposant;
    }




}