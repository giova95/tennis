package tennis;

public class campo {
	
	private int id;
	private String tipo;
	private boolean coperto;
	private float prezzo;
	private int valutazione;
	
	
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
	
	
}
