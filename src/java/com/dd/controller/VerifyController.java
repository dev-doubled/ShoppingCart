/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.controller;

import com.dd.sampleUser.UserDTO;
import com.dd.sampleUser.VerifyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "VerifyController", urlPatterns = {"/VerifyController"})
public class VerifyController extends HttpServlet {

    private static final String ERROR = "verify.jsp";
    private static final String SUCCESS = "enterPass.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            VerifyDAO dao = new VerifyDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("FORGET_EMAIL");
            String code_1 = request.getParameter("code_1");
            String code_2 = request.getParameter("code_2");
            String code_3 = request.getParameter("code_3");
            String code_4 = request.getParameter("code_4");
            String code_5 = request.getParameter("code_5");
            String code_6 = request.getParameter("code_6");
            String CODE = code_1 + code_2 + code_3 + code_4 + code_5 + code_6;
            //check email
            boolean check = dao.check_id_email(email, CODE);
            //check dateTime
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String newDate = dateFormat.format(date);
            String SQL = dao.getTime(email);
            String DateTime[] = newDate.split(" ");
            String day = DateTime[0];
            String time = DateTime[1];
            String sqlRealDate = SQL.split(" ")[0];
            String sqlRealTime = SQL.split(" ")[1];
            String sqlTime = sqlRealTime.split(":")[0] + sqlRealTime.split(":")[1];
            String realTime = time.split(":")[0] + time.split(":")[1];
            int convertRealTime = Integer.parseInt(realTime);
            int convertSqlTime = Integer.parseInt(sqlTime);
            //check verify
            if (check && (sqlRealDate.equals(day) && convertRealTime - convertSqlTime < 1)) {
                dao.delete(email);
                url = SUCCESS;
                
            } else {
                request.setAttribute("ERROR_VERIFY", "Verify Code invalid OR Time Out!");
            }
        } catch (Exception e) {
            log("Error at VerifyController: " + e.toString());
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
