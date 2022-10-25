package tennis;

public class prenotazione {
	
	private int id;
	private String dataOra; 
	private int durata; 
	private float prezzo; 
	private String partecipanti;
	private int campo;
	private int istruttore;
	private String tipo;
	
	public prenotazione(int id, String dataOra, int durata, float prezzo, String partecipanti, int campo,
			int istruttore, String tipo) {
		super();
		this.id = id;
		this.dataOra = dataOra;
		this.durata = durata;
		this.prezzo = prezzo;
		this.partecipanti = partecipanti;
		this.campo = campo;
		this.istruttore = istruttore;
		this.tipo = tipo;
	}


	public int getId() {
		return id;
	}


	public String getPartecipanti() {
		return partecipanti;
	}


	public int getCampo() {
		return campo;
	}


	public int getIstruttore() {
		return istruttore;
	}


	public String getTipo() {
		return tipo;
	}


	public String getDataOra() {
		return dataOra;
	}


	public void setDataOra(String dataOra) {
		this.dataOra = dataOra;
	}


	public int getDurata() {
		return durata;
	}


	public void setDurata(int durata) {
		this.durata = durata;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

}
