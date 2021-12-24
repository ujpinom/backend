package jdbc;

import java.sql.*;

public class FirstConection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver completamente cargado");
		
		Connection conection = DriverManager.getConnection("jdbc:mysql://localhost/hola_perro", "root", "Chikushou95#");
		
		System.out.println("Database connected");
		
		Statement statement = conection.createStatement();
		
		
		ResultSet resultado= statement.executeQuery("select * from hola_perro where title = 'C++'");
		
		while (resultado.next()) {
			
			System.out.println(resultado.getString(1)+ "\t" + resultado.getString(2)+ "\t" + resultado.getString(3)+ "\t"
					+ resultado.getString(4)+ "\t"+ resultado.getString(5));
		}
		
		conection.close();
	}

}
