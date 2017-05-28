package rodrigues.medicament.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import rodrigues.medicament.Modele.Medicament;
import rodrigues.medicament.Modele.MedicamentDAO;
import rodrigues.medicament.R;

public class ModifierMedicament extends MainActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_medicament);

        //recupération de l'id du médicament
        String id= getIntent().getStringExtra("id");
        final int num = Integer.parseInt(id);

        //recupération du médicament
        final MedicamentDAO medicamentAcces = new MedicamentDAO(this);
        final Medicament medicBdd = medicamentAcces.getMedicament(num);

        //remplissage editText
        final EditText txtId = (EditText) findViewById(R.id.num);
        txtId.clearFocus();
        final EditText txtLib = (EditText) findViewById(R.id.lib);
        final EditText txtContre = (EditText) findViewById(R.id.contre);
        final EditText txtPrix = (EditText) findViewById(R.id.prix);
        final EditText txtEffet = (EditText) findViewById(R.id.effet);
        txtId.setText(String.valueOf(medicBdd.getNumDepotLegal()));
        txtLib.setText(medicBdd.getNom());
        txtContre.setText(medicBdd.getContreindic());
        txtPrix.setText(medicBdd.getPrixEchantillon());
        txtEffet.setText(medicBdd.getEffets());

        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
                Intent liste = new Intent(ModifierMedicament.this, ListerMedic.class);
                startActivity(liste);
            }
        });

        Button buttonComposant = (Button) findViewById(R.id.btnCompo);
        buttonComposant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Medicament leMedic = medicamentAcces.getMedicament(num);
                finish();
                Intent liste = new Intent(ModifierMedicament.this, ListerComposant.class);
                liste.putExtra("id",leMedic.getNumDepotLegal());
                startActivity(liste);
            }
        });

        Button buttonAjout = (Button) findViewById(R.id.btnModifMedic);
        buttonAjout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Medicament leMedic = medicamentAcces.getMedicament(num);
                String lib = txtLib.getText().toString();
                String contre = txtContre.getText().toString();
                String prix = txtPrix.getText().toString();
                String effet = txtEffet.getText().toString();
                Medicament unMedic = new Medicament(leMedic.getNumDepotLegal(),lib,contre,prix,effet,leMedic.getFamille());
                medicamentAcces.majMedicament(unMedic);
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(ModifierMedicament.this, ListerMedic.class);
                startActivity(liste);
            }
        });

        Button buttonSupprimer = (Button) findViewById(R.id.btnSuprMedic);
        buttonSupprimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Medicament leMedic = medicamentAcces.getMedicament(num);
                medicamentAcces.suprMedicament(String.valueOf(leMedic.getNumDepotLegal()));
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(ModifierMedicament.this, ListerMedic.class);
                startActivity(liste);
            }
        });
    }
}