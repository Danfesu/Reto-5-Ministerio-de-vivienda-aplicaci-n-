package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import connection.JDBCUtlities;
import view.JFrameMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	private JDBCUtlities jdbcUtlities;
	private JFrameMain frameMain;
	private static Controller controller = null;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {
		this.jdbcUtlities = new JDBCUtlities();
	}

	public void starApp() {
		frameMain = new JFrameMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ComandoCasa":
			showTableReportCasa();
			break;
		case "ComandoLider":
			showTableReportLider();
			break;
		case "ComandoHomecenter":
			showTableReportHomecenter();
			break;
		case "ComandoAtras":
			showMenu();
			break;
		}
	}

	private void showMenu() {
		frameMain.showMenu();
	}

	private void showTableReportHomecenter() {
		ResultSet resultSet = jdbcUtlities.resultQuery(
				"SELECT C.ID_COMPRA, P.CONSTRUCTORA, P.BANCO_VINCULADO FROM COMPRA C JOIN PROYECTO P ON P.ID_PROYECTO = C.ID_PROYECTO WHERE C.PROVEEDOR = 'Homecenter' AND P.CIUDAD = 'Salento'");
 		frameMain.showTableReport(getColumnNames(resultSet), getRowQuery(resultSet));
	}

	private void showTableReportLider() {
		ResultSet resultSet = jdbcUtlities.resultQuery(
				"SELECT ID_LIDER, NOMBRE, PRIMER_APELLIDO, CIUDAD_RESIDENCIA FROM LIDER ORDER BY CIUDAD_RESIDENCIA");
		frameMain.showTableReport(getColumnNames(resultSet), getRowQuery(resultSet));
	}

	private void showTableReportCasa() {
		ResultSet resultSet = jdbcUtlities.resultQuery(
				"SELECT ID_PROYECTO, CONSTRUCTORA, NUMERO_HABITACIONES, CIUDAD FROM PROYECTO WHERE CLASIFICACION = 'Casa Campestre' AND CIUDAD IN ('Santa Marta','Cartagena','Barranquilla')");
		frameMain.showTableReport(getColumnNames(resultSet), getRowQuery(resultSet));
	}
	
	private ArrayList<Object[]> getRowQuery(ResultSet resultSet) {
		ArrayList<Object[]> rows = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Object[] aux = new Object[resultSet.getMetaData().getColumnCount()];
				for (int i = 0; i < aux.length; i++) {
					aux[i] = resultSet.getObject(i+1);
				}
				rows.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	private String[] getColumnNames(ResultSet resultSet) {
		String[] columnNames = null;
		try {
			columnNames = new String[resultSet.getMetaData().getColumnCount()];
			for (int i = 0; i < columnNames.length; i++) {
				columnNames[i] = resultSet.getMetaData().getColumnName(i+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNames;
	}

}
