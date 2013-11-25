/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.text.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oubaid
 */
public class BuyServlet extends HttpServlet {

    Connection con;//=DriverConnection.getConnection();
    PreparedStatement ps = null;//=con.createStatement();
    Statement st = null;
    ResultSet rs = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession(false);
        if (hs == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Login.html");
            rd.forward(request, response);
            return;
        }
        //out.print("Im Threre 1");
        String uname = (String) hs.getAttribute("UserName");
        double totamt = (Double) hs.getAttribute("TotalAmount");
        int totqty = (Integer) hs.getAttribute("TotalQty");
        try {

            String sqlstmt = "INSERT INTO OVM_main.Order(email,dop,totalamt,totqty)"
                    + " VALUES "
                    + "(?,?,?,?)";
            con = DriverConnection.getConnection();

            ps = con.prepareStatement(sqlstmt);

            ps.setString(1, uname);
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");


            ps.setString(2, ft.format(dNow).toString());
            ps.setDouble(3, totamt);
            ps.setDouble(4, totqty);
            //out.print("Im Threre 3");
            con.setAutoCommit(false);

            int count = ps.executeUpdate();
            //out.print("Im Threre 4");
            if (count == 1 || count == Statement.SUCCESS_NO_INFO) {
                out.println("<html><body style=\"background-image: url(./images/bg1.jpg);\"><center>");
                out.println("Items Purchased Successfully<br/>");
                out.println("<li><i>Thank You for Buying</i></li>");
                out.println("</center></body></html>");
                //String qry="select o_id from order where "
                st = con.createStatement();
                Collection products = null;
                /*products = (Collection) hs.getAttribute("products");
                if (products == null) {
                    out.println("<tr><td colspan='5' align='center'>");
                    out.println("You have not Added Any Products");
                    out.println("</td></tr>");
                } else {
                    Iterator i = products.iterator();
                    while (i.hasNext()) {

                        Product p = (Product) i.next();
                    }
                }*/

                String qry = "insert into OVM_main.onlinestatus(email,start_time,status) values('" + uname + "','" + ft.format(dNow) + "','Your Order Forwarded to Vendor')";
                count = st.executeUpdate(qry);
                //out.print("Im Threre 6");
                if (count == 1 || count == Statement.SUCCESS_NO_INFO) {
                    out.println("<html><body><center>");
                    out.println("Items Purchased Successfully<br/>");
                    out.println("<li><i>Thank You for Buying</i></li>");
                    out.println("</center></body></html>");
                    con.commit();
                    totqty = 0;
                    totamt = 0;
                    hs.setAttribute("TotalAmount", totamt);
                    hs.setAttribute("TotalQty", totqty);

                    hs.setAttribute("products", products);

                } else {
                    out.println("<html><body><center>");
                    out.println("Given details are incorrect<br/>");
                    out.println("<li><i>Please try again later</i></li>");
                    out.println("</center></body></html>");
                }
            } else {
                out.println("<html><body><center>");
                out.println("Given details are incorrect<br/>");
                out.println("<li><i>Please try again later</i></li>");
                out.println("</center></body></html>");
            }
            //out.print("Im Threre 5");

            //out.print("Im Threre 7");
        } catch (Exception e) {
            //out.print("Im Threre 8"+e);
            throw new ServletException("Initalization failed, Unable to get DB Connection");
        } finally {
            //out.print("Im Threre 9");
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
