/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author URIEL
 */
public class Registration extends HttpServlet {

  private Connection conn;
private final  String host = "jdbc:postgresql://localhost/hola_perro";
private final String user = "postgres";
private final String password = "Chikushou95";
private PreparedStatement statement;
private ResultSet result;
private int registros=0;

@Override
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


 out.println("<p><form method=\"post\" action=" +
  "Registration>");
 // Set hidden values
  out.println("<p><input type=\"hidden\" " +
  "value=" + lastName + " name=\"lastName\">");
 out.println("<p><input type=\"hidden\" " +
 "value=" + firstName + " name=\"firstName\">");
  out.println("<p><input type=\"hidden\" " +
  "value=" + mi + " name=\"mi\">");
  out.println("<p><input type=\"hidden\" " +
  "value=" + telefono + " name=\"telefono\">");
 out.println("<p><input type=\"hidden\" " +
  "value=" + email + " name=\"email\">");
  out.println("<p><input type=\"hidden\" " +
  "value=" + street + " name=\"street\">");
  out.println("<p><input type=\"hidden\" " +
  "value=" + city + " name=\"city\">");
 out.println("<p><input type=\"hidden\" " +
  "value=" + state + " name=\"state\">");
  out.println("<p><input type=\"hidden\" " +
 "value=" + zip + " name=\"zip\">");
  out.println("<p><input type=\"submit\" value=\"Confirm\" >");

//out.println("<p><form method =\"post\" action=Registration>");
//out.println("<input type=\"hidden\" value="+firstName+"name=\"firstName\">");
//out.println("<input type=\"hidden\" value="+lastName+"name=\"lastName\">");
//out.println("<input type=\"hidden\" value="+mi+"name=\"mi\">");
//out.println("<input type=\"hidden\" value="+telefono+"name=\"telefono\">");
//out.println("<input type=\"hidden\" value="+email+"name=\"email\">");
//out.println("<input type=\"hidden\" value="+street+"name=\"street\">");
//out.println("<input type=\"hidden\" value="+city+"name=\"city\">");
//out.println("<input type=\"hidden\" value="+state+"name=\"state\">");
//out.println("<input type=\"hidden\" value="+zip+"name=\"zip\">");
//out.println("<p><input type=\"submit\" value=\"Enviar\" name=\"enviar\">");
//
//out.println("</form>");
//    insertarUsuario(lastName, firstName, mi, telefono, email, street, city, state, zip);
//    out.println(firstName + " " + lastName + " ha sido registrado con ??xito en la base de datos.");
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

String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String mi = request.getParameter("mi");
String telefono = request.getParameter("telefono");
String email = request.getParameter("email");
String street = request.getParameter("street");
String city = request.getParameter("city");
String state = request.getParameter("state");
String zip = request.getParameter("zip");

try{
 insertarUsuario(lastName, firstName, mi, telefono, email, street, city, state, zip);
}
catch(Exception ex){
ex.printStackTrace();
}
 out.println(firstName + " " + lastName + " ha sido registrado con ??xito en la base de datos.");


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
