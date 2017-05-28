package rodrigues.medicament.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import rodrigues.medicament.Modele.Famille;
import rodrigues.medicament.Modele.FamilleDAO;
import rodrigues.medicament.R;

public class ModifierFamille extends MainActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_famille);

        Button buttonQuitter = (Button) findViewById(R.id.quitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  //fermeture de la fenêtre
                Intent liste = new Intent(ModifierFamille.this, ListerFamille.class);
                startActivity(liste);
            }
        });

        final FamilleDAO familleAcces = new FamilleDAO(this);
        Button buttonModif = (Button) findViewById(R.id.btnModifFamille);
        buttonModif.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtId = (EditText) findViewById(R.id.num);
                EditText txtLib = (EditText) findViewById(R.id.lib);
                int id = Integer.valueOf(txtId.getText().toString());
                String lib = txtLib.getText().toString();
                Famille uneFamille = new Famille(id,lib);
                familleAcces.majFamille(uneFamille);
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(ModifierFamille.this, ListerFamille.class);
                startActivity(liste);
            }
        });

        Button buttonSupr = (Button) findViewById(R.id.btnSuprFamille);
        buttonSupr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText txtId = (EditText) findViewById(R.id.num);
                EditText txtLib = (EditText) findViewById(R.id.lib);
                int id = Integer.valueOf(txtId.getText().toString());
                String lib = txtLib.getText().toString();
                Famille uneFamille = new Famille(id,lib);
                familleAcces.suprFamille(String.valueOf(id));
                finish();
                //rechargement de l'activité liste pour mettre a jour la BDD
                Intent liste = new Intent(ModifierFamille.this, ListerFamille.class);
                startActivity(liste);
            }
        });
    }
}
