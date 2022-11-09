package execute;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import model.istruttore;
import model.utente;

public class main2 {
	
	public static void main(String[] args) throws SQLException {
		databaseDAO dao = new databaseDAO();
		dao. deleteInstru(3);
		List<istruttore> lst = dao.selectInstructors();	
		for(int i = 0; i < lst.size(); i++) {
			System.out.println(lst.get(i).getNome());
		}
	}
}
