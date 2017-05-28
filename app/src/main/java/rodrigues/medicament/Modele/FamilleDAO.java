package rodrigues.medicament.Modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FamilleDAO {

    private static String base = "Medicament";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public FamilleDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);

    }

    public long addFamille(Famille uneFamille){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("id", uneFamille.getId());
        value.put("LIB", uneFamille.getLib());
        ret = bd.insert("famille", null, value);

        return ret;
    }
    public void majFamille(Famille uneFamille){
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("lib", uneFamille.getLib());
        bd.update("famille",value,"id = '"+uneFamille.getId()+"'",null);
    }
    public void suprFamille(String id){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("famille","id LIKE '%"+id+"'",null);
    }


    public Famille getFamille(int id){
        Famille laFamille = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from famille where id="+id+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laFamille = new Famille(curseur.getInt(0),curseur.getString(1));

        }
        return laFamille;
    }
    public Famille getFamille(String lib){
        Famille laFamille = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from famille where lib='"+lib+"';",null);

        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laFamille = new Famille(curseur.getInt(0),curseur.getString(1));

        }
        return laFamille;
    }

    public ArrayList<Famille> getFamilles(String lib){
        Cursor curseur;
        String req = "select * from famille where lib like '"+lib+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToFamilleArrayList(curseur);
    }


    private ArrayList<Famille> cursorToFamilleArrayList(Cursor curseur){
        ArrayList<Famille> listeFamille = new ArrayList<Famille>();
        int _id;
        String lib;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            _id = curseur.getInt(0);
            lib = curseur.getString(1);
            listeFamille.add(new Famille(_id,lib));
            curseur.moveToNext();
        }

        return listeFamille;
    }
}
