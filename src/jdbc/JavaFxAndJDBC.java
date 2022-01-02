package jdbc;

import java.lang.Thread.State;
import java.sql.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxAndJDBC extends Application {
	
	private PreparedStatement preparedstatement;
	private Statement statement;
	private TextField inputGender = new TextField();
	private TextField inputDecade = new TextField();
	private Connection connection;
	private Label label1;
	private Label label2;
	private Button exec;
	private Label estado= new Label();

	@Override
	public void start(Stage a) throws Exception {
		
		DBInitialize();
		
		inputGender.setPrefColumnCount(6);
		inputDecade.setPrefColumnCount(6);
		
		exec= new Button("Consultar");
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("Genero"),inputGender,new Label("Decada"),inputDecade,exec);
		
		VBox vb= new VBox(10);
		vb.setPadding(new Insets(10));
		vb.getChildren().addAll(hBox,estado);
		
		
		exec.setOnAction(e->{
			try {
				showResult();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	
		
		Scene scene= new Scene(vb,390,80);
		
		a.setScene(scene);
		a.setTitle("Consulta DB");
		a.show();
		a.setResizable(false);
		

		
	}
	
	private void DBInitialize() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		connection= DriverManager.getConnection("jdbc:postgresql://localhost/hola_perro",
		        "postgres", "Chikushou95");
		
		System.out.println("Connected to DataBase");
		
		preparedstatement = connection.prepareStatement("SELECT name,frequency from most_frequent_name where Gender = ? and Decade = ? "
				+ "order by frequency desc limit 1 ");
		
		statement = connection.createStatement();
		
		
		DatabaseMetaData metadata= connection.getMetaData();
		
		System.out.println("URL de la base de datos: "+ metadata.getURL());
		
		System.out.println("Product version "+ metadata.getDatabaseProductVersion());
		
		System.out.println("maximo numero de columnas: "+metadata.getMaxColumnsInTable());
		
		System.out.println("soportta batch "+metadata.supportsBatchUpdates());
	}
	
	public static void main(String [] args) {
		
		launch(args);
		
		
	}
	
	
	public void showResult() throws SQLException {
		
		String gender= inputGender.getText();
		
		String decada= inputDecade.getText();
		
		preparedstatement.setString(1, gender);
		preparedstatement.setString(2, decada);
		
//		String query= String.format("SELECT name,frequency from most_frequent_name where Gender = '%s' and Decade = '%s'"
//				+ "order by frequency desc limit 1 ", gender,decada);
		
		
		ResultSet resulSet= preparedstatement.executeQuery();
		
		if (resulSet.next()) {
			
			String name= resulSet.getString(1);
			String freq= resulSet.getString(2);
			
			System.out.println(name);
			
			if (name== null || freq== null) {
				estado.setText("No hay datos para la consulta");
			}
			else
			estado.setText("El nombre más popular fue "+ name + " con una frecuencia de " + freq + " veces." );
		
		}
		else {
			
			estado.setText("No hay datos para la consulta");
		}
	

	}

}
