package rodrigues.medicament.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import rodrigues.medicament.Modele.Famille;
import rodrigues.medicament.Modele.FamilleDAO;
import rodrigues.medicament.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListerFamille extends Activity{

    private ListView listViewFamille;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.famille);

        Button buttonAdd = (Button)findViewById(R.id.btnAjouterFamille);
        Button buttonModif = (Button)findViewById(R.id.btnModifFamille);
        //On crée un écouteur d'évènement commun au deux Button
        View.OnClickListener onClickLister = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.btnAjouterFamille:
                        Intent addFam = new Intent(ListerFamille.this, AjouterFamille.class);
                        finish();
                        startActivity(addFam);
                        break;
                    case R.id.btnModifFamille:
                        Intent modifFam = new Intent(ListerFamille.this, ModifierFamille.class);
                        finish();
                        startActivity(modifFam);
                        break;
                }
            }
        };
        buttonAdd.setOnClickListener(onClickLister); //déclaration d’un objet source d’évênement
        buttonModif.setOnClickListener(onClickLister);

        //Récupération de la listview
        listViewFamille = (ListView) findViewById(R.id.listViewFamille);

        // récupération des données
        final FamilleDAO familleAcces = new FamilleDAO(this);
        ArrayList<Famille> listeFamille;
        listeFamille = familleAcces.getFamilles("%");

        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //essai foreach
        for (Famille uneFamille : listeFamille) {
            map = new HashMap<String, String>();
            map.put("num", String.valueOf(uneFamille.getId()));
            map.put("lib", uneFamille.getLib());
            listItem.add(map);
        }


        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.tpl_famille,

                new String[]{"num", "lib"}, new int[]{R.id.num, R.id.lib});

        //On attribue à notre listView l'adapter que l'on vient de créer
        listViewFamille.setAdapter(mSchedule);

        listViewFamille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                final HashMap<String, String> map = (HashMap<String, String>) listViewFamille.getItemAtPosition(position);
                Intent listeFamille = new Intent(ListerFamille.this, ListerMedic.class);
                listeFamille.putExtra("id", map.get("num"));
                finish();
                startActivity(listeFamille);
            }
        });
    }
}
