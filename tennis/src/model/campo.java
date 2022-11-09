package model;

public class campo {
	
	private int id;
	private String tipo;
	private boolean coperto;
	private float prezzo;
	private int valutazione;
	private String codice;
	
	public campo(int id, String tipo, boolean coperto, float prezzo, int valutazione, String codice) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.coperto = coperto;
		this.prezzo = prezzo;
		this.valutazione = valutazione;
		this.codice = codice;
	}


	public int getId() {
		return id;
	}


	public String getTipo() {
		return tipo;
	}


	public boolean isCoperto() {
		return coperto;
	}


	public void setCoperto(boolean coperto) {
		this.coperto = coperto;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	public int getValutazione() {
		return valutazione;
	}


	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}


	public String getCodice() {
		return codice;
	}
	
	
}
