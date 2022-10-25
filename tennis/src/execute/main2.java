package execute;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import tennis.utente;

public class main2 {
	
	public utente utn;
	
	public static void main(String[] args) {
		databaseDAO dao = new databaseDAO();
		List<utente> lst = dao.selectUsers();	
		for(int i = 0; i < lst.size(); i++) {
			System.out.println(lst.get(i).getNome());
		}
	}
}

/*private int id;

	private String nome;
	private String cognome;
	private int eta;
	private String email;
	private String numero;
	private String username;
	private String password; 
	private char sesso;
	*/