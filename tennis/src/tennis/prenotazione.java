package tennis;

public class prenotazione {
	
	private int id;
	private String dataOra; 
	private int durata; 
	private float prezzo; 
	private String partecipanti;
	private campo campo;
	private istruttore istruttore;
	private String tipo;
	
	
	public int getId() {
		return id;
	}


	public String getPartecipanti() {
		return partecipanti;
	}


	public campo getCampo() {
		return campo;
	}


	public istruttore getIstruttore() {
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
