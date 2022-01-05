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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author URIEL
 */
public class RegistrationWithCookie extends HttpServlet {

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

    Cookie cookielastName = new Cookie("lastName", lastName);
response.addCookie(cookielastName);
    Cookie cookiefirstName = new Cookie("firstName", firstName);
response.addCookie(cookiefirstName);
    Cookie cookiemi = new Cookie("mi", mi);
response.addCookie(cookiemi);
    Cookie cookitelefono = new Cookie("telefono", telefono);
response.addCookie(cookitelefono);
    Cookie cookiemail = new Cookie("email", email);
response.addCookie(cookiemail);
    Cookie cookieaddress = new Cookie("address", street);
response.addCookie(cookieaddress);
    Cookie cookiecity = new Cookie("city", city);
response.addCookie(cookiecity);
    Cookie cookiestate = new Cookie("state", state);
response.addCookie(cookiestate);
    Cookie cookiezip = new Cookie("zip", zip);
response.addCookie(cookiezip);

out.println("<form action= RegistrationWithCookie method=\"post\"");
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

 String lastName = "";
 String firstName = "";
  String mi = "";
 String telephone = "";
  String email = "";
 String street = "";
 String city = "";
 String state = "";
 String zip = "";

Cookie [] cookies = request.getCookies();

for(int i=0;i<cookies.length;i++){

if(cookies[i].getName().equals("lastName"))
lastName = cookies[i].getValue();
else if (cookies[i].getName().equals("firstName"))
firstName= cookies[i].getValue();
else if (cookies[i].getName().equals("mi"))
mi= cookies[i].getValue();
else if (cookies[i].getName().equals("telefono"))
telephone= cookies[i].getValue();
else if (cookies[i].getName().equals("email"))
email = cookies[i].getValue();
else if (cookies[i].getName().equals("address"))
street = cookies[i].getValue();
else if (cookies[i].getName().equals("city"))
city = cookies[i].getValue();
else if (cookies[i].getName().equals("state"))
state = cookies[i].getValue();
else if (cookies[i].getName().equals("zip"))
zip = cookies[i].getValue();

}

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
