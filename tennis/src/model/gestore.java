package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import dao.databaseDAO;

public class gestore {

	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private String email;
	private String numero;
	private String username;
	private String password;
	private char sesso;
	private String partitaIva;

	public gestore(int id, String nome, String cognome, int eta, String email, String numero, String username,
			String password, char sesso, String partitaIva) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.email = email;
		this.numero = numero;
		this.username = username;
		this.password = password;
		this.sesso = sesso;
		this.partitaIva = partitaIva;
	}
	
	// getter and setter
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getEta() {
		return eta;
	}

	public String getPartitaIva() {
		return partitaIva;
	}


}
