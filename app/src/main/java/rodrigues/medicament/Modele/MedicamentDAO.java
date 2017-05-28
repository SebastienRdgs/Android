package rodrigues.medicament.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO {

    private static String base = "Medicament";
    private static int version = 1;
    private rodrigues.medicament.Modele.BdSQLiteOpenHelper accesBD;

    public MedicamentDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);

    }

    public long addMedicament(Medicament unMedic){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("numDepotLegal", unMedic.getNumDepotLegal());
        value.put("nom", unMedic.getNom());
        value.put("contreindic", unMedic.getContreindic());
        value.put("prixEchantillon", unMedic.getPrixEchantillon());
        value.put("effet", unMedic.getEffets());
        value.put("codeFamille", unMedic.getFamille());
        ret = bd.insert("medicament", null, value);

        return ret;
    }
    public void suprMedicament(String numDepot){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("medicament","numDepotLegal LIKE '%"+numDepot+"'",null);
    }

    public void majMedicament(Medicament unMedic){
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("nom", unMedic.getNom());
        value.put("contreindic", unMedic.getContreindic());
        value.put("prixEchantillon", unMedic.getPrixEchantillon());
        value.put("effet", unMedic.getEffets());
        value.put("codeFamille", unMedic.getFamille());
        bd.update("medicament",value,"numDepotLegal LIKE '%"+unMedic.getNumDepotLegal()+"'",null);
    }


    public Medicament getMedicament(int numDepot){
        Medicament leMedicament = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from medicament where numDepotLegal="+numDepot+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leMedicament = new Medicament(curseur.getInt(0),curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4),curseur.getInt(5));

        }
        return leMedicament;
    }

    public Medicament getMedicament(String nom){
        Medicament leMedicament = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from medicament where nom='"+nom+"';",null);

        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leMedicament = new Medicament(curseur.getInt(0),curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4),curseur.getInt(5));

        }
        return leMedicament;
    }

    public ArrayList<Medicament> getMedicaments(String codeFamille){
        Cursor curseur;
        String req = "select * from medicament where codeFamille like '"+codeFamille+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToComposantArrayList(curseur);
    }

    public int getFamilleId(String lib){
        int idFamille;
        Cursor curseur;
        String req = "select id from famille where lib = '"+lib+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        curseur.moveToFirst();
        idFamille=curseur.getInt(0);
        return idFamille;
    }


    private ArrayList<Medicament> cursorToComposantArrayList(Cursor curseur){
        ArrayList<Medicament> listeMedicament = new ArrayList<Medicament>();
        int numDepot;
        String nom;
        String contreindic;
        float prixEchantillon;
        String effets;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            listeMedicament.add(new Medicament(curseur.getInt(0),curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4),curseur.getInt(5)));
            curseur.moveToNext();
        }

        return listeMedicament;
    }


    public List<String> getAllFamille()
    {
        List<String> familly=new ArrayList<>();
        //get readable database
        SQLiteDatabase db=accesBD.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id,lib FROM famille",null);
        if(cursor.moveToFirst())
        {
            do {
                familly.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the database
        db.close();
        return familly;
    }
}