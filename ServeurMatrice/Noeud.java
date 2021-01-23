import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Noeud {

    public static void addrLocal () {
        String nomHote ;
        String adresseIPLocale ;

        try{
           InetAddress inetadr = InetAddress.getLocalHost();
           //nom de machine
           nomHote = (String) inetadr.getHostName();
           System.out.println("Nom de la machine = "+nomHote );
           //adresse ip sur le réseau
           adresseIPLocale = (String) inetadr.getHostAddress();
           System.out.println("Adresse IP locale = "+adresseIPLocale );
   
        } catch (UnknownHostException e) {
               e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				System.out.println("IoRMatrix SERVEUR Start ...");				
				try {
					boolean ready = true;
					ServerSocket serveurControle = new ServerSocket(3000);
                    ServerSocket serveurMatrice = new ServerSocket(3001);

					Socket clientControle = null;
                    Socket clientMatrice = null;

					while(ready) {
                        System.out.println("Controle connecte ...");
						clientControle = serveurControle.accept();
                        System.out.println("Controle est connecté");
                        
						System.out.println("Matrice connecte ...");
						clientMatrice = serveurMatrice.accept();
                        System.out.println("Matrice est connecté");

                        String s;
                        BufferedReader br = new BufferedReader(new InputStreamReader(clientControle.getInputStream()));
                        PrintStream ps = new PrintStream(clientMatrice.getOutputStream());
                        while(!(s = br.readLine()).equals("exit")) {
                            ps.println(s);
                        }


					}

                    clientMatrice.close();
					clientControle.close();

					serveurMatrice.close();
					serveurControle.close();
				}
				catch(Exception e) {}
			}
		}.start();
	}

}
