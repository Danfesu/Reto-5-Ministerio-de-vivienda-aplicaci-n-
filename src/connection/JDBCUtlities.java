package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtlities {
	private static final String UBICACION_BD = "ProyectosConstruccion.db";
	private Connection connection;
	private Statement statement;
	
	
	
	public JDBCUtlities() {
		try {
			this.connection = getConnection();
			this.statement = connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:" + UBICACION_BD;
		return DriverManager.getConnection(url);
	}
	
	public ResultSet resultQuery(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
