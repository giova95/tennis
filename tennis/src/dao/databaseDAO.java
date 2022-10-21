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
import tennis.prenotazione;

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
			+ "  (dataOra, durata, prezzo, partecipanti, campo, istruttore," + " tipo) VALUES "
			+ " (?, ?, ?, ?. ?, ?, ?);";

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

	public databaseDAO() {
	}

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
	
	//CRUD API GET
	public List < utente > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < utente > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                String surname = rs.getString("cognome");
                int age = rs.getInt("eta");
                char sesso = rs.getString("sesso").charAt(0);
                String email = rs.getString("email");
                String telephone = rs.getString("telefono");
                String password = rs.getString("password");
                users.add(new utente(id, name, surname, age, email, telephone, password, sesso));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
	
	
	
	//CRUD API POST/PUT
	public void insertUser(utente user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getCognome());
			preparedStatement.setString(3, Integer.toString(user.getEta())); 
			preparedStatement.setString(4, Character.toString(user.getSesso())); 
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getNumero());
			preparedStatement.setString(7, user.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
		}
	}

	public void insertInstru(istruttore istr) throws SQLException {
		System.out.println(INSERT_INSTRU_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRU_SQL)) {
			preparedStatement.setString(1, istr.getNome());
			preparedStatement.setString(2, istr.getCognome());
			preparedStatement.setString(3, Integer.toString(istr.getEta())); 
			preparedStatement.setString(4, Character.toString(istr.getSesso())); 
			preparedStatement.setString(5, istr.getEmail());
			preparedStatement.setString(6, istr.getNumero());
			preparedStatement.setString(7, istr.getPassword());
			preparedStatement.setString(8, Integer.toString(istr.getEsperienza())); // TODO: fare Tostring
			preparedStatement.setString(9, Integer.toString(istr.getOreLezione())); // TODO: fare Tostring
			preparedStatement.setString(10, Float.toString(istr.getPagaOraria()));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void insertField(campo c) throws SQLException {
		System.out.println(INSERT_FIELDS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FIELDS_SQL)) {
			preparedStatement.setString(1, c.getTipo());
			preparedStatement.setString(2, Float.toString(c.getPrezzo()));
			preparedStatement.setString(3, Integer.toString(c.getValutazione()));
			preparedStatement.setString(4, Boolean.toString(c.isCoperto()));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void insertReserv(prenotazione p) throws SQLException {
		System.out.println(INSERT_RESERV_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERV_SQL)) {
			preparedStatement.setString(1, p.getDataOra());
			preparedStatement.setString(2, Integer.toString(p.getDurata()));
			preparedStatement.setString(3, Float.toString(p.getPrezzo()));
			preparedStatement.setString(4, p.getPartecipanti());
			preparedStatement.setString(5, Integer.toString(p.getCampo().getId()));
			preparedStatement.setString(6, Integer.toString(p.getIstruttore().getId()));
			preparedStatement.setString(7, p.getTipo());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}


private void printSQLException(SQLException ex) {
    for (Throwable e: ex) {
        if (e instanceof SQLException) {
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        }
    }
}
}


