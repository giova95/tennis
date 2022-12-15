package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import dao.databaseDAO;
import model.campo;
import model.istruttore;
import model.prenotazione;
import model.utente;

class tests {
	
	@BeforeAll
	static void setup() throws SQLException {
		databaseDAO d = new databaseDAO();
		List<campo> campi = d.selectFields();
		for(int i = 0; i < campi.size(); i++){
			if(campi.get(i).getCodice().equals("C242")) {
				assertTrue(d.deleteField(campi.get(i).getId()));
			}
		}
		
		List<utente> utenti = d.selectUsers();
		for(int i = 0; i < utenti.size(); i++){
			if(utenti.get(i).getUsername().equals("U750")) {
				assertTrue(d.deleteUser(utenti.get(i).getId()));
			}
		}
		
		List<istruttore> istruttori = d.selectInstructors();
		for(int i = 0; i < istruttori.size(); i++){
			if(istruttori.get(i).getUsername().equals("I905")) {
				assertTrue(d.deleteInstru(istruttori.get(i).getId()));
			}
		}
		
		List<prenotazione> preno = d.selectReserv();
		for(int i = 0; i < preno.size(); i++){
			if(preno.get(i).getDataOra().equals("2022-03-23 15:00:00")) {
				assertTrue(d.deleteReserv(preno.get(i).getId()));
			}
		}
		
	}
	
	@Test
	void testCampo() throws SQLException{
		databaseDAO d = new databaseDAO();
		
		campo campoCorretto = new campo(0, "erba", false, 18, 3, "C242");
		campo campoSbagliato = new campo(0, "erba", false, 18, 3, "C023");
		
		//Test Insert
		assertTrue(d.insertField(campoCorretto));
		assertFalse(d.insertField(campoSbagliato));

		campoCorretto = new campo(9, "erba", true, 18, 3, "C103");
		campoSbagliato = new campo(9, "erba", true, 18, 3, "C023");
		
		//Test Update
		assertTrue(d.updateField(campoCorretto));
		assertFalse(d.updateField(campoSbagliato));
	}
	
	@Test
	void testUtente() throws SQLException {
	    databaseDAO d = new databaseDAO();
	    utente utentecorretto = new utente(0,"nometest", "cognometest", 20, "email@test.com", "333455", ""
	    		+ "U750", "passwordtest", 'm');
	    utente utentesbagliato = new utente(1,"nome2test", "cognome2test", 23, "email2@test.com", "333455", ""
	    		+ "U001", "passwordtest2", 'f');
	    
	   //Test Insert
	    assertTrue(d.insertUser(utentecorretto));
	    assertFalse(d.insertUser(utentesbagliato));
	
	    //Test Update
	    utentecorretto = new utente(18,"modificatest", "modificatest", 20, "modifica@test.com", "333455", ""
	    		+ "U777", "modificatest", 'm');
	    utentesbagliato = new utente(18,"modifica2test", "modifica2test", 23, "modifica2@test.com", "333455", ""
	    		+ "U001", "modificatest2", 'f');
	    
	    assertTrue(d.updateUser(utentecorretto));
	    assertFalse(d.insertUser(utentesbagliato));
	   
	}

	@Test
	void testIstruttore() throws SQLException {
	    databaseDAO d = new databaseDAO();
	    istruttore istruttorecorretto = new istruttore(0,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I905", "passwordistru", 4, 50, 20);
	    istruttore istruttoresbagliato = new istruttore(0,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I001", "passwordistru", 4, 50, 20);
	    
	   //Test Insert
	    assertTrue(d.insertInstru(istruttorecorretto));
	    assertFalse(d.insertInstru(istruttoresbagliato));
	    //Test Update
	    istruttorecorretto = new istruttore(7,"modificatest", "modificatest", 20, 'm', "modifica@test.com", "333455", ""
	    		+ "I531", "modifciaistru", 4, 50, 20);
	    istruttoresbagliato = new istruttore(7,"modificatest2", "modificatest2", 20, 'm', "modificatest2", "333455", ""
	    		+ "I001", "modificaistru2", 4, 50, 20);
	    
	    assertTrue(d.updateInstru(istruttorecorretto)); 	
	    assertFalse(d.updateInstru(istruttoresbagliato));
	   
	}
	
	@Test
	void testPrenotazione() throws SQLException {
		databaseDAO d = new databaseDAO();
	    prenotazione prenCorretta = new prenotazione(0, "2022-03-23 15:00:00", 1, 12, "U001,U002", 2, 2, 1);
	    prenotazione prenSbagliata = new prenotazione(0, "2022-03-23 15:00:00", 1, 12, "U001,U002", 2, 2000, 1);
	  
	    
	    //Test Insert
		assertTrue(d.insertReserv(prenCorretta));
		assertFalse(d.insertReserv(prenSbagliata));
		
	    
		prenCorretta = new prenotazione(134, "2022-08-16 15:00:00", 1, 12, "U001,U002", 2, 2, 1);
	    prenSbagliata = new prenotazione(134, "2022-08-16 15:00:00", 1298901, 12, "U001,U002", 2, 2, 1);
	    
	    //Test Update
	    assertTrue(d.updateReserv(prenCorretta));
		assertFalse(d.updateReserv(prenSbagliata));
	}

}
