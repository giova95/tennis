package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import model.campo;
import model.gestore;
import model.istruttore;
import model.prenotazione;
import model.utente;

public class controller {
	
	public void controller() {
		
	}

	public void registrazione() throws IOException, SQLException {

		databaseDAO dao = new databaseDAO();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inserisci il tuo nome: ");
		String nome = br.readLine();

		System.out.println("Inserisci il tuo cognome: ");
		String cognome = br.readLine();

		System.out.println("Inserisci la tua email: ");
		String email = br.readLine();

		System.out.println("Inserisci la tua password: ");
		String password = br.readLine();

		System.out.println("Inserisci la tua età: ");
		String e = br.readLine();
		int eta = Integer.parseInt(e);

		System.out.println("Inserisci il tuo numero di telefono: ");
		String numero = br.readLine();

		System.out.println("Inserisci il tuo sesso: ");
		String s = br.readLine();
		char sesso = s.charAt(0);

		String username = "U" + (int) (Math.random() * 9) + (int) (Math.random() * 9) + (int) (Math.random() * 9);

		utente u = new utente(0, nome, cognome, eta, email, numero, username, password, sesso);
		dao.insertUser(u);
	}

	public String login() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String response = "errore";
		System.out.println("Inserisci il tuo username");
		String username = br.readLine();
		System.out.println("Inserisci la tua password");
		String password = br.readLine();

		// get users, instructors and manager from DB
		databaseDAO dao = new databaseDAO();
		List<utente> users = dao.selectUsers();
		List<istruttore> istru = dao.selectInstructors();
		List<gestore> manag = dao.selectManager();

		if (username.contains("U")) {
			for (int i = 0; i < users.size(); i++) {
				if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword()))
					response = username;
			}
		}

		else if (username.contains("I")) {
			for (int i = 0; i < istru.size(); i++) {
				if (username == istru.get(i).getUsername() && password == istru.get(i).getPassword())
					response = username;
			}
		}

		else if (username.contains("G")) {
			for (int i = 0; i < manag.size(); i++) {
				if (username == manag.get(i).getUsername() && password == manag.get(i).getPassword())
					response = username;
			}
		}

		return response;
	}

	public void nuovaPrenotazione() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean valid = false;
		List<istruttore> istruttori = dao.selectInstructors();
		int istruttore = 0;
		String partecipanti = "";
		int tipoP = 0;

		while (!valid) {
			System.out.println("Scegli il tipo di prenotazione (0 -> senza istruttore, 1 -> con istruttore): ");
			String t = br.readLine();
			tipoP = Integer.parseInt(t);
			if (tipoP == 0) {
				valid = true;
			} else if (tipoP == 1) {
				valid = true;
				for (int i = 0; i < istruttori.size(); i++) {
					System.out.println(istruttori.get(i).getId() + ") " + istruttori.get(i).getNome() + " "
							+ istruttori.get(i).getCognome() + " Esperienza: " + istruttori.get(i).getEsperienza());
				}
				System.out.println("Digita il numero dell'istruttore desiderato: ");
				String i = br.readLine();
				istruttore = Integer.parseInt(i);
			} else {
				System.out.println("Inserimento non valido!");
			}
		}

		valid = false;
		while (!valid) {
			System.out.println("Inserisci gli username dei partecipanti: ");
			partecipanti = br.readLine();
			List<utente> utenti = dao.selectUsers();
			boolean trovato = false;
			int i = 0;
			while (i < utenti.size() && !trovato) {
				trovato = partecipanti.contains(utenti.get(i).getUsername());
				i++;
			}
			if (!trovato) {
				System.out.println("Non è stata trovata corrispondenza per un username");
			} else {
				valid = true;
			}
		}
		System.out.println("Inserisci la data e l'ora (es. YYYY-MM-DD HH): ");
		String dataOra = br.readLine();
		dataOra = dataOra + ":00:00"; // aggiunge minuti e secondi all'orario

		System.out.println("Inserisci la tipologia di terreno (terra, erba, cemento): ");
		String tipo = br.readLine();
		System.out.println(tipo);
		List<campo> campi = dao.selectFields();
		for (int i = 0; i < campi.size(); i++) {
			if (tipo.equals(campi.get(i).getTipo())) {
				System.out.println(campi.get(i).getId() + ") valutazione: " + campi.get(i).getValutazione()
						+ " prezzo: " + campi.get(i).getPrezzo() + "0 €");
			}
		}
		System.out.println("Inserisci l'id del campo desiderato: ");
		String c = br.readLine();
		int campo = Integer.parseInt(c);

		System.out.println("Inserisci il numero di ore della prenotazione (1 o 2): ");
		String d = br.readLine();
		System.out.println(d);
		int durata = Integer.parseInt(d);
		istruttore ist = null;
		
		for(int i=0;i<istruttori.size();i++) {
			if(istruttori.get(i).getId() == istruttore) {
				ist = istruttori.get(i);
			}
		}
		ist.setOreLezione(ist.getOreLezione() + durata);
		dao.updateInstru(ist);

		////////////////////////////////////////////////////////////////////////////////////
		// METODO DI TARIFFARIO PER IL CALCOLO DEL PREZZO
		float prezzoIstr = 0;
		float prezzoLuci = 0;
		float prezzoCampo = 0;
		float totale;

