/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import businessLogicWS.Book;
import businessLogicWS.Comment;
import businessLogicWS.Purchase;
import businessLogicWS.Rating;
import businessLogicWS.Section;
import businessLogicWS.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Владислав
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setMaxInactiveInterval(-1);
        }
        session.setAttribute("currentPage", request.getRequestURI() + "?" + request.getQueryString());

        String bookId = request.getParameter("id");

        List<Section> sections = getAllSections();
        Book selectedBook = getBookById(Integer.parseInt(bookId));
        List<Comment> comments = getCommentsByBook(Integer.parseInt(bookId));
        int numberOfComments = comments.size();
        Double averageRating = getAverageRatingByBook(Integer.parseInt(bookId));
        Map<Comment, User> commentsWithUsers = new LinkedHashMap<>();
        for (Comment comment : comments) {
            User user = getUserById(comment.getUser());
            commentsWithUsers.put(comment, user);
        }
        Set<Map.Entry<Comment, User>> entrySet = commentsWithUsers.entrySet();
        User currentUser = (User) (session.getAttribute("user"));
        if (currentUser != null) {
            List<Purchase> purchases = getPurchasesByUser(currentUser.getId());
            for (Purchase purchase : purchases) {
                if(purchase.getBook().equals(selectedBook.getId()))
                    request.setAttribute("alreadyBought", true);
            }
            List<Rating> ratings = getRatingsByUser(currentUser.getId());
            for(Rating rating : ratings){
                if(rating.getBook().equals(selectedBook.getId()))
                    request.setAttribute("rating", rating);
            }
        }

        request.setAttribute("sections", sections);
        request.setAttribute("selectedBook", selectedBook);
        request.setAttribute("averageRating", averageRating);
        request.setAttribute("commentsWithUsers", entrySet);
        request.setAttribute("numberOfComments", numberOfComments);

        request.getRequestDispatcher("/WEB-INF/view/book.jsp").forward(request, response);
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

    private static java.util.List<businessLogicWS.Comment> getCommentsByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getCommentsByBook(bookId);
    }

    private static Double getAverageRatingByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAverageRatingByBook(bookId);
    }

    private static User getUserById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getUserById(id);
    }

    private static java.util.List<businessLogicWS.Section> getAllSections() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAllSections();
    }

    private static java.util.List<businessLogicWS.Purchase> getPurchasesByUser(java.lang.Integer userId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getPurchasesByUser(userId);
    }

    private static java.util.List<businessLogicWS.Rating> getRatingsByUser(java.lang.Integer userId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getRatingsByUser(userId);
    }

}
