package model;

public class tariffario {
	
	public float calcolaStipendioIstruttore(istruttore i) {
		int ore = i.getOreLezione();
		float salario = i.getPagaOraria();
		
		return ore*salario;
	}
}
