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
public class getInfoStudents extends HttpServlet {


    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String mi = request.getParameter("mi");
        String gender = request.getParameter("gender");
        String titulo = request.getParameter("major");
        String tennis = request.getParameter("tennis");
        String football = request.getParameter("football");
        String baseball = request.getParameter("baseball");
        String music = request.getParameter("music");
        String remarks = request.getParameter("remarks");

        out.println("Last Name: <b>" + lastName + "</b> First Name: <b>"
        + firstName + "</b> MI: <b>" + mi + "</b><br>");
        out.println("Gender: <b>" + gender + "</b><br>");
        out.println("Major: <b>" + titulo + "</b> Minor: <b>");

          out.println("</b><br> Tennis: <b>" + tennis + "</b> Football: <b>" +
          football + "</b> Baseball: <b>" + baseball + "</b><br>" + "</b> Music: <b>" + music);
         out.println("Remarks: <input>" + remarks + "</input>");
         out.close(); // Close strea
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            process(request,response);

    }

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            process(request,response);

    }


}
