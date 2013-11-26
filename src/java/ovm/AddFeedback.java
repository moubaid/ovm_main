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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oubaid
 */
public class AddFeedback extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String subject=request.getParameter("subject");
        String text=request.getParameter("text");
        Connection con;//=DriverConnection.getConnection();
            PreparedStatement ps=null;//=con.createStatement();
            Statement st=null;
            ResultSet rs=null;
            HttpSession hs=request.getSession(false);
                    if(hs==null)
                        {
                        response.sendRedirect("Login.html");
                                               }
                    String uname=(String)hs.getAttribute("UserName");
                    int totqty=(Integer)hs.getAttribute("TotalQty");
        try {
            
            String sqlstmt = "INSERT INTO FeedBack"
			+ " VALUES "
			+ "(?,?,?,?)";
                con=DriverConnection.getConnection();
                ps=con.prepareStatement(sqlstmt);
                 ps.setString(1, uname);
                ps.setString(2, subject);
                ps.setString(3, text);
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
                ps.setString(4,ft.format(dNow).toString());
                    int count=ps.executeUpdate();
                                
                                if(count==1||count==Statement.SUCCESS_NO_INFO)
                                {
                                        out.println("<head><script>function load(){alert(\"Feedback Added Successfully\");}</script></head>"
                                        + "<body onload=\"load()\"><h1>Added Feedback</h1><a href='feedback.jsp'>Click Here for Feedback</a></body></html>");
                                        //response.sendRedirect("feedback.jsp");
                                }
                                
                     
                                else
                                {
                                    out.println("<html><body style=\"background-image: url(./images/bg1.jpg);\"><center>");
                                    out.println("Given details are incorrect<br/>");
                                    out.println("<li><i>Please try again later</i></li>");
                                    out.println("<br /><a href='feedback.jsp'>Click Here</a>For Feedback</center></body></html>");
                                }
        
            
           
            
        } finally {            
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
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
