package model;

import java.time.LocalDate;
import java.util.List;

public class tariffario {
	
	public float calcolaStipendioIstruttore(istruttore i) {
		int ore = i.getOreLezione();
		float salario = i.getPagaOraria();
		
		return ore*salario;
	}

	public float calcolaRicavi(List<prenotazione> p, String data) {
		float ricavi = 0;
		
		for(int i = 0; i<p.size(); i++) {
			if(p.get(i).getDataOra().contains(data))
				ricavi += p.get(i).getPrezzo();
		}
		return ricavi;
	}

	public float calcolaCosti(List<prenotazione> p, String Data) {
		float costi = 0;
		
		
		return costi;
	}
	public float prezzoPrenotazione(List<istruttore> istruttori, List<campo> campi, int idIst, int idCampo, String dataOra, int durata) {
		float prezzoIstr = 0;
		float prezzoLuci = 0;
		float prezzoCampo = 0;
		float totale;
		
		for (int i = 0; i < istruttori.size(); i++) {
			if (istruttori.get(i).getId() == idIst) {
				prezzoIstr = istruttori.get(i).getPagaOraria() * durata;
			}
		}

		for (int i = 0; i < campi.size(); i++) {
			if (campi.get(i).getId() == idCampo) {
				prezzoCampo = campi.get(i).getPrezzo();
			}
		}

		String oraString = dataOra.substring(11, 13);
		int ora = Integer.parseInt(oraString);

		if (ora > 19) {
		prezzoLuci = 10;
		}

		return totale = prezzoLuci + prezzoIstr + prezzoCampo;
	}
}
