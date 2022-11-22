package execute;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.databaseDAO;
import model.istruttore;
import model.utente;
<<<<<<< Upstream, based on branch 'master' of https://github.com/giova95/tennis
import view.menuGestore;
=======
import view.menuFacade;
import controller.controller;
>>>>>>> 42003ed aggiornato modifica prenotazione utente

public class main2 {
	
	public static void main(String[] args) throws SQLException, InterruptedException, IOException {
<<<<<<< Upstream, based on branch 'master' of https://github.com/giova95/tennis
		menuGestore mg = new menuGestore();
		mg.menu("diocane");
=======
		controller c = new controller();
		
		c.modificaPrenotazioneUtente("U001");
>>>>>>> 42003ed aggiornato modifica prenotazione utente
		}
	}
