/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.IProject;
import model.Permanent;
import model.ProjectType;
import model.Temporary;

/**
 *
 * @author march
 */
public class ProjectController extends HttpServlet {
final String PROJECTS_PATH = "views/projects.jsp"; 
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
            out.println("<title>Servlet ProjectController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjectController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        
        IProject newProject= null;
        if(request.getParameter("txtName")==null){
            double fixedAnual = Double.parseDouble(request.getParameter("txtFixedAnualCostP"));
            double  inflation = Double.parseDouble(request.getParameter("txtInflationP"));
            String name = request.getParameter("txtNameP");
            double initialCost = Double.parseDouble(request.getParameter("txtInitialCostP"));
            int manager = Integer.parseInt(request.getParameter("itemP"));
            newProject = new Permanent(fixedAnual, inflation, name, initialCost, manager);
        }else if(request.getParameter("txtNameP")==null){
        String name = request.getParameter("txtName");
        double initialCost = Double.parseDouble(request.getParameter("txtInitialCost"));
           int manager = Integer.parseInt(request.getParameter("item"));
           String date = request.getParameter("txtFinishDate");
           LocalDate finishDate = LocalDate.parse(date);
           String country = request.getParameter("txtCountry");
           double exchange = Double.parseDouble(request.getParameter("txtDolarExchange"));
            newProject = new Temporary(finishDate,country,name,initialCost,exchange,manager);
        }
        try {
            newProject.save();
        } catch (java.sql.SQLException e) {
        }finally{
            RequestDispatcher view = request.getRequestDispatcher(PROJECTS_PATH);
            view.forward(request, response);
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

}
