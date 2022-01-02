package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableAndUpdatable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		int numeroRegistros=0;
		int numeroRegistroAfter=0;
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hola_perro","root","Chikushou95#");
		
		System.out.println("Conectado a la base de datos");
		
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		
		ResultSet resulset = statement.executeQuery("select state, capital from StateCapital");
		
		
		System.out.println("Antes de hacer la actualización la cantidad de registros son: ");

		mostrarResultados(resulset);
		
		
//		resulset.absolute(2);
//		resulset.updateString("state", "gonorrea");
//		resulset.updateString("capital", "Mi chimbo");
//		resulset.updateRow();
		
		resulset.last();
		 resulset.moveToInsertRow(); // Move cursor to the insert row
		 resulset.updateString("state", "Florida");
		 resulset.updateString("capital", "Tallahassee");
		 resulset.insertRow(); // Insert the row
		resulset.moveToCurrentRow(); // Move the cursor to the current row
		
		System.out.println("Despues de hacer la actualización la cantidad de registros son: " + numeroRegistros);
		
		mostrarResultados(resulset);
	}
	
	
	private static void mostrarResultados(ResultSet result) throws SQLException {
		
		
		int columns = result.getMetaData().getColumnCount();
		
		result.beforeFirst();
		while(result.next()) {
			
			
			for (int i=1; i<=columns;i++) {
				
				System.out.printf("%-18s\t",result.getObject(i));
				
			}
			
			System.out.println();
		}
		
	}

}

