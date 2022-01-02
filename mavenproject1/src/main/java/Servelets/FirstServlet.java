/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author URIEL
 */
public class FirstServlet extends HttpServlet {

    /**
     *
     */
    @Override
   public void init(){
}
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

    response.setContentType("text/html");
    PrintWriter out= response.getWriter();
out.print("<p>The current time is " + new java.util.Date());
out.close();
    
}

}
