package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

import dao.databaseDAO;
import model.campo;
import model.istruttore;
import model.utente;

class tests {
	
	@Test
	void testCampo() throws SQLException{
		databaseDAO d = new databaseDAO();
		
		List<campo> campi = d.selectFields();
		for(int i = 0; i < campi.size(); i++){
			if(campi.get(i).getCodice().equals("C242")) {
				assertTrue(d.deleteField(campi.get(i).getId()));
				System.out.println("Eliminazione avvenuta");
			}
		}
		
		campo campoCorretto = new campo(0, "erba", false, 18, 3, "C242");
		campo campoSbagliato = new campo(0, "erba", false, 18, 3, "C241");
		
		assertTrue(d.insertField(campoCorretto));
		System.out.println("Attesa eccezione per codice duplicato");
		assertFalse(d.insertField(campoSbagliato));

		campoCorretto = new campo(56, "erba", true, 18, 3, "C103");
		campoSbagliato = new campo(56, "erba", true, 18, 3, "C241");
		
		assertTrue(d.updateField(campoCorretto));
		System.out.println("Attesa eccezione per codice duplicato");
		assertFalse(d.updateField(campoSbagliato));

		
	}
	
	@Test
	void testUtente() throws SQLException {
	    databaseDAO d = new databaseDAO();
	    utente utentecorretto = new utente(0,"nometest", "cognometest", 20, "email@test.com", "333455", ""
	    		+ "U550", "passwordtest", 'm');
	    utente utentesbagliato = new utente(1,"nome2test", "cognome2test", 2333215, "email2@test.com", "333455", ""
	    		+ "U001", "passwordtest2", 'f');
	    
	   //Test Insert
	    assertTrue(d.insertUser(utentecorretto));
	    System.out.println("attesa eccezione per codice utente duplicato");
	    assertFalse(d.insertUser(utentesbagliato));
	
	    //Test Update
	    utentecorretto = new utente(18,"modificatest", "modificatest", 20, "modifica@test.com", "333455", ""
	    		+ "U557", "modificatest", 'm');
	    utentesbagliato = new utente(18,"modifica2test", "modifica2test", 2333215, "modifica2@test.com", "333455", ""
	    		+ "U001", "modificatest2", 'f');
	    
	    assertTrue(d.updateUser(utentecorretto));
	    System.out.println("attesa eccezione per codice utente duplicato");
	    assertFalse(d.insertUser(utentesbagliato));
	    
	  //Test Delete
	    assertTrue(d.deleteUser(6));
	}
	
	@Test
	void testIstruttore() throws SQLException {
	    databaseDAO d = new databaseDAO();
	    istruttore istruttorecorretto = new istruttore(0,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I007", "passwordistru", 4, 50, 20);
	    istruttore istruttoresbagliato = new istruttore(0,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I001", "passwordistru", 4, 50, 20);
	    
	   //Test Insert
	    assertTrue(d.insertInstru(istruttorecorretto));
	    System.out.println("attesa eccezione per codice istruttore duplicato");
	    assertFalse(d.insertInstru(istruttoresbagliato));
	
	    //Test Update
	    istruttorecorretto = new istruttore(3,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I007", "passwordistru", 4, 50, 20);
	    istruttoresbagliato = new istruttore(3,"istrutest", "istrutest", 20, 'm', "istruttore@test.com", "333455", ""
	    		+ "I001", "passwordistru", 4, 50, 20);
	    
	    assertTrue(d.updateInstru(istruttorecorretto));
	    System.out.println("attesa eccezione per codice istruttore duplicato");
	    assertFalse(d.updateInstru(istruttoresbagliato));
	    
	  //Test Delete
	    assertTrue(d.deleteInstru(6));
	}
	
}
