package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import controller.controller;
import model.istruttore;

public class menuIstruttore {
	public void menu(String username) throws IOException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(System.in);
		controller c = new controller();
		int choice;
		do {
			System.out.println("Benvenuto " + username + "!");
			System.out.println("Cosa vuoi fare?");
			System.out.println(
					"1) Crea una nuova prenotazione \n2) Modifica una tua penotazione \n3) Elimina una tua prenotazione \n4) Calcola stipendio \n5) Logout");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				c.nuovaPrenotazione();
				break;
			case 2:
				c.modificaPrenotazioneUtente(username);
				break;
			case 3:
				c.eliminaPrenotazioneUtente(username);
				break;
			case 4:
				String idString = username.substring(1);
				int id = Integer.parseInt(idString);
				getIstruttore(id);
			case 5:
				System.out.println("Logout effettuato.");
			}
		} while (choice != 4);
	}
	
	private istruttore getIstruttore(int id) {
		controller c = new controller();
		istruttore i = c.selezionaIstruttore(id);
		return i;
	}
}
