/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;

/**
 *
 * @author LamHung
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "ListAllController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        boolean checkValidation = true;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            String status = request.getParameter("status");
            LocalDate todaysDate = LocalDate.now();
            LocalDate importLocal = LocalDate.parse(importDate);
            LocalDate usingLocal = LocalDate.parse(usingDate);
            long daysBetween = ChronoUnit.DAYS.between(importLocal, usingLocal);
            ProductDAO dao = new ProductDAO();
            boolean checkDuplicate = dao.checkDuplicate(productID);
            if (importLocal.compareTo(todaysDate) > 0){
                productError.setImportDate("Import Day must before Today");
                checkValidation = false;
            }
            
            if (usingLocal.compareTo(todaysDate) < 0 ){
                productError.setUsingDate("Using Day must after Today");
                checkValidation = false;
            }
            
            if (daysBetween > 60 || daysBetween < 14){
                productError.setUsingDate("Day using must be from 14 days to 60 days ");
                checkValidation = false;
            }
            
            if (checkDuplicate == true){
                productError.setProductID("Duplicate");
                checkValidation = false;
            }
            if (productID.length() < 1 || productID.length() > 10) {
                productError.setProductID("UserID must be [[2,10]");
                checkValidation = false;
            }
            
            if (productName.length() < 4 || productName.length() > 20) {
                productError.setProductName("productName must be [4,20]");
                checkValidation = false;
            }
            
            if (price == 0 || price < 0) {
                productError.setPrice("The price must be > 0");
                checkValidation = false;
            }
            
            if (quantity == 0 || quantity < 0) {
                productError.setQuantity("The quantity must be > 0");
                checkValidation = false;
            }
            
            if (dao.checkCategory(categoryID) == false) {
                productError.setCategoryID("The categoryID must be exist");
                checkValidation = false;
            }
            
            if (checkValidation) {
                ProductDTO product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status);
                boolean checkCreate = dao.createProduct(product);
                if (checkCreate) 
                    url = SUCCESS;
                    request.setAttribute("ANOU",productError);
            }else{
                request.setAttribute("PRODUCT_ERROR",productError);
            }         
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

}
