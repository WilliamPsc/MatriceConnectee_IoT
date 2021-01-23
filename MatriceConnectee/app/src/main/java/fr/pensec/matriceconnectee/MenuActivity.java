package fr.pensec.matriceconnectee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    // Lance l'application réelle
    public void deplacementLaunched(View d){
        Intent intent = new Intent(this, DeplacementActivity.class);
        startActivity(intent);
    }

    // Lancement de la matrice
    public void lancementMatrix(View d){
        Intent intent = new Intent(this, VueMatrice.class);
        startActivity(intent);
    }

    // Lance le test d'allumage/eteindre une LED
    public void led(View d){
        Intent intent = new Intent(this, SwitchLED.class);
        startActivity(intent);
    }

    // Lance les paramètres
    public void settings(View d){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}