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
public class ProcessOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(""
                    + "<!DOCTYPE html>"+
"<html lang=\"en\" class=\"no-js\">"+
"	<head>"+
"		<meta charset=\"UTF-8\" />"+
"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> "+
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> "+
"		<title>-=[ OVM : Home ]=-</title>"+
"		<meta name=\"description\" content=\"Online Vegetable Market: Buy Vegetable and fruits online\" />"+
"		<meta name=\"keywords\" content=\"online, vegetable, fruits, market, buy\" />"+
"		<meta name=\"author\" content=\"Obbu\" />"+
"		<link rel=\"shortcut icon\" href=\"../favicon.ico\"> "+
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />"+
"		<script src=\"js/modernizr.custom.63321.js\"></script>"+
"	</head>"+
 "       <body style=\"background-image: url(./images/bg1.jpg);\">"+
"		<div class=\"container\">	"+
"			<!-- Codrops top bar -->"+
"			<div class=\"codrops-top clearfix\">"+
"				<a href=\"register.html\"><strong>&laquo; Click Here to Register </strong></a>"+
				
 "                                  <a href=\"VendorHome\"><strong>Home</strong></a>"+
  "                              <a href=\"./GetProductsServlet?cate=Fruit\"><strong>View Orders</strong></a>"+
"                                <a href=\"./GetProductsServlet?cate=Vegetable\"><strong>Current</strong></a>"+
"                                <a href=\"./GetCartDetails\"><strong>Cart[0]</strong></a>"+
 "                               <a href=\"./BuyServlet\"><strong>Buy Items</strong></a>"+
                                
  "                              <span class=\"right\"><a href=\"Login.html\"><strong>Login</strong></a></span>"+
                             
                                
"			</div><!--/ Codrops top bar -->"+
                        
 "                       <header class=\"clearfix\"  style=\"background-color:#fff;\">"+
"     <h1 style=\" \">Online Vegetable Market <span style=\"color:#0C6\">Buy Fruits and Vegetable Online</span></h1>"+
 
"			</header>"+
                       
"			<div class=\"main\">");
             Connection con = null;
            try {
                String orderid=request.getParameter("oid");
                String cust=request.getParameter("cust");
                con = DriverConnection.getConnection();

                // PreparedStatement ps=null;//=con.createStatement();
                Statement st = null;
                st = con.createStatement();
                ResultSet rs;//=null;

                 HttpSession hs=request.getSession(false);    
                if (hs == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                    rd.forward(request, response);
                    return;
                }
                String uname=(String)request.getAttribute("UserName");
                
                String qry = "select * from Customer Where email='"+cust+"'";
                //System.out.print(qry);
                rs = st.executeQuery(qry);
                out.print("<form action='ProcessOrder' method='post'>");
                out.print("<center><table border='1'><tr><th>Order ID</th><th>Customer Mail ID</th><th>Date of Purchase</th><th>Total Amount</th><th>Total Qty</th><th>Process Order</th>");
                while (rs.next()) {
                    out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");

                    
                }
                out.print("</center></table></form>");
            }
            catch(Exception e){
            out.print("Error"+e);
                    }
                          	
				
                            
out.print("			</div>"+
"		</div><!-- /container -->"+
"		<script src=\"js/jquery.min.js\"></script>"+
"		<script src=\"js/jquery.catslider.js\"></script>"+
"		<script>"+
"			$(function() {"+
"				$( '#mi-slider' ).catslider();"+

"			});"+
"		</script>"+
	"</body>"+
"</html>");
            
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
