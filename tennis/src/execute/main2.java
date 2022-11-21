package execute;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import model.istruttore;
import model.utente;
import view.menuGestore;

public class main2 {
	
	public static void main(String[] args) throws SQLException, InterruptedException, IOException {
		menuGestore mg = new menuGestore();
		mg.menu("diocane");
		}
	}