//		List<istruttore> istruttori = dao.selectInstructors();
//		List<campo> campi = dao.selectFields();

		for (int i = 0; i < istruttori.size(); i++) {
			if (istruttori.get(i).getId() == istruttore) {
				prezzoIstr = istruttori.get(i).getPagaOraria();
			}
		}

		for (int i = 0; i < campi.size(); i++) {
			if (campi.get(i).getId() == campo) {
				prezzoCampo = campi.get(i).getPrezzo();
			}
		}

		String oraString = dataOra.substring(11, 13);
		int ora = Integer.parseInt(oraString);

		if (ora > 19) {
			prezzoLuci = 10;
		}

		totale = prezzoLuci + prezzoIstr + prezzoCampo;

		////////////////////////////////////////////////////////////////////////////////////
		if (durata == 1) {
			if(istruttore == 0) {
				prenotazione p = new prenotazione(0, dataOra, durata, totale, partecipanti, campo, istruttore, tipoP);
				dao.insertReservNoIstr(p);
			}
			else {
				prenotazione p = new prenotazione(0, dataOra, durata, totale, partecipanti, campo, istruttore, tipoP);
				dao.insertReserv(p);
			}
		} else if (durata == 2) {
			int ora2 = ora + 1;
			String dataOra2 = dataOra.substring(0, 11) + ora2 + ":00:00";
			if(istruttore == 0) {
				prenotazione p1 = new prenotazione(0, dataOra, durata, totale, partecipanti, campo, istruttore, tipoP);
				prenotazione p2 = new prenotazione(0, dataOra2, durata, totale, partecipanti, campo, istruttore, tipoP);
				dao.insertReservNoIstr(p1);
				dao.insertReservNoIstr(p2);
			}
			else {
				prenotazione p1 = new prenotazione(0, dataOra, durata, totale, partecipanti, campo, istruttore, tipoP);
				prenotazione p2 = new prenotazione(0, dataOra2, durata, totale, partecipanti, campo, istruttore, tipoP);
				dao.insertReserv(p1);
				dao.insertReserv(p2);
			}
		}

	}

	public void modificaPrenotazione() throws SQLException, IOException {
		databaseDAO dao = new databaseDAO();
		List<prenotazione> prenotazioni = dao.selectReserv();
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int prenotazione = 0;
		
		for(int i=0; i < prenotazioni.size() ; i++) {
			System.out.println(prenotazioni.get(i).getId() + ") " + "Data e ora: " + prenotazioni.get(i).getDataOra() + " Durata: " + prenotazioni.get(i).getDurata());
		}
		System.out.println("Digita il numero della prenotazione che si desidera modificare: ");
		String pre = br.readLine();
		prenotazione = Integer.parseInt(pre);
		
		System.out.println("Inserisci la data e l'ora (es. YYYY-MM-DD HH): ");
		String dataOra = br.readLine();
		dataOra = dataOra + ":00:00";
		
		System.out.println("Inserisci il numero di ore della prenotazione (1 o 2): ");
		String d = br.readLine();
		System.out.println(d);
		int durata = Integer.parseInt(d);
		
		
		////////////////////////////////////////////////////////////////////////////////////
		// METODO DI TARIFFARIO PER IL CALCOLO DEL PREZZO
		float prezzoIstr = 0;
		float prezzoLuci = 0;
		float prezzoCampo = 0;
		float totale;
		int campo = 0;
		int istruttore = 0;
		
		List<istruttore> istruttori = dao.selectInstructors();
		List<campo> campi = dao.selectFields();
		
		for(int j=0; j < prenotazioni.size(); j++) {
			if(prenotazioni.get(j).getId() == prenotazione) {
				istruttore = prenotazioni.get(j).getIstruttore();
				campo = prenotazioni.get(j).getCampo();
			}
		}
		
		for(int j=0; j < istruttori.size() ; j++) {
			if(istruttori.get(j).getId() == istruttore) {
				prezzoIstr = istruttori.get(j).getPagaOraria();
			}
		}
		
		for(int j=0; j < campi.size() ; j++) {
			if(campi.get(j).getId() == campo) {
				prezzoCampo = campi.get(j).getPrezzo();
			}
		}
		
		String oraString = dataOra.substring(11, 13);
		int ora = Integer.parseInt(oraString);
		
		if(ora > 19) {
			prezzoLuci = 10;
		}
		
		totale = prezzoLuci + prezzoIstr + prezzoCampo;
		
		////////////////////////////////////////////////////////////////////////////////////
		
		int durataOld = 0;
		int idElimina = 0;
		
		for(int i=0; i < prenotazioni.size(); i++) {
			if(prenotazione == prenotazioni.get(i).getId()) {
				durataOld = prenotazioni.get(i).getDurata();
				idElimina = prenotazioni.get(i+1).getId();
			}
		}
		
		if(durata != durataOld) {
			if(durata == 1) {
				prenotazione p = new prenotazione(prenotazione, dataOra, durata, totale, "", campo, istruttore, 0);
				dao.updateReserv(p);
				dao.deleteReserv(idElimina);
			}
			else if(durata == 2) {
				int ora2 = ora + 1;
				String dataOra2 = dataOra.substring(0, 11) + ora2 + ":00:00";
				prenotazione p1 = new prenotazione(0, dataOra, durata, totale, "", campo, istruttore, 0);
				prenotazione p2 = new prenotazione(0, dataOra2, durata, totale, "", campo, istruttore, 0);
				dao.updateReserv(p1);
				if(istruttore == 0) {
					dao.insertReservNoIstr(p2);
				}
				else {
					dao.insertReserv(p2);
				}
			}
		}
		else {
			prenotazione p = new prenotazione(prenotazione, dataOra, durata, totale, "", campo, istruttore, 0);
			dao.updateReserv(p);
		}
	}
	
	public void eliminaPrenotazione() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<prenotazione> reserv = dao.selectReserv();
		List<istruttore> istruttori = dao.selectInstructors();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("LE TUE PRENOTAZIONI");
		for (int i = 0; i < reserv.size(); i++) {
			System.out.println("codice: " + reserv.get(i).getId());
			System.out.println("partecipanti: " + reserv.get(i).getPartecipanti());
			System.out.println("data e ora: " + reserv.get(i).getDataOra());
			System.out.println("campo: " + reserv.get(i).getCampo());
			System.out.println("istuttore: " + reserv.get(i).getIstruttore());
		}

		System.out.println("Digita il codice della prenotazione da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		
		istruttore ist = null;
		
		for(int i=0;i<istruttori.size();i++) {
			if(istruttori.get(i).getId() == codice) {
				ist = istruttori.get(i);
			}
		}
		
		int durata = 0;
		
		for(int i=0;i<reserv.size();i++) {
			if(reserv.get(i).getIstruttore() == codice) {
				durata = reserv.get(i).getDurata();
			}
		}
		
		ist.setOreLezione(ist.getOreLezione() - durata);
		dao.updateInstru(ist);
		
		if (dao.deleteReserv(codice)) {
			System.out.println("Prenotazione eliminato correttamente");
		}
		;

	}
	
	public void modificaPrenotazioneUtente(String username) throws SQLException, IOException {
		databaseDAO dao = new databaseDAO();
		List<prenotazione> prenotazioni = dao.selectReserv();
		List<prenotazione> myPrenot = new ArrayList<>();		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int prenotazione = 0;
		String[] partecipanti;
		String p;
		
		for(int i=0; i < prenotazioni.size() ; i++) {
			p = prenotazioni.get(i).getPartecipanti();
			partecipanti = p.split(",");
			for(int j=0; j<partecipanti.length; j++) {
				if(username.equals(partecipanti[j])) {
					myPrenot.add(prenotazioni.get(i));
				}
			}
		}

		for(int i=0; i < myPrenot.size() ; i++) {
			System.out.println(myPrenot.get(i).getId() + ") " + "Data e ora: " + myPrenot.get(i).getDataOra() + " Durata: " + myPrenot.get(i).getDurata());
		}
		System.out.println("Digita il numero della prenotazione che si desidera modificare: ");
		String pre = br.readLine();
		prenotazione = Integer.parseInt(pre);
		
		System.out.println("Inserisci la data e l'ora (es. YYYY-MM-DD HH): ");
		String dataOra = br.readLine();
		dataOra = dataOra + ":00:00";
		
		System.out.println("Inserisci il numero di ore della prenotazione (1 o 2): ");
		String d = br.readLine();
		System.out.println(d);
		int durata = Integer.parseInt(d);
		
		
		////////////////////////////////////////////////////////////////////////////////////
		// METODO DI TARIFFARIO PER IL CALCOLO DEL PREZZO
		float prezzoIstr = 0;
		float prezzoLuci = 0;
		float prezzoCampo = 0;
		float totale;
		int campo = 0;
		int istruttore = 0;
		
		List<istruttore> istruttori = dao.selectInstructors();
		List<campo> campi = dao.selectFields();
		
		for(int j=0; j < prenotazioni.size(); j++) {
			if(prenotazioni.get(j).getId() == prenotazione) {
				istruttore = prenotazioni.get(j).getIstruttore();
				campo = prenotazioni.get(j).getCampo();
			}
		}
		
		for(int j=0; j < istruttori.size() ; j++) {
			if(istruttori.get(j).getId() == istruttore) {
				prezzoIstr = istruttori.get(j).getPagaOraria();
			}
		}
		
		for(int j=0; j < campi.size() ; j++) {
			if(campi.get(j).getId() == campo) {
				prezzoCampo = campi.get(j).getPrezzo();
			}
		}
		
		String oraString = dataOra.substring(11, 13);
		int ora = Integer.parseInt(oraString);
		
		if(ora > 19) {
			prezzoLuci = 10;
		}
		
		totale = prezzoLuci + prezzoIstr + prezzoCampo;
		
		////////////////////////////////////////////////////////////////////////////////////
		
		int durataOld = 0;
		int idElimina = 0;
		
		for(int i=0; i < prenotazioni.size(); i++) {
			if(prenotazione == prenotazioni.get(i).getId()) {
				durataOld = prenotazioni.get(i).getDurata();
				idElimina = prenotazioni.get(i+1).getId();
			}
		}
		
		if(durata != durataOld) {
			if(durata == 1) {
				prenotazione pren = new prenotazione(prenotazione, dataOra, durata, totale, "", campo, istruttore, 0);
				dao.updateReserv(pren);
				dao.deleteReserv(idElimina);
			}
			else if(durata == 2) {
				int ora2 = ora + 1;
				String dataOra2 = dataOra.substring(0, 11) + ora2 + ":00:00";
				prenotazione p1 = new prenotazione(0, dataOra, durata, totale, "", campo, istruttore, 0);
				prenotazione p2 = new prenotazione(0, dataOra2, durata, totale, "", campo, istruttore, 0);
				dao.updateReserv(p1);
				if(istruttore == 0) {
					dao.insertReservNoIstr(p2);
				}
				else {
					dao.insertReserv(p2);
				}
			}
		}
		else {
			prenotazione pren = new prenotazione(prenotazione, dataOra, durata, totale, "", campo, istruttore, 0);
			dao.updateReserv(pren);
		}
	}

	public void eliminaPrenotazioneUtente(String username) throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<prenotazione> reserv = dao.selectReserv();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		List<prenotazione> myPrenot = new ArrayList<>();		
				
		int prenotazione = 0;
		String[] partecipanti;
		String p;
		
		for(int i=0; i < reserv.size() ; i++) {
			p = reserv.get(i).getPartecipanti();
			partecipanti = p.split(",");
			for(int j=0; j<partecipanti.length; j++) {
				if(username.equals(partecipanti[j])) {
					myPrenot.add(reserv.get(i));
				}
			}
		}

		System.out.println("LE TUE PRENOTAZIONI");
		for (int i = 0; i < myPrenot.size(); i++) {
			System.out.println("codice: " + myPrenot.get(i).getId());
			System.out.println("partecipanti: " + myPrenot.get(i).getPartecipanti());
			System.out.println("data e ora: " + myPrenot.get(i).getDataOra());
			System.out.println("campo: " + myPrenot.get(i).getCampo());
			System.out.println("istuttore: " + myPrenot.get(i).getIstruttore());
		}

		System.out.println("Digita il codice della prenotazione da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		if (dao.deleteReserv(codice)) {
			System.out.println("Prenotazione eliminato correttamente");
		}
		;

	}

	public void aggiungiCampo() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inserisci tipo di campo: ");
		String tipo = br.readLine();

		System.out.println("Campo coperto? [Yes o No] ");
		String c = br.readLine();
		boolean coperto = false;
		if (c.equals("Yes")) {
			coperto = true;
		}

		System.out.println("Inserisci il prezzo orario: ");
		float prezzo = Float.parseFloat(br.readLine());

		System.out.println("Inserisci valutazione: [0-5]");
		int valuta = Integer.parseInt(br.readLine());

		String codice = "C" + (int) (Math.random() * 9) + (int) (Math.random() * 9) + (int) (Math.random() * 9);

		campo field = new campo(0, tipo, coperto, prezzo, valuta, codice);
		dao.insertField(field);

	}

	public void modificaCampo() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<campo> fields = dao.selectFields();
		String tipo = "";
		String codice = "";
		String campi = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("I TUOI CAMPI: ");
		for (int i = 0; i < fields.size(); i++) {
			campi = fields.get(i).getId() + ")" + fields.get(i).getTipo()+ " "; 
			if (fields.get(i).isCoperto()) {
				campi = campi + "Coperto ";
			} else {
				campi = campi + "Non Coperto ";
			}
			System.out.println(campi);
		}
		
		System.out.println("Inserisci codice campo da modificare: ");
		String cod = br.readLine();
		int id = Integer.parseInt(cod);
		
		System.out.println("Campo coperto? [Yes o No] ");
		String c = br.readLine();
		boolean coperto = false;
		if (c.equals("Yes")) {
			coperto = true;
		}

		System.out.println("Inserisci il nuovo prezzo orario: ");
		float prezzo = Float.parseFloat(br.readLine());

		System.out.println("Inserisci nuova valutazione: [0-5]");
		int valuta = Integer.parseInt(br.readLine());

		campo field = new campo(id, tipo, coperto, prezzo, valuta, codice);
		
		if(dao.updateField(field)) {
			System.out.println("Campo modificato correttamente!");
		}
		else {
			System.out.println("Errore nella modifica!");
		}

	}

	public void eliminaCampo() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<campo> fields = dao.selectFields();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String campi = "";

		System.out.println("I TUOI CAMPI: ");
		System.out.println("I TUOI CAMPI: ");
		for (int i = 0; i < fields.size(); i++) {
			campi = fields.get(i).getId() + ")" + fields.get(i).getTipo()+ " "; 
			if (fields.get(i).isCoperto()) {
				campi = campi + "Coperto ";
			} else {
				campi = campi + "Non Coperto ";
			}
			System.out.println(campi);
		}

		System.out.println("Digita il codice del campo da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		if (dao.deleteField(codice)) {
			System.out.println("Campo eliminato correttamente");
		}
		else {
			System.out.println("Errore nell'eliminazione!");
		}
	}

	
	public void aggiungiIstruttore() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inserisci il nome dell'istruttore: ");
		String nome = br.readLine();

		System.out.println("Inserisci il cognome dell'istruttore: ");
		String cognome = br.readLine();

		System.out.println("Inserisci email dell'istruttore:: ");
		String email = br.readLine();

		System.out.println("Inserisci la password dell'istruttore:: ");
		String password = br.readLine();

		System.out.println("Inserisci età dell'istruttore:: ");
		String e = br.readLine();
		int eta = Integer.parseInt(e);

		System.out.println("Inserisci il numero di telefono dell'istruttore:: ");
		String numero = br.readLine();

		System.out.println("Inserisci il tuo sesso: ");
		String s = br.readLine();
		char sesso = s.charAt(0);

		System.out.println("Inserisci esperienza dell'istruttore: ");
		String ex = br.readLine();
		int esp = Integer.parseInt(ex);

		System.out.println("Inserisci ore lezione dell'istruttore: ");
		String o = br.readLine();
		int ore = Integer.parseInt(o);

		System.out.println("Inserisci paga oraria dell'istruttore: ");
		String p = br.readLine();
		float paga = Float.parseFloat(p);

		String username = "I" + (int) (Math.random() * 9) + (int) (Math.random() * 9) + (int) (Math.random() * 9);

		istruttore i = new istruttore(0, nome, cognome, eta, sesso, email, numero, username, password, esp, ore, paga);
		dao.insertInstru(i);

	}


	public void modificaIstruttore() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<istruttore> istruttori = dao.selectInstructors();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("REGISTRO ISTRUTTORI: ");
		for (int i = 0; i < istruttori.size(); i++) {
			System.out.println(istruttori.get(i).getId() + ") " + istruttori.get(i).getNome()
					+ istruttori.get(i).getCognome());
		}

		System.out.println("Digita il numero relativo all'istruttore che si desidera modificare: ");
		String a = br.readLine();
		int istruttore = Integer.parseInt(a);

		System.out.println("Inserisci l'username: ");
		String usr = br.readLine();

		System.out.println("Inserisci la password: ");
		String psw = br.readLine();

		System.out.println("Inserisci l'esperienza: ");
		String e = br.readLine();
		int exp = Integer.parseInt(e);

		System.out.println("Inserisci il sesso: ");
		String se = br.readLine();
		char s = se.charAt(0);

		System.out.println("Inserisci la email: ");
		String email = br.readLine();

		System.out.println("Inserisci il numero di telefono: ");
		String telefono = br.readLine();

		System.out.println("Inserisci il salario: ");
		String p = br.readLine();
		float paga = Float.parseFloat(p);

		System.out.println("Inserisci le ore di lezione: ");
		String o = br.readLine();
		int ore = Integer.parseInt(o);

		istruttore ist = new istruttore(istruttore, "", "", 0, s, email, telefono, usr, psw, exp, ore, paga);
		dao.updateInstru(ist);
	}

	
	public void eliminaIstruttore() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<istruttore> istr = dao.selectInstructors();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("REGISTRO ISTRUTTORI: ");
		for (int i = 0; i < istr.size(); i++) {
			System.out.println(istr.get(i).getId() + ") " + istr.get(i).getNome()
					+ istr.get(i).getCognome());
		}

		System.out.println("Digita il codice dell'istruttore da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		if (dao.deleteInstru(codice)) {
			System.out.println("Istruttore eliminato correttamente");
		}

	}

	
	public void modificaUtente() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<utente> users = dao.selectUsers();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("REGISTRO UTENTI: ");
		
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getId() + ") " + users.get(i).getNome()+ " "
					+ users.get(i).getCognome());
		}
		
		System.out.println("Digita il codice dell'utente da modificare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		
		System.out.println("Inserisci il nuovo username: ");
		String usr = br.readLine();

		System.out.println("Inserisci la nuova password: ");
		String psw = br.readLine();
		
		System.out.println("Inserisci il nuovo sesso: ");
		String se = br.readLine();
		char s = se.charAt(0);

		System.out.println("Inserisci la nuova email: ");
		String email = br.readLine();

		System.out.println("Inserisci il nuovo numero di telefono: ");
		String numero = br.readLine();
		
		utente u = new utente(codice, "", "", 0, email, numero, usr, psw, s);
		if(dao.updateUser(u)) {
			System.out.println("Utente modificato correttamente!");
		}
		else {
			System.out.println("Errore nella modifica!");
		}
	}
	
	public void eliminaUtente() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<utente> users = dao.selectUsers();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("REGISTRO UTENTI: ");
		
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getId() + ") " + users.get(i).getNome()
					+ users.get(i).getCognome());
		}

		System.out.println("Digita il codice dell'utente da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		
		if (dao.deleteUser(codice)) {
			System.out.println("Utente eliminato correttamente");
		}
		else {
			System.out.println("Errore nell'eliminazione!");
		}

	}

}