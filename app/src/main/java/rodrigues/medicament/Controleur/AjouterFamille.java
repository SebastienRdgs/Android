package rodrigues.medicament.Controleur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import rodrigues.medicament.Modele.Famille;
import rodrigues.medicament.Modele.FamilleDAO;
import rodrigues.medicament.R;

public class AjouterFamille extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_famille);

        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
                Intent liste = new Intent(AjouterFamille.this, ListerFamille.class);
                startActivity(liste);
            }
        });

        final FamilleDAO familleAcces = new FamilleDAO(this);
        Button buttonAjout = (Button) findViewById(R.id.btnAjouterFam);
        buttonAjout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtId = (EditText) findViewById(R.id.num);
                EditText txtLib = (EditText) findViewById(R.id.lib);
                int id = Integer.valueOf(txtId.getText().toString());
                String lib = txtLib.getText().toString();
                Famille uneFamille = new Famille(id,lib);
                //Ajout de la famille a la BDD
                familleAcces.addFamille(uneFamille);
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(AjouterFamille.this, ListerFamille.class);
                startActivity(liste);
            }
        });
    }
}