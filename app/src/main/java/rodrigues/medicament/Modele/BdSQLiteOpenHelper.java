package rodrigues.medicament.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper  {

    private String requete="create table famille (id INTEGER PRIMARY KEY, lib TEXT NOT NULL);";
    private String requete1="create table medicament (numDepotLegal INTEGER PRIMARY KEY, nom TEXT NOT NULL, contreindic TEXT NOT NULL, prixEchantillon TEXT NULL, effet TEXT NOT NULL, codeFamille INTEGER NOT NULL, FOREIGN KEY(codeFamille) REFERENCES Famille (id));";
    private String requete2="create table composant (code INTEGER PRIMARY KEY, codeMedic INTEGER, libelle TEXT NOT NULL, FOREIGN KEY(codeMedic) REFERENCES Medicament (numDepotLegal));";


    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(requete);
        db.execSQL(requete1);
        db.execSQL(requete2);


        //Insertion d'un jeu d'essai
        ContentValues value = new ContentValues();
        value.put("id", 1);
        value.put("lib","Antibiotique antituberculeux");
        db.insert("famille", null, value);
        value.put("id", 2);
        value.put("lib","Antalgiques en association");
        db.insert("famille", null, value);

        ContentValues value1 = new ContentValues();
        value1.put("numDepotLegal", 1);
        value1.put("nom","PIRIZAN");
        value1.put("contreindic","Ce médicament est contre-indiqué en cas d\'allergie à  l\'un des constituants, d\'insuffisance rénale ou hépatique, d\'hyperuricémie ou de porphyrie.");
        value1.put("prixEchantillon","2");
        value1.put("effet","Ce médicament est utilisé, en association à  d\'autres antibiotiques, pour traiter la tuberculose.");
        value1.put("codeFamille",1);
        db.insert("medicament", null, value1);

        value1.put("numDepotLegal", 2);
        value1.put("nom","PARMOCODEINE");
        value1.put("contreindic","Ce médicament est contre-indiqué en cas d\'allergie à  l\'un des constituants, chez l\'enfant de moins de 15 Kg, en cas d\'insuffisance hépatique ou respiratoire, d\'asthme, de phénylcétonurie et chez la femme qui allaite.");
        value1.put("prixEchantillon","1");
        value1.put("effet","Ce médicament est utilisé pour le traitement des douleurs lorsque des antalgiques simples ne sont pas assez efficaces.");
        value1.put("codeFamille",2);
        db.insert("medicament", null, value1);


        ContentValues value2 = new ContentValues();
        value2.put("code", 1);
        value2.put("codeMedic",1);
        value2.put("libelle","Pyrazinamide");
        db.insert("composant", null, value2);

        value2.put("code", 2);
        value2.put("codeMedic",2);
        value2.put("libelle","Codeine");
        db.insert("composant", null, value2);
        value2.put("code", 3);
        value2.put("codeMedic",2);
        value2.put("libelle","Paracetamol");
        db.insert("composant", null, value2);

        value1.put("numDepotLegal", 3);
        value1.put("nom","JOVENIL");
        value1.put("contreindic","Ce médicament est contre-indiqué en cas d\'allergie aux macrolides (dont le chef de file est l\'érythromycine).");
        value1.put("prixEchantillon","3");
        value1.put("effet","Ce médicament est utilisé pour traiter des infections bactériennes spécifiques.");
        value1.put("codeFamille",2);
        db.insert("medicament", null, value1);

        value2.put("code", 4);
        value2.put("codeMedic",3);
        value2.put("libelle","Josamycine");
        db.insert("composant", null, value2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS famille");
        db.execSQL("DROP TABLE IF EXISTS medicament");
        db.execSQL("DROP TABLE IF EXISTS composant");
        onCreate(db);
    }
}