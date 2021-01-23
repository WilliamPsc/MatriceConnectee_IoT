package fr.pensec.matriceconnectee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.io.PrintStream;
import java.net.Socket;

public class SwitchLED extends AppCompatActivity {
    public Socket client;
    String host = "192.168.43.105";
    int port = 8080;
    public PrintStream ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchled);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void on(View v){
        Thread t = new Thread(() -> {
            try {
                client = new Socket(host, port);
                ps = new PrintStream(client.getOutputStream());
                ps.print("1");
            } catch (Exception ignored) {}
        });
        t.start();
    }

    public void off(View v){
        Thread t = new Thread(() -> {
            try {
                client = new Socket(host, port);
                ps = new PrintStream(client.getOutputStream());
                ps.print("0");
            } catch (Exception ignored) {}
        });
        t.start();
    }
}