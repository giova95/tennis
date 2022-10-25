package execute;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import tennis.utente;

public class main2 {
	
	public utente utn;
	
	
	public static void main(String[] args) {
		System.out.println("diocane");
		databaseDAO dao = new databaseDAO();
		List<utente> lst = dao.selectUsers();
		for(int i = 0; i < lst.size(); i++) {
			System.out.println(lst.get(i));
		}
	}
}
