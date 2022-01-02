package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Grader extends Application {
	
	
	private PreparedStatement preparedstatement;
	private Statement statement;
	private Connection connection;
	private Label username = new Label("Username");
	private Label password = new Label("Password");
	private Label fullName = new Label("FullName");
	private Label instructorEmail = new Label("InstructorEmail");
	
	private HBox hb1= new HBox(80);
	private HBox hb2= new HBox(30);
	private HBox hb3= new HBox(30);
	
	private VBox vb= new VBox();
	
	private TextField uName= new TextField();
	private TextField pass= new TextField();
	private TextField fName= new TextField();
	private TextField insEmail= new TextField();
	private Button exec = new Button("Insertar");
	
	private Label status = new Label();
	
	
	
	@Override
	public void start(Stage a) throws Exception {
		
		DBInitializer();
		
		hb1.getChildren().addAll(username,password,fullName,instructorEmail);
		hb2.getChildren().addAll(uName,pass,fName,insEmail);
		hb3.getChildren().addAll(exec,status);
		hb3.setPadding(new Insets(10));
		vb.getChildren().addAll(hb1,hb2,hb3);
		vb.setPadding(new Insets(10));
		
		
		
		Scene scene= new Scene(vb,500,90);
		a.setScene(scene);
		a.setTitle("Consulta DB");
		a.show();
		a.setResizable(false);
		
		exec.setOnAction(e->ejecutar());
		
	}
	
	private void ejecutar() {
		
		String username= uName.getText();
		
		String pass = this.pass.getText();
		
		String nombre = fName.getText();
		
		String email = insEmail.getText();
		
		if (verificarEmail(email)) {
			
			status.setText("Email encontrado. Puede continuar con el registro");
			
			if(verificarUsuario(username, pass)) {
				
				status.setText("El nuevo usuario ha sido registrado correctamente");
				
			}
			else {
				
				status.setText("El usuario ingresado ya existe. Ingrese un usuario diferente");
			}
			
			
		}
		else {
			
			status.setText("Email no encontrado. Vuelva a ingresar los datos");
		}
		
		
	}
	
	private void DBInitializer() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("org.postgresql.Driver");
		
		System.out.println("Connecting to databse...");
		
		connection= DriverManager.getConnection("jdbc:postgresql://localhost/hola_perro",
		        "postgres", "Chikushou95");
		
		
		System.out.println("Connected to database.");
		
		
	}
	
	private boolean verificarUsuario(String user, String pass )  {
		
		boolean flag = false;
		
		String query= "select count(*) from agsstudent where username = ?  and password_ = ?";
		
		
		
		try {
			
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, user);
			
			preparedstatement.setString(2, pass);
			
			ResultSet resulset= preparedstatement.executeQuery();
			
			if (resulset.next()) {
			int resultado = resulset.getInt(1);
			
			 if(resultado==0) {
				 
				 flag = true;
			 }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}
	
	private boolean verificarEmail(String email) {
		 boolean flag = false;
		 
		 
		 String query= "select count(*) from exerciseassigned where instructoremail = ?";
		 
		 try {
			preparedstatement = connection.prepareStatement(query);
			
			preparedstatement.setString(1, email);
			
			 ResultSet resulset = preparedstatement.executeQuery();
			 
			 if (resulset.next()) {
				 
				 int resultado= resulset.getInt(1);
				 
				 if(resultado>0) {
					 
					 flag = true;
				 }
				 
			 }	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return flag;
	}
	
	public static void main(String args[]) {
		
		launch(args);
	}


}
