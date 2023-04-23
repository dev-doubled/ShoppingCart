/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.controller;

import com.dd.emailsender.EmailSender;
import com.dd.phone.Cart;
import com.dd.phone.Order;
import com.dd.phone.OrderDAO;
import com.dd.phone.OrderDetail;
import com.dd.phone.OrderDetailDAO;
import com.dd.phone.Phone;
import com.dd.phone.PhoneDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCartSuccess.jsp";
    private static final long serialVersionUID = 1L;
    private static final String SECRET_KEY = "6Lcyh_kkAAAAAEPNvRP_ncMFLAIBXI2uQdd25JUa";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone");
            String address = request.getParameter("address");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            Random rd = new Random();
            int orderID = rd.nextInt(100) + 1;
            int orderDetailID = rd.nextInt(901) + 100;
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            PhoneDAO dao = new PhoneDAO();
            List<Phone> listPhone = new ArrayList<>();
            Phone PhoneSender = new Phone();
            String orderInfo = "Thông tin đơn hàng:\n";
            //reCAPTCHA
            String verificationUrl = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + SECRET_KEY
                    + "&response=" + gRecaptchaResponse;
            String jsonResponse = CreateController.HttpUtils.sendGet(verificationUrl);
            JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
            boolean success = jsonObject.get("success").getAsBoolean();
            if (success) {
                //checkout
                if (cart != null) {
                    boolean checkQuantity = dao.canOrder(cart);
                    if (checkQuantity) {
                        int total = 0;
                        //add vao order detail
                        for (Phone phone : cart.getCart().values()) {
                            total += phone.getPhonePrice() * phone.getPhoneQuantity();
                        }
                        Order order = new Order(orderID, name, email, phoneNumber, address, total);
                        OrderDAO orderdao = new OrderDAO();
                        orderdao.add(order);
                        //add vao OrderDetail
                        for (Phone phone : cart.getCart().values()) {
                            OrderDetail orderDetail = new OrderDetail(orderDetailID, phone.getPhoneID(), orderID, phone.getPhoneQuantity(), (int) (phone.getPhoneQuantity() * phone.getPhonePrice()));
                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            orderDetailDAO.add(orderDetail);
                            //cap nhat lai so luong  san pham
                            dao.updateQuantity(orderDetail.getQuantity(), orderDetail.getPid());
                            //tao thong tin de gui ve mail
                            PhoneSender = dao.getPhone(orderDetail.getPid());
                            int newQuantity = orderDetailDAO.getQuantity(orderDetail.getPid());
                            PhoneSender.setPhoneQuantity(newQuantity);
                            listPhone.add(PhoneSender);
                        }
                        for (int i = 0; i < listPhone.size(); i++) {
                            orderInfo += "Tên sản phẩm: " + listPhone.get(i).getPhoneName() + "\n" + "Số lượng: " + listPhone.get(i).getPhoneQuantity() + "\n"
                                    + "Giá tiền: " + listPhone.get(i).getPhonePrice() * listPhone.get(i).getPhoneQuantity() + "$" + "\n" + "------------------------------------------------" + "\n";
                        }
                        request.setAttribute("SUCCESS", "Vui lòng kiểm tra email " + email + " để xác nhận đơn hàng.");
                        session.setAttribute("CART", null);
                        EmailSender emailSender = new EmailSender("javaweblearning@gmail.com");
                        int randomNum = rd.nextInt(900000000) + 100000000;
                        String subject = "Xác nhận đơn hàng #" + orderID;
                        String message = "Cảm ơn quý khách đã đặt hàng của chúng tôi. Đơn hàng của quý khách đã được nhận và đang được xử lý. Vui lòng kiểm tra lại thông tin đơn hàng sau đây:\n\n"
                                + "Tên khách hàng: " + name + "\n"
                                + "Email: " + email + "\n"
                                + "Điện thoại: " + phoneNumber + "\n"
                                + "Địa chỉ: " + address + "\n\n"
                                + orderInfo
                                + "Tổng giá trị đơn hàng: " + total + "$\n\n"
                                + "Mã đơn hàng: " + randomNum + "\n"
                                + "Cảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi. Hẹn gặp lại bạn lần sau!";
                        emailSender.SendMessage(email, subject, message);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("QUANTITY_ERROR", "Số lượng sản phẩm không đủ");
                    }
                }
            } else {
                request.setAttribute("RECAPTCHA_ERROR", "Please check reCAPTCHA!");
            }

        } catch (Exception e) {
            log("Error at OrderController: " + e.toString());
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
    //reCAPTCHA
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
