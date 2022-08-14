/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author LamHung
 */
public class OrderDAO {
    
    
    private static final String ADDORDER = "INSERT INTO tblOrder(orderDate, total, userID, status) VALUES (?, ?, ?, ?)";
    private static final String GETORDERID = "SELECT orderID FROM tblOrder WHERE userID = ? AND status ='true'";
    
    
    public boolean addOrder(OrderDTO order) throws SQLException, ClassNotFoundException{
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADDORDER);
                ptm.setString(1, order.getOrderDate());
                ptm.setDouble(2, order.getTotal());
                ptm.setString(3, order.getUserID());
                ptm.setString(4, order.getStatus());
                check = ptm.executeUpdate()>0?true:false;
            }
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }
    
    
    public int getOrderID(OrderDTO order) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        int orderID = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GETORDERID);
            ptm.setString(1, order.getUserID());
            rs = ptm.executeQuery();
            while (rs.next()) {                              
                orderID = rs.getInt("orderID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return orderID;
    }
}
