package view;

import controller.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class menuGestore {

	public void menu(String u) throws InterruptedException, IOException, SQLException {
		Scanner scanner = new Scanner(System.in);
		controller con = new controller();
		int c;
		do {
			System.out.println("Benvenuto " + u);
			System.out.println("1)Gestione Utenti \n2)Gestione Prenotazioni \n3)Gestione Campi \n4)Contabilità \n5)LogOut");
			System.out.println("Scegli una delle opzioni[1-5]");
			c = scanner.nextInt();

			switch (c) {
			case 1:
				gestioneUtenti();
				break;
			case 2:
				gestionePrenotazioni();
				break;
			case 3:
				gestioneCampi();
			case 4:
				gestioneContabilita();
				break;
			case 5:
				System.out.println("LogOut in corso...");
				break;
			}
		} while (c != 5);

	}

	public void gestioneUtenti() throws IOException, SQLException {
		Scanner scanner = new Scanner(System.in);
		controller con = new controller();
		int c;
		do {
			System.out.println("1)Inserisci nuovo utente \n2)Modifica utente esistente \n3)Elimina utente \n4)HomePage");
			System.out.println("Scegli una delle opzioni[1-4]:");
			c = scanner.nextInt();
			switch (c) {
			case 1:
				con.registrazione();
				break;
			case 2:
				con.modificaUtente();
				break;
			case 3:
				con.eliminaUtente();
				break;
			case 4:
				System.out.println("Caricamento Homepage...");
				break;
			}
		} while (c != 4);
	}

	public void gestionePrenotazioni() throws IOException, SQLException{
		Scanner scanner = new Scanner(System.in);
		controller con = new controller();
		int c;
		do {
			System.out.println("1)Inserisci nuova prenotazione \n2)Modifica Prenotazione \n3)Elimina Prenotazione \n4)Fissa evento \n5)HomePage");
			System.out.println("Scegli una delle opzioni[1-4]:");
			c = scanner.nextInt();
			switch (c) {
			case 1:
				con.nuovaPrenotazione();
				break;
			case 2:
				con.modificaPrenotazione();
				break;
			case 3:
				con.eliminaPrenotazione(); 
				break;
			case 4:
				con.fissaEvento();
			case 5:
				System.out.println("Caricamento Homepage...");
				break;
			}
		} while (c != 4);
		
	}
	
	public void gestioneCampi() throws IOException, SQLException{
		Scanner scanner = new Scanner(System.in);
		controller con = new controller();
		int c;
		do {
			System.out.println("1)Inserisci nuovo campo \n2)Modifica campo \n3)Elimina campo \n4)HomePage");
			System.out.println("Scegli una delle opzioni[1-4]:");
			c = scanner.nextInt();
			switch (c) {
			case 1:
				con.aggiungiCampo();
				break;
			case 2:
				con.modificaCampo();
				break;
			case 3:
				con.eliminaCampo();
				break;
			case 4:
				System.out.println("Caricamento Homepage...");
				break;
			}
		} while (c != 4);
		
	}

	public void gestioneContabilita() throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		controller con = new controller();
		int c;
		String data;
		do {
			System.out.println("1)Visualizza Profitti \n2)HomePage");
			System.out.println("Scegli una delle opzioni[1-2]:");
			c = scanner.nextInt();
			switch (c) {
			case 1:
				System.out.println("Digita il mese e l'anno in cui vuoi vedere i profitti[YYYY-MM]");
				data = br.readLine();
				float[] profitti = con.calcolaProfitti(data);
				System.out.println("1)Profitti: "+ profitti[0]+"€");
				System.out.println("2)Costi: "+profitti[1]+"€");
				System.out.println("3)Ricavi: "+ profitti[2]+"€");
				break;
			case 2:
				System.out.println("Caricamento Homepage...");
				break;
			}
		} while (c != 2);
	}
}
