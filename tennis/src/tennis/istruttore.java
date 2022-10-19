package tennis;

public class istruttore {
	
	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private char sesso; 
	private String email; 
	private String numero; 
	private String password; 
	private short esperienza; 
	private int oreLezione; 
	private float pagaOraria; 

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

	public short getEsperienza() {
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


	}
