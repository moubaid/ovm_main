/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AssignOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          HttpSession hs = request.getSession(false);
                if (hs == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                    rd.forward(request, response);
                    return;
                }
                String uname = (String) request.getAttribute("UserName");
        try {
          
            Connection con = null;
            try {
                int orderid = Integer.parseInt(request.getParameter("oid"));
                String vend = request.getParameter("vemail");
                con = DriverConnection.getConnection();

                // PreparedStatement ps=null;//=con.createStatement();
                Statement st = null;
              
                ResultSet rs;//=null;

              
                String area = null;
                  st = con.createStatement();
                  out.print("1<br/>");
                String qry = "update ovm_main.order set vemail='"+vend+"' where o_id="+orderid;
                out.print("2<br/>");
                int count = st.executeUpdate(qry);

                    out.print("3<br/>");
                if (count==1||count== Statement.SUCCESS_NO_INFO) {
                  response.sendRedirect("AdminHome");  

                }
                else
                {
                    out.print("4<br/>");
                    out.println("<html><body><h1>Error in the Details</h1></body></html>");
                }
                
                

               
            }
            catch(Exception e){
                out.print("Error "+e);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AssignOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AssignOrder.class.getName()).log(Level.SEVERE, null, ex);
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AssignOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AssignOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>
    }
