/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author LamHung
 */
public class OrderDetailDAO {
    
    private static final String ADDORDERDETAIL = "INSERT INTO tblOrderDetail(price, quantity, orderID, productID, status) VALUES (?, ?, ?, ?, ?)";
    
    
    public boolean addOrder(OrderDetailDTO order) throws SQLException, ClassNotFoundException{
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADDORDERDETAIL);
                ptm.setDouble(1, order.getPrice());
                ptm.setInt(2, order.getQuantity());
                ptm.setInt(3, order.getOrderID());
                ptm.setString(4, order.getProductID());
                ptm.setString(5, order.getStatus());
                check = ptm.executeUpdate()>0?true:false;
            }
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }

}
