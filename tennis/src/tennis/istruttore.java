package tennis;

public class istruttore {
	
	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private char sesso; 
	private String email; 
	private String numero; 
	private String username;
	private String password; 
	private int esperienza; 
	private int oreLezione; 
	private float pagaOraria; 
	
	public istruttore(int id, String nome, String cognome, int eta, char sesso, String email, String numero,
			String username, String password, int esperienza, int oreLezione, float pagaOraria) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.sesso = sesso;
		this.email = email;
		this.numero = numero;
		this.username = username;
		this.password = password;
		this.esperienza = esperienza;
		this.oreLezione = oreLezione;
		this.pagaOraria = pagaOraria;
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

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
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

	public int getEsperienza() {
		return esperienza;
	}


	public void setEsperienza(short esperienza) {
		this.esperienza = esperienza;
	}

	public int getOreLezione() {
		return oreLezione;
	}

	public void setOreLezione(int oreLezione) {
		this.oreLezione = oreLezione;
	}

	public float getPagaOraria() {
		return pagaOraria;
	}

	public void setPagaOraria(float pagaOraria) {
		this.pagaOraria = pagaOraria;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	}
