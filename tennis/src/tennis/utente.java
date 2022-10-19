package tennis;

public class utente {

	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private String email;
	private String numero;
	private String password; 
	private char sesso;
	
	public int getId() {
		return id;
	}
	

	public int getEta() {
		return eta;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}


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

}
