/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author URIEL
 */
public class TimeForm extends HttpServlet {

 private static final String CONTENT_TYPE = "text/html";
 private Locale[] allLocale = Locale.getAvailableLocales();
 private String[] allTimeZone = TimeZone.getAvailableIDs();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TimeForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeForm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 response.setContentType(CONTENT_TYPE);
  PrintWriter out = response.getWriter();
  out.println("<h3>Choose locale and time zone</h3>");
  out.println("<form method=\"post\" action=" +
  "TimeForm>");
  out.println("Locale <select size=\"1\" name=\"locale\">");
 
    // Fill in all locales
  for (int i = 0; i < allLocale.length; i++) {
  out.println("<option value=\"" + i +"\">" +
  allLocale[i].getDisplayName() + "</option>");
  }
  out.println("</select>");
 
  // Fill in all time zones
  out.println("<p>Time Zone<select size=\"1\" name=\"timezone\">");
  for (int i = 0; i < allTimeZone.length; i++) {
  out.println("<option value=\"" + allTimeZone[i] +"\">" +
  allTimeZone[i] + "</option>");
  }
  out.println("</select>");
 
  out.println("<p><input type=\"submit\" value=\"Submit\" >");
  out.println("<input type=\"reset\" value=\"Reset\"></p>");
  out.println("</form>");
  out.close(); // Close stream


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
response.setContentType(CONTENT_TYPE);
  PrintWriter out = response.getWriter();
  out.println("<html>");
  int localeIndex = Integer.parseInt(
  request.getParameter("locale"));
 String timeZoneID = request.getParameter("timezone");
 out.println("<head><title>Current Time</title></head>");
  out.println("<body>");
  Calendar calendar =
  new GregorianCalendar(allLocale[localeIndex]);
  TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
  DateFormat dateFormat = DateFormat.getDateTimeInstance(
   DateFormat.FULL, DateFormat.FULL, allLocale[localeIndex]);
  dateFormat.setTimeZone(timeZone);
  out.println("Current time is " +
  dateFormat.format(calendar.getTime()) + "</p>");
  out.println("</body></html>");
 out.close(); //

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
