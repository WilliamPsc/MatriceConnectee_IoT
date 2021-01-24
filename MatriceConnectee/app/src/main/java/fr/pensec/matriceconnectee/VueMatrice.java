package fr.pensec.matriceconnectee;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import androidx.appcompat.app.AppCompatActivity;

public class VueMatrice extends AppCompatActivity {

    Button connexion;
    Button deconnexion;
    MyMatrix matrice;
    public Socket client;
    String host = "192.168.43.105";
    int port = 8081;
    boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_matrice);

        connexion = findViewById(R.id.connexion2);
        deconnexion = findViewById(R.id.deconnexion2);
        matrice = findViewById(R.id.maMatrice);

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

    public void connexion (View v){
        System.out.println("IoRMatrix CLIENT Start ...");
        System.out.print("IoRMatrix_C > ");
        Thread t = new Thread(() -> {
            try {
                client = new Socket(host, port);
                ready = true;
            } catch (Exception ignored) {}
        });
        t.start();

        try{
            Thread.sleep(300);
            t.interrupt();
        } catch (InterruptedException ignored) {}

        if (client != null) {
            Toast.makeText(getApplicationContext(), "Connecté !", Toast.LENGTH_SHORT).show();

            connexion.setEnabled(false);
            deconnexion.setEnabled(true);
        }else{
            Toast.makeText(getApplicationContext(), "Connexion impossible !", Toast.LENGTH_SHORT).show();
        }

        new Thread(()->{
            while(ready) {
                System.out.println("ClientMatrice START ");
                System.out.print("IoRMatrix_C > ");
                matrice.afficher();
                matrice.init();
                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String s ;
                    while(!(s = br.readLine()).equals(("exit"))) {
                        if(s.length() == 0){
                            System.out.println("Message vide");
                        } else if (Character.isDigit(s.charAt(0))) {
                            System.out.println("Cordonnées reçu");
                            int posX = Integer.parseInt(s);
                            System.out.print("x:" + posX);
                            s = br.readLine();
                            int posY = Integer.parseInt(s);
                            System.out.println("\t y:" + posY);
                            matrice.changeCoordonnees(posX,7 -  posY); // Inversement de position car système coordonnées différentes entre ARDUINO & ANDROID
                            System.out.print("IoRMatrix_C > ");
                        } else {
                            if (s.equals("d")) {
                                System.out.println("DROITE");
                                matrice.incX();
                                System.out.print("IoRMatrix_C > ");
                            }
                            if (s.equals("g")) {
                                System.out.println("GAUCHE");
                                matrice.decX();
                                System.out.print("IoRMatrix_C > ");
                            }
                            if (s.equals("b")) {
                                System.out.println("BAS");
                                matrice.incY();
                                System.out.print("IoRMatrix_C > ");
                            }
                            if (s.equals("h")) {
                                System.out.println("HAUT");
                                matrice.decY();
                                System.out.print("IoRMatrix_C > ");
                            }
                            if (s.equals("a")) {
                                System.out.println("Afficher/Masquer");
                                matrice.afficher();
                                System.out.print("IoRMatrix_C > ");
                            }
                            if (s.equals("init")) {
                                System.out.println("INIT");
                                matrice.init();
                                System.out.print("IoRMatrix_C > ");
                            }
                        }
                    }
                }catch(Exception e){e.printStackTrace();}
                ready = false;
                System.out.println("Bye");
            }
        }).start();
    }

    public void quitter(View v) {
        try {
            matrice.cacher();
            client.close();


            if (!client.isConnected()) {
                Toast.makeText(getApplicationContext(), "Déconnexion faite !", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Déconnexion impossible !", Toast.LENGTH_SHORT).show();
            }

            matrice.exit();
        } catch (Exception ignored) {}
    }
}