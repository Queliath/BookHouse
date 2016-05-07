/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import businessLogicWS.Book;
import businessLogicWS.Rating;
import businessLogicWS.Section;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
@WebServlet(name = "AnalyticsServlet", urlPatterns = {"/analytics"})
public class AnalyticsServlet extends HttpServlet {

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
        
        List<Section> sections = getAllSections();
        
        List<Book> topBooksByRating = getTopBooksByRating();
        Map<Book, Double> topBooksByRatingWithRating = new LinkedHashMap<>();
        for(Book book : topBooksByRating){
            Double rating = getAverageRatingByBook(book.getId());
            topBooksByRatingWithRating.put(book, rating);
        }
        Set<Map.Entry<Book, Double>> entrySetRating = topBooksByRatingWithRating.entrySet();
        
        List<Book> topBooksByComment = getTopBooksByComment();
        Map<Book, Integer> topBooksByCommentWithCommentNumber = new LinkedHashMap<>();
        for(Book book : topBooksByComment){
            Integer commentsNumber = getCommentsByBook(book.getId()).size();
            topBooksByCommentWithCommentNumber.put(book, commentsNumber);
        }
        Set<Map.Entry<Book, Integer>> entrySetComment = topBooksByCommentWithCommentNumber.entrySet();
        
        List<Book> topBooksByPurchases = getTopBooksByPurchases();
        Map<Book, Integer> topBooksByPurchasesWithPurchaseNumber = new LinkedHashMap<>();
        for(Book book : topBooksByPurchases){
            Integer purchaseNumber = getPurchasesByBook(book.getId()).size();
            topBooksByPurchasesWithPurchaseNumber.put(book, purchaseNumber);
        }
        Set<Map.Entry<Book, Integer>> entrySetPurchase = topBooksByPurchasesWithPurchaseNumber.entrySet();
        
        List<Integer> purchasesBySectionChart = getPurchasesBySectionChart();
        Map<Section, Integer> sectionsWithPurchases = new LinkedHashMap<>();
        Iterator<Section> it = sections.iterator();
        for(Integer purchases : purchasesBySectionChart)
            sectionsWithPurchases.put(it.next(), purchases);
        
        List<Integer> purchasesByPriceChart = getPurchasesByPriceChart();
        
        request.setAttribute("sections", sections);
        request.setAttribute("topBooksByRating", entrySetRating);
        request.setAttribute("topBooksByComment", entrySetComment);
        request.setAttribute("topBooksByPurchase", entrySetPurchase);
        request.setAttribute("sectionsWithPurchases", sectionsWithPurchases);
        request.setAttribute("purchasesByPriceChart", purchasesByPriceChart);
        
        request.getRequestDispatcher("/WEB-INF/view/admin/analytics.jsp").forward(request, response);
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

    private static java.util.List<businessLogicWS.Book> getTopBooksByRating() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getTopBooksByRating();
    }

    private static java.util.List<businessLogicWS.Book> getTopBooksByComment() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getTopBooksByComment();
    }

    private static java.util.List<businessLogicWS.Book> getTopBooksByPurchases() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getTopBooksByPurchases();
    }

    private static Double getAverageRatingByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAverageRatingByBook(bookId);
    }

    private static java.util.List<businessLogicWS.Comment> getCommentsByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getCommentsByBook(bookId);
    }

    private static java.util.List<businessLogicWS.Purchase> getPurchasesByBook(java.lang.Integer bookId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getPurchasesByBook(bookId);
    }

    private static java.util.List<java.lang.Integer> getPurchasesBySectionChart() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getPurchasesBySectionChart();
    }

    private static java.util.List<java.lang.Integer> getPurchasesByPriceChart() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getPurchasesByPriceChart();
    }

}
