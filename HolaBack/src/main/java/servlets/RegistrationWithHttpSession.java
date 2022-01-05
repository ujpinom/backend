package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationWithHttpSession
 */
@WebServlet("/RegistrationWithHttpSession")
public class RegistrationWithHttpSession extends HttpServlet {
	
	 private Connection conn;
	 private final  String host = "jdbc:postgresql://localhost:5432/hola_perro";
	 private final String user = "postgres";
	 private final String password = "Chikushou95";
	 private PreparedStatement statement;
	 private ResultSet result;
	 private int registros=0;

	 
	 public void init() throws ServletException{
	 connectJDBC();
	 }
	 @Override
	 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{

	 response.setContentType("text/html");
	 PrintWriter out = response.getWriter();
	 String firstName = request.getParameter("firstName");
	 String lastName = request.getParameter("lastName");
	 String mi = request.getParameter("mi");
	 String telefono = request.getParameter("telefono");
	 String email = request.getParameter("email");
	 String street = request.getParameter("street");
	 String city = request.getParameter("city");
	 String state = request.getParameter("state");
	 String zip = request.getParameter("zip");
	 
	 HttpSession httppSesssion = request.getSession();
	 
	

	 try{

	 if(firstName.length()>0 && lastName.length()>0){
	 if(verificarUsuario(firstName, lastName)){
	 out.println("El usuario ingresado ya existe. Ingrese un nombre y apellido diferente");
	 }
	 else{
	      
	         out.println("You entered the following data");
	         out.println("<br>");
	         out.println("<br>Last Name: " + lastName);
	         out.println("<br>First Name: " + firstName);
	         out.println("<br>MI: " + mi);
	         out.println("<br>Telephone: " + telefono);
	         out.println("<br>Email: " + email);
	         out.println("<br>Address: " + street);
	         out.println("<br>City: " + city);
	         out.println("<br>State: " + state);
	         out.println("<br>Zip: " + zip);

	Address address = new Address();
	address.setLastName(lastName);
	address.setFirstName(firstName);
	address.setMi(mi);
	address.setStreet(street);
	address.setTel(telefono);
	address.setEmail(email);
	address.setCity(city);
	address.setState(state);
	address.setZip(zip);
	
	
	 httppSesssion.setAttribute("address", address);
	 
	 out.println("<br>Tiempo antes de invalidar la sección: " + httppSesssion.getMaxInactiveInterval());
	

	 out.println("<form action= RegistrationWithHttpSession method=\"post\"");
	 out.println("<br><input type=\"submit\" value=\"Confirmar\" />");
	 out.println("</form>");

	 }
	 }
	 else{
	 out.println("Debe ingresar el nombre y el apellido");
	 }
	 }
	 catch(Exception ex){
	 out.println("Error :" + ex.getMessage());
	 }
	 finally{
	 out.close();
	 }

	 }

	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
	 response.setContentType("text/html");
	 PrintWriter out = response.getWriter();
	 
	 HttpSession httpp = request.getSession();
	 
	 Address address = (Address)httpp.getAttribute("address");
	 

	  String lastName = address.getLastName();
	  String firstName = address.getFirstName();
	   String mi =address.getMi();
	  String telephone = address.getTel();
	   String email = address.getEmail();
	  String street = address.getStreet();
	  String city = address.getCity();
	  String state = address.getState();
	  String zip = address.getZip();

	 try{
	  insertarUsuario(lastName, firstName, mi, telephone, email, street, city, state, zip);
	 }
	 catch(Exception ex){
	 ex.printStackTrace();
	 }
	  out.println(firstName + " " + lastName + " ha sido registrado con éxito en la base de datos.");

	 }


	 private void insertarUsuario(String lastName, String firstName,String mi, String phone, String email, String address,String city, String state, String zip) throws SQLException 
	 {
	 statement.setString(1, firstName);
	 statement.setString(2, mi);
	 statement.setString(3, lastName);
	 statement.setString(4, address);
	 statement.setString(5, city);
	 statement.setString(6, state);
	 statement.setString(7, zip);
	 statement.setString(8, phone);
	 statement.setString(9, email);
	 statement.executeUpdate();

	 }

	 private boolean verificarUsuario(String firstName,String lastName){

	 boolean flag= false;

	 String query = "select count(*) from address where firstname = ? and lastname= ?";
	 try{

	 PreparedStatement pst1 = conn.prepareStatement(query);
	 pst1.setString(1, firstName);
	 pst1.setString(2, lastName);

	     ResultSet result= pst1.executeQuery();

	 if (result.next()){

	 int registros = result.getInt(1);

	 if(registros>0){
	 flag=true;
	 }
	 }
	 }
	 catch(Exception ex){
	 ex.printStackTrace();
	 }


	 return flag;


	 }

	 private void connectJDBC() {

	 try{
	     Class.forName("org.postgresql.Driver");
	     System.out.println("Driver Cargado");

	     conn = DriverManager.getConnection(host,user , password);

	     System.out.println("Conectado a la base de datos");

	     String query = "insert into address (firstname,mi,lastname,street,city,state,zip,telephone,"
	     + "email) values (?,?,?,?,?,?,?,?,?)";

	     statement = conn.prepareStatement(query);

	 }
	 catch(Exception ex){
	 ex.printStackTrace();
	 }
	 }

	
	
}
