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
import java.sql.Statement;

/**
 *
 * @author DevDD
 */
public class OrderDAO {

    private static final String ADD = "INSERT INTO tblOrder(oid, username, email, address, phone, total) VALUES(?,?,?,?,?,?)";

    public void add(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD);
                ptm.setInt(1, order.getOid());
                ptm.setString(2, order.getUsername());
                ptm.setString(3, order.getEmail());
                ptm.setString(4, order.getAddress());
                ptm.setString(5, order.getPhone());
                ptm.setInt(6, order.getTotal());
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

}
