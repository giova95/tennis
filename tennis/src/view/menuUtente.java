package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import javax.mail.MessagingException;

import controller.controller;

public class menuUtente {
	public void menu(String username) throws IOException, SQLException, MessagingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(System.in);
		controller c = new controller();
		int choice = 0;
		do{
			try {
				System.out.println("Benvenuto " + username + "!");
				System.out.println("Cosa vuoi fare?");
				System.out.println("1) Crea una nuova prenotazione \n2) Modifica una tua penotazione \n3) Elimina una tua prenotazione \n4) Logout");
				choice = scan.nextInt();
				switch(choice) {
				case 1:
					c.nuovaPrenotazione(username);
					break;
				case 2:
					c.modificaPrenotazioneUtente(username);
					break;
				case 3:
					c.eliminaPrenotazioneUtente(username);
					break;
				case 4:
					System.out.println("Logout effettuato.");
				}
			}
			catch (Exception e) {
				System.out.println("Si è verificato un errore durnte l'ultima operazione, si consiglia di ricontrollare i valori inseriti e di riprovare.");
			}
		}while(choice != 4);
	}
}