/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author LamHung
 */
public class ProductDAO {

    private static final String GETALLPRODUCT = "SELECT productID, productName, image, price, quantity, categoryID, importDate, usingDate, status FROM tblProduct WHERE status = 'true'";
    private static final String GETPRODUCTBYNAME = "SELECT productID, productName, image, price, quantity, categoryID, importDate, usingDate, status FROM tblProduct WHERE productName like ? AND status = 'true'";
    private static final String UPDATEPRODUCT = "UPDATE tblProduct SET productName = ?, image = ?, price = ?, quantity = ?, categoryID = ?, importDate = ?, usingDate = ?, status = ? WHERE productID = ?";
    private static final String DELETEPRODUCT = "UPDATE tblProduct SET status = 'false' WHERE productID = ?";
    private static final String CREATEPRODUCT = "INSERT INTO tblProduct(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String CHECK_DUPLICATE = "SELECT productName FROM tblProduct WHERE productID=?";
    private static final String CHECK_EXIST_CATEGORY = "SELECT categoryName FROM tblCategory WHERE categoryID = ?";
    private static final String GETPRODUCTBYID = "SELECT productName, image, price, quantity, categoryID, importDate, usingDate, status FROM tblProduct WHERE productID = ? AND status = 'true'";
    private static final String GETPRODUCTQUANTITY = "SELECT quantity FROM tblProduct WHERE productID = ? AND status = 'true'";
    private static final String UPDATEQUANTITY = "UPDATE tblProduct SET quantity = ? WHERE productID = ? AND status = 'true'";
    
    public List<ProductDTO> getAllProducts() throws SQLException {

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETALLPRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    String status = rs.getString("status");
                    listProduct.add(new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }

        return listProduct;
    }
    
    public List<ProductDTO> getProductsByName(String search) throws SQLException {

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETPRODUCTBYNAME);
                ptm.setString(1, "%"+search+"%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    String status = rs.getString("status");
                    listProduct.add(new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }

        return listProduct;
    }
    
    public ProductDTO getProductById(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        ProductDTO product = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GETPRODUCTBYID);
            ptm.setString(1, productID);
            rs = ptm.executeQuery();
            while (rs.next()) {                              
                int quantity = rs.getInt("quantity");
                String categoryID = rs.getString("categoryID");
                String importDate = rs.getString("importDate");
                String usingDate = rs.getString("usingDate");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                String productName = rs.getString("productName");
                String status = rs.getString("status");
                product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return product;
    }
     
    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEPRODUCT);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getImage());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getCategoryID());
                ptm.setString(6, product.getImportDate());
                ptm.setString(7, product.getUsingDate());
                ptm.setString(8, product.getStatus());
                ptm.setString(9, product.getProductID());
//                int checkUpdate = ps.executeUpdate();
//                if (checkUpdate > 0) {
//                    check = true;
//                }
               check = ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }
    
    public boolean deleteProduct(String productID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(DELETEPRODUCT);
                ptm.setString(1, productID);
                check = ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
        }finally{
            if(ptm != null) ptm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean createProduct(ProductDTO product) throws SQLException, ClassNotFoundException{
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATEPRODUCT);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getImage());
                ptm.setDouble(4, product.getPrice());
                ptm.setInt(5, product.getQuantity());
                ptm.setString(6, product.getCategoryID());
                ptm.setString(7, product.getImportDate());
                ptm.setString(8, product.getUsingDate());
                ptm.setString(9, product.getStatus());
                check = ptm.executeUpdate()>0?true:false;
            }
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicate (String productID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(ptm != null) ptm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkCategory (String categoryID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(CHECK_EXIST_CATEGORY);
                ptm.setString(1, categoryID);
                rs = ptm.executeQuery();
                if (rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(ptm != null) ptm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public int getQuantityOfProduct(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GETPRODUCTQUANTITY);
            ptm.setString(1, productID);
            rs = ptm.executeQuery();
            while (rs.next()) {                              
                quantity = rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return quantity;
    }
    public boolean updateQuantity(int quantity, ProductDTO product) throws SQLException {
        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEQUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, product.getProductID());
                check = ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }
    
}
