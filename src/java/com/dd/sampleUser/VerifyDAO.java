/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.sampleUser;

import com.dd.utils.DBUtils;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author DevDD
 */
public class VerifyDAO {

    private static final String INSERT = "INSERT INTO tblVerifyCode values (?,?,?)";
    private static final String DELETE = "DELETE FROM tblVerifyCode WHERE email = ?";
    private static final String CHECK_CODE_EMAIL = "SELECT email, code FROM tblVerifyCode WHERE email = ? AND code = ?";
    private static final String GET_TIME = "select SendCodeTime from tblVerifyCode WHERE email = ?";

    public void insert(String email, String code, String date) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, email);
                ptm.setString(2, code);
                ptm.setString(3, date);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(String email) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, email);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean check_id_email(String email, String code) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CODE_EMAIL);
                ptm.setString(1, email);
                ptm.setString(2, code);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public String getTime(String email) throws SQLException, ClassNotFoundException, NamingException {
        String time = "";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TIME);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    time = rs.getString("SendCodeTime");
                }
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return time;
    }
}
