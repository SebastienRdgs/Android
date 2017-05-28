package rodrigues.medicament.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rodrigues.medicament.Modele.Medicament;
import rodrigues.medicament.Modele.MedicamentDAO;
import rodrigues.medicament.R;

public class AjouterMedicament extends MainActivity implements OnItemSelectedListener{
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ajout_medicament);

            Button buttonQuitter = (Button) findViewById(R.id.quitter);
            buttonQuitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();  //fermeture de la fenêtre
                    Intent liste = new Intent(AjouterMedicament.this, ListerMedic.class);
                    startActivity(liste);
                }
            });

            final MedicamentDAO medicamentAcces = new MedicamentDAO(this);
            Button buttonAjout = (Button) findViewById(R.id.btnAjouterMedic);

            prepareData();

            buttonAjout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Spinner spinner = (Spinner) findViewById(R.id.spinner);
                    String fam= null;
                    int famille=0;
                    if(spinner != null && spinner.getSelectedItem() !=null ) {
                        fam = (String)spinner.getSelectedItem();
                        EditText txtId = (EditText) findViewById(R.id.num);
                        EditText txtLib = (EditText) findViewById(R.id.lib);
                        EditText txtContre = (EditText) findViewById(R.id.contre);
                        EditText txtPrix = (EditText) findViewById(R.id.prix);
                        EditText txtEffet = (EditText) findViewById(R.id.effet);
                        int num = Integer.valueOf(txtId.getText().toString());
                        String lib = txtLib.getText().toString();
                        String contre = txtContre.getText().toString();
                        String prix = txtPrix.getText().toString();
                        String effet = txtEffet.getText().toString();
                        famille=medicamentAcces.getFamilleId(fam);
                        Medicament unMedic = new Medicament(num,lib,contre,prix,effet,famille);
                        medicamentAcces.addMedicament(unMedic);
                        finish();
                        //rechargement de l'activité liste pour mettre a jour la BDD
                        Intent liste = new Intent(AjouterMedicament.this, ListerMedic.class);
                        startActivity(liste);
                    } else  {
                        Toast.makeText(getApplicationContext(), "Choisissez une famille",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    public void prepareData()
    {
        ArrayAdapter<String> adapter;
        List<String> familles=new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        MedicamentDAO medicamentAcces = new MedicamentDAO(this);
        familles=medicamentAcces.getAllFamille();
        //adapter for spinner
        adapter=new ArrayAdapter<String>(AjouterMedicament.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,familles);
        //attach adapter to spinner
        spinner.setAdapter(adapter);
    }
}

