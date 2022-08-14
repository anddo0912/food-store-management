/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.order.OrderDetailDAO;
import sample.order.OrderDetailDTO;
import sample.product.Cart;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.user.UserDTO;
import sample.utils.SendMail;

/**
 *
 * @author LamHung
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkOut.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        double total = 0;
        try {

            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            OrderDAO dao = new OrderDAO();
            OrderDetailDAO daoDetail = new OrderDetailDAO();
            ProductDAO daoProduct = new ProductDAO();
            LocalDate localdate = LocalDate.now();
            String currentDate = localdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Cart cart = (Cart) session.getAttribute("CART");
            boolean checkQuantity = true;
            if (cart != null) {
                if (cart.getCart().size() > 0) {

                    for (ProductDTO product : cart.getCart().values()) {
                        int quantityInSQL = daoProduct.getQuantityOfProduct(product.getProductID());
                        if (product.getQuantity() < quantityInSQL) {
                            total += product.getPrice() * product.getQuantity();
                        } else {
                            request.setAttribute("MESSAGE", "The product you choose is not enough to provide you");
                            checkQuantity = false;
                        }
                    }
                    if (checkQuantity) {
                        OrderDTO order = new OrderDTO(currentDate, total, user.getUserID(), "true");
                        boolean check = dao.addOrder(order);
                        boolean checkDetail = false;
                        int orderID = dao.getOrderID(order);
                        for (ProductDTO product : cart.getCart().values()) {
                            OrderDetailDTO orderDetail = new OrderDetailDTO(product.getPrice() * product.getQuantity(), product.getQuantity(), orderID, product.getProductID(), "true");
                            checkDetail = daoDetail.addOrder(orderDetail);
                        }

                        if (check == true && checkDetail == true) {
                            for (ProductDTO product : cart.getCart().values()) {
                                daoProduct.updateQuantity((daoProduct.getQuantityOfProduct(product.getProductID()) - product.getQuantity()) , product);
                            }
                            session.setAttribute("CART", null);
                            SendMail email = new SendMail();
                            email.sendOrder(user);
                            url = SUCCESS;
                        }
                    }
                } else {
                    request.setAttribute("MESSAGE", "You don't have any items in your cart. You can't checkout.");
                }
            } else {
                request.setAttribute("MESSAGE", "Session dont have any cart attribute");
            }

        } catch (Exception e) {
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
