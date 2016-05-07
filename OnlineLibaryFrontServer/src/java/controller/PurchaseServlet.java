/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import businessLogicWS.Book;
import businessLogicWS.Section;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Владислав
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/purchase"})
public class PurchaseServlet extends HttpServlet {

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
        String bookId = request.getParameter("book");
        String userId = request.getParameter("userId");
        String cardNumber = request.getParameter("cardNumber");
        String securityNumber = request.getParameter("securityNumber");
        
        if(bookId != null && userId != null && cardNumber != null && securityNumber != null)
            if(addPurchase(cardNumber, securityNumber, Integer.parseInt(bookId), Integer.parseInt(userId)))
                request.setAttribute("purchaseSuccess", true);
        
        List<Section> sections = getAllSections();
        Book book = getBookById(Integer.parseInt(bookId));
        
        request.setAttribute("sections", sections);
        request.setAttribute("book", book);
        
        request.getRequestDispatcher("/WEB-INF/view/purchase.jsp").forward(request, response);
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

    private static Book getBookById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getBookById(id);
    }

    private static java.util.List<businessLogicWS.Section> getAllSections() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAllSections();
    }

    private static Boolean addPurchase(java.lang.String cardNumber, java.lang.String securityNumber, java.lang.Integer bookId, java.lang.Integer userId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.addPurchase(cardNumber, securityNumber, bookId, userId);
    }

}
