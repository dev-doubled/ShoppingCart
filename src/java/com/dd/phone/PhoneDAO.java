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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DevDD
 */
public class PhoneDAO {

    private static final String SEARCH = "SELECT Pid, Pname, Pprice, Pquantity, Pimage FROM tblProducts WHERE Pname LIKE ? "
            + "Order by Pprice";
    private static final String GET_PHONE = "SELECT Pid, Pname, Pprice, Pquantity, Pimage FROM tblProducts WHERE Pid LIKE ? ";
    private static final String SEARCH1 = "SELECT Pquantity FROM tblProducts WHERE Pid LIKE ?";
    private static final String CHECK_DUPLICATE = "SELECT Pid FROM tblProducts WHERE Pid = ?";
    private static final String CREATE = "INSERT INTO tblProducts(Pid, Pname, Pprice, Pimage, Pquantity) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE tblProducts SET Pname = ?, Pprice = ?  WHERE Pid = ?";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET Pquantity = Pquantity - ? WHERE Pid = ?";
    private String CAN_ORDER = "SELECT Pid FROM tblProducts WHERE ";

    public List<Phone> getAllPhone(String search, int currPage) throws SQLException {
        List<Phone> listPhone = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                int count = 1;
                int maxCount = currPage * 12;
                int minCount = (currPage - 1) * 12 + 1;
                while (rs.next()) {
                    if (minCount <= count && count <= maxCount) {
                        String id = rs.getString("Pid");
                        String name = rs.getString("Pname");
                        double price = Double.parseDouble(rs.getString("Pprice"));
                        String image = rs.getString("Pimage");
                        int quantity = rs.getInt("Pquantity");
                        listPhone.add(new Phone(id, name, image, price, quantity));
                    }
                    count++;
                }
            }
        } catch (Exception e) {
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
        return listPhone;
    }
    
    public Phone getPhone(String phoneID) throws SQLException {
        Phone phone = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PHONE);
                ptm.setString(1, "%" + phoneID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                        String id = rs.getString("Pid");
                        String name = rs.getString("Pname");
                        double price = Double.parseDouble(rs.getString("Pprice"));
                        String image = rs.getString("Pimage");
                        int quantity = rs.getInt("Pquantity");
                        phone = new Phone(id, name, image, price, quantity);
                }
            }
        } catch (Exception e) {
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
        return phone;
    }

    public int quantity(String phoneID) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH1);
                ptm.setString(1, "%" + phoneID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {;
                    quantity = rs.getInt("Pquantity");

                }
            }
        } catch (Exception e) {
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
        return quantity;
    }

    public boolean checkDuplicate(String phoneID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, phoneID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    public boolean create(Phone phone) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, phone.getPhoneID());
                ptm.setString(2, phone.getPhoneName());
                ptm.setInt(3, (int) phone.getPhonePrice());
                ptm.setString(4, phone.getPhoneImage());
                ptm.setInt(5, phone.getPhoneQuantity());
                result = ptm.executeUpdate() > 0 ? true : false;
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
        return result;
    }

    public boolean update(Phone newPhone) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, newPhone.getPhoneName());
                ptm.setInt(2, (int) newPhone.getPhonePrice());
                ptm.setString(3, newPhone.getPhoneID());
                result = ptm.executeUpdate() > 0 ? true : false;

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
        return result;
    }

    public void updateQuantity(int quantity, String phoneId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, phoneId);
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

    public boolean canOrder(Cart cart) throws SQLException {
        boolean result = false;
        Connection conn = null;
        Statement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "(1 != 1) ";
                for (Phone phone : cart.getCart().values()) {
                    sql += String.format("OR (Pid = '%s' AND Pquantity >= %s) ", phone.getPhoneID(), phone.getPhoneQuantity());
                }
                CAN_ORDER += sql;
                ptm = conn.createStatement();
                rs = ptm.executeQuery(CAN_ORDER);
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count == cart.getCart().values().size()) {
                    result = true;
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
        return result;
    }
}
