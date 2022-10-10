/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.IPerson;
import model.Person;

/**
 *
 * @author march
 */
public class PersonController extends HttpServlet {
    final String PERSONS_PATH = "views/persons.jsp"; 
    final String INDEX_PATH = "index.jsp"; 
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
        String url_redirect = "";
        
        String requestAction = request.getParameter("action");
        switch (requestAction) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                IPerson person = new Person(id);
                try {
                    person.delete();
                    url_redirect = PERSONS_PATH;
                } catch (SQLException e) {
                    url_redirect = PERSONS_PATH;
                }
                break;
            default:
               url_redirect = PERSONS_PATH;
            
        }
        RequestDispatcher view = request.getRequestDispatcher(url_redirect);
        view.forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("txtId"));
        String surname =request.getParameter("txtSurname");
        String lastname = request.getParameter("txtLastname");
        String email = request.getParameter("txtEmail");
        int phone = Integer.parseInt(request.getParameter("txtNumber"));
        String date = request.getParameter("txtBornDate");
        LocalDate parseDate = LocalDate.parse(date);
        IPerson newPerson = new Person(id, surname, lastname, email, phone, parseDate);
        try {
            newPerson.save();
        } catch (SQLException e) {
            
        }finally{
            RequestDispatcher view = request.getRequestDispatcher(PERSONS_PATH);
            view.include(request, response);
        }
        
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
