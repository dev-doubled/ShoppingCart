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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DevDD
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, email, roleID, avatar FROM tblUsers WHERE userID = ? AND password = ?";
    private static final String SEARCH = "SELECT userID, fullName, email, roleID, avatar FROM tblUsers WHERE fullName LIKE ?";
    private static final String DELETE = "DELETE tblUsers WHERE userID = ?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName = ?, roleID = ?  WHERE userID = ?";
    private static final String UPDATE_PASSWORD = "UPDATE tblUsers SET password = ?  WHERE userID = ?";
    private static final String CHECK_DUPLICATE = "SELECT userID FROM tblUsers WHERE userID = ?";
    private static final String CHECK_EMAIL_DUPLICATE = "SELECT * FROM tblUsers WHERE email = ?";
    private static final String CREATE = "INSERT INTO tblUsers(userId, fullName, roleID, password, avatar, email) VALUES(?,?,?,?,?,?)";
    private static final String CHECK_ID_EMAIL = "SELECT userID, email FROM tblUsers WHERE userID = ? AND email = ?";
    private static final String GET = "SELECT * FROM tblUsers WHERE userID = ?";
    private static final String SHOW = "SELECT top 2 * FROM tblUsers";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO loginUser = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String avatar = rs.getString("avatar");
                    loginUser = new UserDTO(userID, fullName, email, avatar, roleID);
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
        return loginUser;

    }

    public UserDTO getUser(String userID) throws SQLException {
        UserDTO loginUser = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String avatar = rs.getString("avatar");
                    loginUser = new UserDTO(userID, fullName, email, avatar, roleID);
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
        return loginUser;

    }

    public List<UserDTO> getListUser(String search, int currPage) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
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
                int maxCount = currPage * 5;
                int minCount = (currPage - 1) * 5 + 1;
                while (rs.next()) {
                    if (minCount <= count && count <= maxCount) {
                        String userID = rs.getString("userID");
                        String fullName = rs.getString("fullName");
                        String email = rs.getString("email");
                        String roleID = rs.getString("roleID");
                        String avatar = rs.getString("avatar");
                        String password = "****";
                        listUser.add(new UserDTO(userID, fullName, email, avatar, roleID, password));
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
        return listUser;
    }

    public List<UserDTO> getListUserTop2() throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String avatar = rs.getString("avatar");
                    String password = "****";
                    listUser.add(new UserDTO(userID, fullName, email, avatar, roleID, password));
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
        return listUser;
    }

    public boolean delete(String userId) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userId);
                int delete = ptm.executeUpdate();
                if (delete > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
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

    public boolean update(UserDTO newUser) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, newUser.getFullName());
                ptm.setString(2, newUser.getRoleID());
                ptm.setString(3, newUser.getUserID());
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

    public void updatePassword(String newPasswor, String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, newPasswor);
                ptm.setString(2, userID);
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

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
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

    public UserDTO checkEmailDuplicate(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EMAIL_DUPLICATE);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String emails = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String avatar = rs.getString("avatar");
                    String password = "****";
                    user = new UserDTO(userID, fullName, emails, avatar, roleID, password);
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
        return user;
    }

    public boolean check_id_email(String userID, String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_ID_EMAIL);
                ptm.setString(1, userID);
                ptm.setString(2, email);
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

    public boolean create(UserDTO user) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                ptm.setString(5, user.getAvatar());
                ptm.setString(6, user.getEmail());
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

    public boolean create2(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                ptm.setString(5, user.getAvatar());
                ptm.setString(6, user.getEmail());
                result = ptm.executeUpdate() > 0 ? true : false;
            }
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
