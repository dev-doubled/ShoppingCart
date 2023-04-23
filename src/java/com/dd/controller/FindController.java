/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.controller;

import com.dd.emailsender.EmailSender;
import com.dd.sampleUser.UserDAO;
import com.dd.sampleUser.UserDTO;
import com.dd.sampleUser.VerifyDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DevDD
 */
@WebServlet(name = "FindController", urlPatterns = {"/FindController"})
public class FindController extends HttpServlet {

    private static final String ERROR = "forgotPass.jsp";
    private static final String SUCCESS = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String email = request.getParameter("email");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String newDate = dateFormat.format(date);
            HttpSession session = request.getSession();
            session.setAttribute("FORGET_EMAIL", email);
            session.setAttribute("FORGET_USERID", userID);
            UserDAO dao = new UserDAO();
            UserDTO user = dao.getUser(userID);
            boolean checkExist = dao.check_id_email(userID, email);
            if (checkExist) {
                Random rd = new Random();
                int randomNum = rd.nextInt(900000) + 100000;
                String code = randomNum + "";
                VerifyDAO verifyDAO = new VerifyDAO();

                verifyDAO.insert(email, code, newDate);

                EmailSender emailSender = new EmailSender("javaweblearning@gmail.com");
                String subject = "Xác nhận mật khẩu";
                String message = "Dear " + user.getFullName() + "\n\n"
                        + "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn\n\n"
                        + "Để đặt lại mật khẩu của bạn, vui lòng nhập VERIFY CODE dưới đây:\n\n"
                        + code + "\n\n"
                        + "Trân trọng!!!";

                emailSender.SendMessage(email, subject, message);
                request.setAttribute("START_COUNT", true);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_FORGOT", "UserID or Email does not exist!");
            }
        } catch (Exception e) {
            log("Error at FindController: " + e.toString());
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

}
