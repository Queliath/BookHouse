/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import businessLogicWS.Book;
import businessLogicWS.Section;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "CatalogServlet", urlPatterns = {"/catalog"})
public class CatalogServlet extends HttpServlet {

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

        request.setCharacterEncoding("UTF-8");
        String sectionId = request.getParameter("section");
        String keyword = request.getParameter("keyword");

        List<Section> sections = getAllSections();
        request.setAttribute("sections", sections);
        List<Book> books = null;
        if (sectionId != null) {
            Section selectedSection = getSectionById(Integer.parseInt(sectionId));
            books = getBooksBySection(Integer.parseInt(sectionId));
            request.setAttribute("selectedSection", selectedSection);
        } else if(keyword != null){
            books = getBooksByKeyword(keyword);
            request.setAttribute("keyword", keyword);
        } else{
            books = getAllBooks();
        }

        request.setAttribute("books", books);
        
        request.getRequestDispatcher("/WEB-INF/view/catalog.jsp").forward(request, response);
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

    private static Section getSectionById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getSectionById(id);
    }

    private static java.util.List<businessLogicWS.Book> getBooksBySection(java.lang.Integer sectionId) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getBooksBySection(sectionId);
    }

    private static java.util.List<businessLogicWS.Book> getBooksByKeyword(java.lang.String keyword) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getBooksByKeyword(keyword);
    }

    private static java.util.List<businessLogicWS.Book> getAllBooks() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAllBooks();
    }

}
