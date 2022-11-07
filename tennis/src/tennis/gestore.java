package tennis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import dao.databaseDAO;

public class gestore {

	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private String email;
	private String numero;
	private String username;
	private String password;
	private char sesso;
	private String partitaIva;
	private int campo;

	public gestore(int id, String nome, String cognome, int eta, String email, String numero, String username,
			String password, char sesso, String partitaIva, int campo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.email = email;
		this.numero = numero;
		this.username = username;
		this.password = password;
		this.sesso = sesso;
		this.partitaIva = partitaIva;
		this.campo = campo;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("I TUOI CAMPI: ");
		for (int i = 0; i < fields.size(); i++) {
			System.out.println("Codice: " + fields.get(i).getId());
			System.out.println("Tipo: " + fields.get(i).getTipo());
			System.out.println("Coperto: ");
			if (fields.get(i).isCoperto()) {
				System.out.println(" Sì ");
			} else {
				System.out.println("No");
			}
			System.out.println("Valutazione: " + fields.get(i).getValutazione());
			System.out.println("Prezzo: " + fields.get(i).getPrezzo());
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

		System.out.println("Inserisci il prezzo orario: ");
		float prezzo = Float.parseFloat(br.readLine());

		System.out.println("Inserisci valutazione: [0-5]");
		int valuta = Integer.parseInt(br.readLine());

		campo field = new campo(id, tipo, coperto, prezzo, valuta, codice);
		
		if(dao.updateField(field)) {
			System.out.println("Campo modificato correttamente!");
		}

	}

	public void eliminaCampo() throws IOException, SQLException {
		databaseDAO dao = new databaseDAO();
		List<campo> fields = dao.selectFields();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("I TUOI CAMPI: ");
		for (int i = 0; i < fields.size(); i++) {
			System.out.println("Codice: " + fields.get(i).getId());
			System.out.println("Tipo: " + fields.get(i).getTipo());
			System.out.println("Coperto: ");
			if (fields.get(i).isCoperto()) {
				System.out.println(" Sì ");
			} else {
				System.out.println("No");
			}
			System.out.println("Valutazione: " + fields.get(i).getValutazione());
			System.out.println("Prezzo: " + fields.get(i).getPrezzo());
		}

		System.out.println("Digita il codice del campo da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		if (dao.deleteField(codice)) {
			System.out.println("Campo eliminato correttamente");
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
			System.out.println(users.get(i).getId() + ") " + users.get(i).getNome()
					+ users.get(i).getCognome());
		}
		
		System.out.println("Digita il codice dell'utente da eliminare: ");
		String c = br.readLine();
		int codice = Integer.parseInt(c);
		
		System.out.println("Inserisci l'username: ");
		String usr = br.readLine();

		System.out.println("Inserisci la password: ");
		String psw = br.readLine();
		
		System.out.println("Inserisci il sesso: ");
		String se = br.readLine();
		char s = se.charAt(0);

		System.out.println("Inserisci la email: ");
		String email = br.readLine();

		System.out.println("Inserisci il numero di telefono: ");
		String numero = br.readLine();
		
		utente u = new utente(codice, "", "", 0, email, numero, usr, psw, s);
		dao.updateUser(u);
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

	}

	// getter and setter
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getEta() {
		return eta;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public int getCodCampo() {
		return campo;
	}

}
