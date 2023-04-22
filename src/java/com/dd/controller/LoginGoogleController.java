/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.controller;

import com.dd.details.Constants;
import com.dd.sampleUser.UserDAO;
import com.dd.sampleUser.UserDTO;
import com.dd.sampleUser.UserGoogleDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;

/**
 *
 * @author DevDD
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";

    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String AD = "AD";
    private static final String US_PAGE = "user.jsp";
    private static final String US = "US";
    private static final Logger logger = Logger.getLogger(LoginGoogleController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDTO user = getUserInfo(accessToken);
            UserDAO dao = new UserDAO();
            int leng = user.getId().length();
            String newID = "SE16" + user.getId().substring(leng - 4, leng);
            String fullName = user.getName();
            String role = "US";
            String password = user.getId().substring(3, 9);
            String avatar = user.getPicture();
            String email = user.getEmail();
            UserDTO checkExist = dao.checkEmailDuplicate(email);
            if (checkExist != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", checkExist);
                String roleID = checkExist.getRoleID();
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                    logger.info("LoginGoogle with ADMIN role, username: " + fullName);

                } else if (US.equals(roleID)) {
                    url = US_PAGE;
                    logger.info("LoginGoogle with USER role, username: " + fullName);
                } else {
                    request.setAttribute("ERROR", "Your role is not support!");
                    logger.warn("Your role is not support!");
                }
            } else {
                UserDTO users = new UserDTO(newID, fullName, email, avatar, role, password);
                boolean checkInsert = dao.create(users);
                if (checkInsert) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", users);
                    url = US_PAGE;
                    logger.info("The new user \"" +  users.getFullName() + "\" were inserted into the database");
                } else {
                    request.setAttribute("ERROR", "Unknown ERROR");
                    logger.warn("Unknown ERROR");
                }
            }
        } catch (Exception e) {
            logger.error("Error at LoginGoogleController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class
        );
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO google = new Gson().fromJson(response, UserGoogleDTO.class
        );

        return google;
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
