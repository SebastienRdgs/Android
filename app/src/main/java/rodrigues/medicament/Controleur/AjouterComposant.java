package rodrigues.medicament.Controleur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import rodrigues.medicament.Modele.Composant;
import rodrigues.medicament.Modele.ComposantDAO;
import rodrigues.medicament.R;

public class AjouterComposant extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_composant);

        //Récupération de l'id du médicament
        final int idMedic= getIntent().getIntExtra("id",0);

        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
                Intent liste = new Intent(AjouterComposant.this, ListerComposant.class);
                startActivity(liste);
            }
        });

        final ComposantDAO composantAcces = new ComposantDAO(this);
        Button buttonAjout = (Button) findViewById(R.id.btnAjouterCompo);
        buttonAjout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtId = (EditText) findViewById(R.id.num);
                EditText txtLib = (EditText) findViewById(R.id.lib);
                int id = Integer.valueOf(txtId.getText().toString());
                String lib = txtLib.getText().toString();
                Composant unCompo = new Composant(id,lib,idMedic);
                //Ajout du composant a la BDD
                composantAcces.addComposant(unCompo);
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(AjouterComposant.this, ListerComposant.class);
                liste.putExtra("id",idMedic);
                startActivity(liste);
            }
        });
    }
}