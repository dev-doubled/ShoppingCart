/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.phone;

import com.dd.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DevDD
 */
public class OrderDetailDAO {

    private static final String ADD = "INSERT INTO tblOrderDetail(odid ,pid, oid, quantity, price) VALUES(?,?,?,?,?)";
    private static final String GET_QUANTITY = "SELECT quantity FROM tblOrderDetail WHERE Pid = ?";
    
    public void add(OrderDetail orderDetail) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD);
                ptm.setInt(1, orderDetail.getODid());
                ptm.setString(2, orderDetail.getPid());
                ptm.setInt(3, orderDetail.getOid());
                ptm.setInt(4, orderDetail.getQuantity());
                ptm.setInt(5, orderDetail.getPrice());
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public int getQuantity(String pid) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY);
                ptm.setString(1, pid);
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }
}
