/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import businessLogicWS.Book;
import businessLogicWS.Comment;
import businessLogicWS.Purchase;
import businessLogicWS.Section;
import businessLogicWS.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Владислав
 */
@WebServlet(name = "PersonalServlet", urlPatterns = {"/personal"})
public class PersonalServlet extends HttpServlet {

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
        User currentUser = (User)(request.getSession().getAttribute("user"));
        
        List<Section> sections = getAllSections();
        List<Comment> comments = getCommentsByUser(currentUser.getId());
        Map<Comment, Book> commentsWithBooks = new LinkedHashMap<>();
        for(Comment comment : comments){
            Book book = getBookById(comment.getBook());
            commentsWithBooks.put(comment, book);
        }
        Set<Map.Entry<Comment, Book>> entrySet = commentsWithBooks.entrySet();
        List<Purchase> purchases = getPurchasesByUser(currentUser.getId());
        List<Book> books = new ArrayList<>();
        for(Purchase purchase : purchases){
            Book book = getBookById(purchase.getBook());
            books.add(book);
        }
        
        request.setAttribute("sections", sections);
        request.setAttribute("commentsWithUsers", entrySet);
        request.setAttribute("books", books);
        
        request.getRequestDispatcher("/WEB-INF/view/personal.jsp").forward(request, response);
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

    private static java.util.List<businessLogicWS.Section> getAllSections() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAllSections();
    }

    private static java.util.List<businessLogicWS.Comment> getCommentsByUser(java.lang.Integer userId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getCommentsByUser(userId);
    }

    private static java.util.List<businessLogicWS.Purchase> getPurchasesByUser(java.lang.Integer userId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getPurchasesByUser(userId);
    }

    private static Book getBookById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getBookById(id);
    }

}
