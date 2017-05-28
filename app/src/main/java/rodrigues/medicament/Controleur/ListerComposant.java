package rodrigues.medicament.Controleur;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import rodrigues.medicament.Modele.Composant;
import rodrigues.medicament.Modele.ComposantDAO;
import rodrigues.medicament.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListerComposant extends Activity{

    private ListView listViewCompo;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composant);

        final int idMedic= getIntent().getIntExtra("id",0);

        Button buttonAdd = (Button)findViewById(R.id.btnAjouterCompo);
        View.OnClickListener onClickLister = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.btnAjouterCompo:
                        Intent addCompo = new Intent(ListerComposant.this, AjouterComposant.class);
                        addCompo.putExtra("id",idMedic);
                        finish();
                        startActivity(addCompo);
                        break;
                }
            }
        };
        buttonAdd.setOnClickListener(onClickLister); //déclaration d’un objet source d’évênement

        //Récupération de la listview
        listViewCompo = (ListView) findViewById(R.id.listViewCompo);

        // récupération des données
        final ComposantDAO compoAcces = new ComposantDAO(this);
        ArrayList<Composant> listeCompo;
        listeCompo = compoAcces.getComposants(String.valueOf(idMedic));

        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //essai foreach
        for (Composant unComposant : listeCompo) {
            map = new HashMap<String, String>();
            map.put("id", String.valueOf(unComposant.getCode()));
            map.put("lib", unComposant.getLib());
            listItem.add(map);
        }

        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.tpl_composant,

                new String[]{"id", "lib"}, new int[]{R.id.id, R.id.lib});

        //On attribue à notre listView l'adapter que l'on vient de créer
        listViewCompo.setAdapter(mSchedule);

        listViewCompo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                final HashMap<String, String> map = (HashMap<String, String>) listViewCompo.getItemAtPosition(position);
                //on créé une boite de dialogue
                final AlertDialog.Builder adb = new AlertDialog.Builder(ListerComposant.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle("Sélection composant");
                //on insère un message à notre boite de dialogue, et ici on affiche la designation de l'item cliqué
                adb.setMessage("Votre choix : "+map.get("lib"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.setNegativeButton("Supprimer",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface d,int id){
                        compoAcces.suprComposant(map.get("id").toString());
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                } );
                adb.show();
            }
        });
    }
}