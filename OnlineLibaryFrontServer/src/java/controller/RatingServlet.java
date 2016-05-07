/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Владислав
 */
@WebServlet(name = "RatingServlet", urlPatterns = {"/rating"})
public class RatingServlet extends HttpServlet {

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
        String value = request.getParameter("value");
        String bookId = request.getParameter("bookId");
        String userId = request.getParameter("userId");
        
        String responseStr = "";
        
        if(addRating(Integer.parseInt(value), Integer.parseInt(userId), Integer.parseInt(bookId))){
            responseStr = Double.toString(getAverageRatingByBook(Integer.parseInt(bookId)));
        } else{
            responseStr = "Error";
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println(responseStr);
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
        processRequest(request, response);
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

    private static Boolean addRating(java.lang.Integer value, java.lang.Integer userId, java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.addRating(value, userId, bookId);
    }

    private static Double getAverageRatingByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAverageRatingByBook(bookId);
    }

}
