/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
@WebServlet(name = "CategoryAdminServlet", urlPatterns = {"/category_admin"})
public class CategoryAdminServlet extends HttpServlet {

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
        
        String categoryId = request.getParameter("id");
        String delete = request.getParameter("delete");
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        
        if(name != null && description != null && image != null && categoryId != null){
            if(updateSection(name, description, image, Integer.parseInt(categoryId)))
                request.setAttribute("updateSuccess", true);
        } else if(name != null && description != null && image != null){
            if(addSection(name, description, image))
                request.setAttribute("addSuccess", true);
        }
        
        if(categoryId != null && delete != null){
            deleteSection(Integer.parseInt(categoryId));
            response.sendRedirect((String)request.getSession().getAttribute("currentPage"));
            return;
        }
        
        List<Section> sections = getAllSections();
        request.setAttribute("sections", sections);
        
        if(categoryId != null){
            Section section = getSectionById(Integer.parseInt(categoryId));
            request.setAttribute("section", section);
        }
        
        request.getRequestDispatcher("/WEB-INF/view/admin/category_admin.jsp").forward(request, response);
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

    private static Section getSectionById(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getSectionById(id);
    }

    private static java.util.List<businessLogicWS.Section> getAllSections() {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.getAllSections();
    }

    private static Boolean addSection(java.lang.String name, java.lang.String description, java.lang.String image) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.addSection(name, description, image);
    }

    private static Boolean updateSection(java.lang.String name, java.lang.String description, java.lang.String image, java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.updateSection(name, description, image, id);
    }

    private static Boolean deleteSection(java.lang.Integer id) {
        businessLogicWS.BusinessLogic_Service service = new businessLogicWS.BusinessLogic_Service();
        businessLogicWS.BusinessLogic port = service.getBusinessLogicPort();
        return port.deleteSection(id);
    }

}
