import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClientMatrice {

/*
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
*/
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				JFrame frame = new JFrame("IoRMatrix");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(100, 100, 600, 600);				
				MyMatrix matrix = new MyMatrix();
				frame.add(matrix);
				frame.setVisible(true);
				System.out.println("IoRMatrix SERVEUR Start ...");
				
				try {
					boolean ready = true;

					Socket client = new Socket("172.28.189.240", 3001); //A changer

					while(ready) {
						System.out.println("ClientMatrice START ");
						System.out.print("IoRMatrix_S > ");
						matrix.afficher();
						matrix.init();
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						String s ;
						while(!(s = br.readLine()).equals("exit")) {
							if(s.equals("d")) { System.out.println("DROITE"); matrix.incX(); System.out.print("IoRMatrix_S > "); }
							if(s.equals("g")) { System.out.println("GAUCHE"); matrix.decX(); System.out.print("IoRMatrix_S > "); }
							if(s.equals("b")) { System.out.println("BAS"); matrix.incY(); System.out.print("IoRMatrix_S > "); }
							if(s.equals("h")) { System.out.println("HAUT"); matrix.decY(); System.out.print("IoRMatrix_S > "); }
							if(s.equals("init")) { System.out.println("INIT"); matrix.init(); System.out.print("IoRMatrix_S > "); }
						}
						matrix.cacher();
						System.out.println("Bye");	
					}
					frame.dispose();
					client.close();
				}
				catch(Exception e) {}
			}
		}.start();
	}

}
