package view;

import controller.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import javax.mail.MessagingException;

public class menuFacade {
	
	public void menu() throws IOException, SQLException, InterruptedException, MessagingException {
		Scanner scan = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		menuUtente u = new menuUtente();
		menuIstruttore i = new menuIstruttore();
		menuGestore g = new menuGestore();
		controller c = new controller();
		boolean terminate = false;
		int choice;
		
		while(!terminate) {
			System.out.println("Benvenuto nel Circolo di Tennis Universitario!");
			System.out.println("1) se non hai ancora un account registrati \n2) login \n3) Exit");
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				c.registrazione();
				break;
			case 2:	
				String client = c.login();
				System.out.println(client);
				if(client.contains("U"))
					u.menu(client);
				else if(client.contains("I"))
					i.menu(client);
				else if (client.contains("G"))
					g.menu(client);
				else
					System.out.println("Le credenziali inserite sono sbagliate o non corrispondono");	
				break;
			case 3:
				terminate = true;
				break;
		}
	
	}
}
}
