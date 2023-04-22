/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.controller;

import com.dd.sampleUser.UserDAO;
import com.dd.sampleUser.UserDTO;
import com.dd.sampleUser.UserError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author DevDD
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";
    private static final long serialVersionUID = 1L;
    private static final String SECRET_KEY = "6Lcyh_kkAAAAAEPNvRP_ncMFLAIBXI2uQdd25JUa";
    private static final Logger logger = Logger.getLogger(CreateController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String avatar = request.getParameter("avatar");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

            String verificationUrl = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + SECRET_KEY
                    + "&response=" + gRecaptchaResponse;

            String jsonResponse = HttpUtils.sendGet(verificationUrl);
            JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);

            boolean success = jsonObject.get("success").getAsBoolean();
            if (success) {
                UserDAO dao = new UserDAO();
                boolean checkValid = true;
                //check existed userID
                if (userID.length() > 10 || userID.length() < 3) {
                    checkValid = false;
                    userError.setUserIDError("User ID needs more than 2 characters");
                }
                if (fullName.length() > 20 || fullName.length() < 3) {
                    checkValid = false;
                    userError.setFullNameError("Full Name needs more than 2 characters");
                }
                UserDTO checkExist = dao.checkEmailDuplicate(email);
                if (checkExist != null) {
                    checkValid = false;
                    userError.setEmailError("The email already exists");
                }
                if (password.length() > 10 || password.length() < 1) {
                    checkValid = false;
                    userError.setPasswordError("Password at least 1 characters");
                }
                if (!password.equals(confirm)) {
                    checkValid = false;
                    userError.setConfirmError("Confirm password does not match the password");
                }
                //All complete
                if (checkValid == true) {
                    UserDTO user = new UserDTO(userID, fullName, email, avatar, roleID, password);
                    boolean checkInsert = dao.create2(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Unknown ERROR");
                        logger.warn("Unknown ERROR");
                    }
                } else {
                    request.setAttribute("USER_ERROR", userError);
                    logger.warn("USER_ERROR: " + userError);
                }
            } else {
                request.setAttribute("RECAPTCHA_ERROR", "Please check reCAPTCHA!");
                logger.warn("RECAPTCHA_ERROR");
            }

        } catch (Exception e) {
            logger.error("Error at CreateController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("User ID already exist!");
                logger.warn("User ID already exist!");
                request.setAttribute("USER_ERROR", userError);
            }
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

    public static class HttpUtils {

        public static String sendGet(String url) throws Exception {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // return JSON response
            return response.toString();
        }
    }

}
