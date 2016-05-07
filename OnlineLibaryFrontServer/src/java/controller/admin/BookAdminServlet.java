/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import businessLogicWS.Book;
import businessLogicWS.Section;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;
import parsing.XMLCalendarToDate;

/**
 *
 * @author Владислав
 */
@WebServlet(name = "BookAdminServlet", urlPatterns = {"/book_admin"})
public class BookAdminServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        String bookId = request.getParameter("id");
        String delete = request.getParameter("delete");
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String preview = request.getParameter("preview");
        String numberOfPages = request.getParameter("numberOfPages");
        String price = request.getParameter("price");
        String fileName = request.getParameter("fileName");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String section = request.getParameter("section");
        String image = request.getParameter("image");
        XMLGregorianCalendar date = XMLCalendarToDate.toXMLGregorianCalendar(new Date());
        
        if(bookId != null && title != null && description != null && preview != null && numberOfPages != null && price != null && fileName != null && author != null && year != null && section != null && image != null){
            if(updateBook(Integer.parseInt(bookId), title, description, preview, Integer.parseInt(numberOfPages), Integer.parseInt(price), Integer.parseInt(year), image, fileName, date, Integer.parseInt(section), author))
                request.setAttribute("updateSuccess", true);
        } else if(title != null && description != null && preview != null && numberOfPages != null && price != null && fileName != null && author != null && year != null && section != null && image != null){
            if(addBook(title, description, preview, Integer.parseInt(numberOfPages), Integer.parseInt(price), Integer.parseInt(year), image, fileName, date, Integer.parseInt(section), author))
                request.setAttribute("addSuccess", true);
        }
        
        if(bookId != null && delete != null){
            deleteBook(Integer.parseInt(bookId));
            response.sendRedirect((String)request.getSession().getAttribute("currentPage"));
            return;
        }
        
        List<Section> sections = getAllSections();
        request.setAttribute("sections", sections);
        
        if(bookId != null){
            Book book = getBookById(Integer.parseInt(bookId));
            request.setAttribute("book", book);
        }
        
        request.getRequestDispatcher("/WEB-INF/view/admin/book_admin.jsp").forward(request, response);
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

    private static Book getBookById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getBookById(id);
    }

    private static Boolean addBook(java.lang.String title, java.lang.String description, java.lang.String preview, int numberOfPages, int price, int year, java.lang.String image, java.lang.String fileName, javax.xml.datatype.XMLGregorianCalendar dateOfPublication, java.lang.Integer section, java.lang.String author) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.addBook(title, description, preview, numberOfPages, price, year, image, fileName, dateOfPublication, section, author);
    }

    private static Boolean updateBook(java.lang.Integer id, java.lang.String title, java.lang.String description, java.lang.String preview, int numberOfPages, int price, int year, java.lang.String image, java.lang.String fileName, javax.xml.datatype.XMLGregorianCalendar dateOfPublication, java.lang.Integer section, java.lang.String author) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.updateBook(id, title, description, preview, numberOfPages, price, year, image, fileName, dateOfPublication, section, author);
    }

    private static Boolean deleteBook(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.deleteBook(id);
    }

}
