package rodrigues.medicament.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import rodrigues.medicament.Modele.Medicament;
import rodrigues.medicament.Modele.MedicamentDAO;
import rodrigues.medicament.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListerMedic extends Activity{

    private ListView listViewMedicament;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicament);

        Button buttonAdd = (Button)findViewById(R.id.btnAjouterMedicament);
        View.OnClickListener onClickLister = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.btnAjouterMedicament:
                        Intent addMedic = new Intent(ListerMedic.this, AjouterMedicament.class);
                        finish();
                        startActivity(addMedic);
                        break;
                }
            }
        };
        buttonAdd.setOnClickListener(onClickLister); //déclaration d’un objet source d’évênement

        //Récupération de la listview
        listViewMedicament = (ListView) findViewById(R.id.listViewMedicament);

        // récupération des données
        final MedicamentDAO medicamentAcces = new MedicamentDAO(this);
        ArrayList<Medicament> listeMedicament;

        String idFam= getIntent().getStringExtra("id");
        if(idFam==null) {
            listeMedicament = medicamentAcces.getMedicaments("%");
        }else{
            listeMedicament = medicamentAcces.getMedicaments(String.valueOf(idFam));
        }

        //Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //essai foreach
        for (Medicament unMedic : listeMedicament) {
            map = new HashMap<String, String>();
            map.put("numDepotLegal", String.valueOf(unMedic.getNumDepotLegal()));
            map.put("nom", unMedic.getNom());
            listItem.add(map);
        }

        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.tpl_medicament,

                new String[]{"numDepotLegal", "nom"}, new int[]{R.id.num, R.id.nom});

        //On attribue à notre listView l'adapter que l'on vient de créer
        listViewMedicament.setAdapter(mSchedule);

        listViewMedicament.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                final HashMap<String, String> map = (HashMap<String, String>) listViewMedicament.getItemAtPosition(position);
                Intent modifMedic = new Intent(ListerMedic.this, ModifierMedicament.class);
                modifMedic.putExtra("id", map.get("numDepotLegal"));
                finish();
                startActivity(modifMedic);
            }
        });
    }
}
