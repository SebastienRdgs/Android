package rodrigues.medicament.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rodrigues.medicament.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rodrigues.medicament.R.layout.activity_main);
        Button buttonMedic = (Button)findViewById(R.id.btnSrchMedic);
        Button buttonFamille = (Button)findViewById(R.id.btnSrchFamille);
        //On crée un écouteur d'évènement commun au deux Button
        View.OnClickListener onClickLister = new View.OnClickListener() {


            // OnClickListener onClickLister = new OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.btnSrchFamille:
                        Intent fam = new Intent(MainActivity.this, ListerFamille.class);
                        startActivity(fam);
                        break;
                    case R.id.btnSrchMedic:
                        Intent medic = new Intent(MainActivity.this, ListerMedic.class);
                        startActivity(medic);
                        break;
                }
            }
        };
        buttonFamille.setOnClickListener(onClickLister); //déclaration d’un objet source d’évênement
        buttonMedic.setOnClickListener(onClickLister);
    }
}
