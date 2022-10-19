package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tennis.utente;
import tennis.campo;
import tennis.istruttore;

/**
 * AbstractDAO for API CRUD
 */
public class databaseDAO {
	private String url = "jdbc:mysql://localhost:8080/tennisApp?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";


	// select all tables SQL
	private static final String SELECT_ALL_USERS = "select * from utente";
	private static final String SELECT_ALL_FIELDS = "select * from campo";
	private static final String SELECT_ALL_RESERV = "select * from prenotazione";
	private static final String SELECT_ALL_INSTRU = "select * from istruttore";

	// insert in all tables SQL
	private static final String INSERT_USERS_SQL = "INSERT INTO utente"
			+ "  (nome, cognome, eta, sesso, email, telefono, password) VALUES " + " (?, ?, ?, ?. ?, ?, ?);";
	private static final String INSERT_FIELDS_SQL = "INSERT INTO campo"
			+ "  (tipo, prezzo, valutazione, coperto) VALUES " + " (?, ?, ?, ?);";
	private static final String INSERT_INSTRU_SQL = "INSERT INTO istruttore"
			+ "  (nome, cognome, eta, sesso, email, telefono, password," + " esperienza, oreLezione, pagaOraria) "
			+ "VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_RESERV_SQL = "INSERT INTO prenotazione"
			+ "  (dataOra, durata, prezzo, partecipanti, campo, istruttore," + " tipo) VALUES " + " (?, ?, ?, ?. ?, ?, ?);";

	// delete from all tables SQL
	private static final String DELETE_USERS_SQL = "delete from utente where id = ?;";
	private static final String DELETE_FIELDS_SQL = "delete from campo where id = ?;";
	private static final String DELETE_RESERV_SQL = "delete from prenotazione where id = ?;";
	private static final String DELETE_ISTRU_SQL = "delete from istruttore where id = ?;";

	// update all tables SQL
	private static final String UPDATE_USERS_SQL = "update utente set sesso = ?, email = ?, telefono = ?, password = ? where id = ?;";
	private static final String UPDATE_FIELDS_SQL = "update users set prezzo = ?, valutazione = ?, coperto = ? where id = ?;";
	private static final String UPDATE_REVERS_SQL = "update users set  dataOra = ?, durata = ?, prezzo = ? where id = ?;";
	private static final String UPDATE_INSTRU_SQL = "update users set sesso = ?, email = ?, telefono = ?, password = ?, esperienza = ?"
			+ " oreLezione = ?, pagaOraria = ? where id = ?;";

	public databaseDAO() {}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;

	}
	
	
	public void insertUser(utente user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getCognome());
            preparedStatement.setLong(3, user.getEta()); // TODO: fare Tostring
            preparedStatement.setLong(4, user.getSesso()); // TODO: fare Tostring
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getNumero());
            preparedStatement.setString(7, user.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLState: " +((SQLException)e).getSQLState());
        }
    }
	
	public void insertInstru(istruttore istr) throws SQLException {
        System.out.println(INSERT_INSTRU_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRU_SQL)) {
            preparedStatement.setString(1, istr.getNome());
            preparedStatement.setString(2, istr.getCognome());
            preparedStatement.setLong(3, istr.getEta()); // TODO: fare Tostring
            preparedStatement.setLong(4, istr.getSesso()); // TODO: fare Tostring
            preparedStatement.setString(5, istr.getEmail());
            preparedStatement.setString(6, istr.getNumero());
            preparedStatement.setString(7, istr.getPassword());
            preparedStatement.setLong(8, istr.getEsperienza()); // TODO: fare Tostring
            preparedStatement.setLong(9, istr.getOreLezione()); // TODO: fare Tostring
            preparedStatement.setString(10, Float.toString(istr.getPagaOraria()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLState: " +((SQLException)e).getSQLState());
        }
    }
	
	public void insertField(campo c) throws SQLException {
        System.out.println(INSERT_FIELDS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FIELDS_SQL)) {
            preparedStatement.setString(1, c.getTipo());
            preparedStatement.setString(2, Float.toString(c.getPrezzo()));
            preparedStatement.setString(3, Integer.toString(c.getValutazione()));
            preparedStatement.setString(4, Boolean.toString(c.isCoperto()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLState: " +((SQLException)e).getSQLState());
        }
    }
	
	
}
