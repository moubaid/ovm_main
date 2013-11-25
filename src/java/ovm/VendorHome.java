/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class VendorHome extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
                  HttpSession hs=request.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");
            rd.forward(request, response);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
                
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(""
                    + "<!DOCTYPE html>"
                    + "<html lang=\"en\" class=\"no-js\">"
                    + "	<head>"
                    + "		<meta charset=\"UTF-8\" />"
                    + "		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> "
                    + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> "
                    + "		<title>-=[ OVM : Home ]=-</title>"
                    + "		<meta name=\"description\" content=\"Online Vegetable Market: Buy Vegetable and fruits online\" />"
                    + "		<meta name=\"keywords\" content=\"online, vegetable, fruits, market, buy\" />"
                    + "		<meta name=\"author\" content=\"Obbu\" />"
                    + "		<link rel=\"shortcut icon\" href=\"../favicon.ico\"> "
                    + "		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />"
                    + "		<script src=\"js/modernizr.custom.63321.js\"></script>"
                    + "	</head>"
                    + "       <body style=\"background-image: url(./images/bg1.jpg);\">"
                    + "		<div class=\"container\">	"
                    + "			<!-- Codrops top bar -->"
                    + "			<div class=\"codrops-top clearfix\">"
                    + "				<a href=\"VendorDetails\"><strong>&laquo; Welcome, "+uname+"</strong></a>"
                    + "                                  <a href=\"VendorHome\"><strong>Home</strong></a>"
                    + "                              <a href=\"./GetProductsServlet?cate=Fruit\"><strong>View Orders</strong></a>"
                    + "                                <a href=\"./GetProductsServlet?cate=Vegetable\"><strong>Current</strong></a>"
                    + "                                <a href=\"./GetCartDetails\"><strong>Cart[0]</strong></a>"
                    + "                               <a href=\"./BuyServlet\"><strong>Buy Items</strong></a>"
                    + "                              <span class=\"right\"><a href=\"Logout\"><strong>Logout</strong></a></span>"
                    + "			</div><!--/ Codrops top bar -->"
                    + "                       <header class=\"clearfix\"  style='background-image: url(./images/hd1.jpg);background-color: whitesmoke;background-position: left ;'>"
                    + "     <h1 style=\" \">Online Vegetable Market <span style=\"color:#0C6\">Buy Fruits and Vegetable Online</span></h1>"
                    + "			</header>"
                    + "			<div class=\"main\">");
            Connection con = null;
            try {
                con = DriverConnection.getConnection();

                // PreparedStatement ps=null;//=con.createStatement();
                Statement st = null;
                st = con.createStatement();
                ResultSet rs;//=null;

                String qry = "select * from ovm_main.Order where vemail like 'tamjeedqazi@gmail.com'";//+uname+"%'";
                out.print("Hello 1");
                rs = st.executeQuery(qry);
                out.print("Hello 2");
                while (rs.next()) {
                    out.print("Hello 3");
                    out.print("<h1>"+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));

                    
                }
                out.print("Hello 5");
            }
            catch(Exception e){out.println("Error :"+e);}
                    out.print("			</div>"
                            + "		</div><!-- /container -->"
                            + "		<script src=\"js/jquery.min.js\"></script>"
                            + "		<script src=\"js/jquery.catslider.js\"></script>"
                            + "		<script>"
                            + "			$(function() {"
                            + "				$( '#mi-slider' ).catslider();"
                            + "			});"
                            + "		</script>"
                            + "</body>"
                            + "</html>");

                }  finally {
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
            processRequest(request, response);
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
