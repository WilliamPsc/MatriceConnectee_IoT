package fr.pensec.matriceconnectee;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.GRAY;

public class DeplacementActivity extends AppCompatActivity {

    boolean aff = true;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton upButton;
    ImageButton bottomButton;
    ImageButton centerButton;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    Button deconnexion;
    Button connexion;

    public Socket client;
    String host = "192.168.43.105";
    int port = 8080;
    public PrintStream ps;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deplacement);

        deconnexion = findViewById(R.id.deconnexion1);
        connexion = findViewById(R.id.connexion1);
        leftButton = findViewById(R.id.buttonGauche);
        rightButton = findViewById(R.id.buttonDroite);
        upButton = findViewById(R.id.buttonHaut);
        bottomButton = findViewById(R.id.buttonBas);
        centerButton = findViewById(R.id.buttonCentre);
        disabledCommand();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void disabledCommand(){
        deconnexion.setEnabled(!deconnexion.isEnabled());
        connexion.setEnabled(!connexion.isEnabled());

        leftButton.setEnabled(!leftButton.isEnabled());
        if(leftButton.isEnabled()){
            leftButton.setBackgroundColor(Color.parseColor("#FFC107"));
        } else{
            leftButton.setBackgroundColor(GRAY);
        }

        rightButton.setEnabled(!rightButton.isEnabled());
        if(rightButton.isEnabled()){
            rightButton.setBackgroundColor(Color.parseColor("#FFC107"));
        } else{
            rightButton.setBackgroundColor(GRAY);
        }

        upButton.setEnabled(!upButton.isEnabled());
        if(upButton.isEnabled()){
            upButton.setBackgroundColor(Color.parseColor("#FFC107"));
        } else{
            upButton.setBackgroundColor(GRAY);
        }

        bottomButton.setEnabled(!bottomButton.isEnabled());
        if(bottomButton.isEnabled()){
            bottomButton.setBackgroundColor(Color.parseColor("#FFC107"));
        } else{
            bottomButton.setBackgroundColor(GRAY);
        }

        centerButton.setEnabled(!centerButton.isEnabled());
        if(centerButton.isEnabled()){
            centerButton.setBackgroundColor(Color.parseColor("#3951EF"));
        } else{
            centerButton.setBackgroundColor(GRAY);
        }
    }


    public void connexion (View v) {
        System.out.println("IoRMatrix CLIENT Start ...");
        System.out.print("IoRMatrix_C > ");
        Thread t = new Thread(() -> {
            try {
                client = new Socket(host, port);
                ps = new PrintStream(client.getOutputStream());
                ps.println("init");
            } catch (Exception ignored) {}
        });
        t.start();

        try{
            Thread.sleep(100);
            t.interrupt();
        } catch (InterruptedException ignored) {}

        if (client != null) {
            Toast.makeText(getApplicationContext(), "Connecté !", Toast.LENGTH_SHORT).show();

            disabledCommand();
            addListenerOnButton();
        }else{
            Toast.makeText(getApplicationContext(), "Connexion impossible !", Toast.LENGTH_SHORT).show();
        }
    }

    public void quitter(View v) throws IOException {
        ps.println("exit");
        ps.close();
        client.close();

        if (!client.isConnected()) {
            Toast.makeText(getApplicationContext(), "Déconnexion faite !", Toast.LENGTH_SHORT).show();
            disabledCommand();
        }else{
            Toast.makeText(getApplicationContext(), "Déconnexion impossible !", Toast.LENGTH_SHORT).show();
        }
    }

    public void addListenerOnButton() {

        leftButton.setOnClickListener(v -> {
            System.out.println("Gauche");
            new Thread(() -> {
                try {
                    ps.println("g");
                } catch (Exception e) {
                    System.out.println("Erreur !");
                }
            }).start();
        });

        rightButton.setOnClickListener(v -> {
            System.out.println("Droite");
            new Thread(() -> {
                try {
                    ps.println("d");
                } catch (Exception e) {
                    System.out.println("Erreur !");
                }
            }).start();
        });

        upButton.setOnClickListener(v -> {
            System.out.println("Haut");
            new Thread(() -> {
                try {
                    ps.println("h");
                } catch (Exception e) {
                    System.out.println("Erreur !");
                }
            }).start();
        });

        bottomButton.setOnClickListener(v -> {
            System.out.println("Bas");
            new Thread(() -> {
                try {
                    ps.println("b");
                } catch (Exception e) {
                    System.out.println("Erreur !");
                }
            }).start();
        });

        centerButton.setOnClickListener(v -> new Thread(() -> {
            try {
                ps.println("a");
            } catch (Exception e) {
                System.out.println("Erreur !");
            }
        }).start());
    }
}