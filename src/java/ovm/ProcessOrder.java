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
                        
 "                       <header class=\"clearfix\"  style='background-image: url(./images/hd1.jpg);background-color: whitesmoke;background-position: left ;'>"+
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
                String area = null;
                String qry = "select * from Customer Where email='"+cust+"'";
                //System.out.print(qry);
                rs = st.executeQuery(qry);
                
                out.print("<center>Order ID"+orderid+"<br /><table border='1'><tr><th>Customer Name</th><th>Mobile No.</th><th>Address of Customer</th><th>Area </th><th>City</th><th>PinCode</th></tr>");
                if (rs.next()) {
                    out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</tr>");
                    area=rs.getString(5);
                    
                }
                rs.close();
                out.print("</center></table><br /><br />");
                qry = "select * from Vendor Where area='"+area+"'";
                //System.out.print(qry);
                rs = st.executeQuery(qry);
                
                out.print("<center>Nearest Vednor For the Customer<br /><table border='1'><tr><th>Vendor Name</th><th>Mobile No.</th><th>Address of Vendor</th><th>Area </th><th>City</th><th>PinCode</th><th>Assign Orders</th></tr>");
                while (rs.next()) {
                    out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td><a href='./AssignOrder?vemail="+rs.getString(1)+"&oid="+orderid+"'>Assign Order</a></td></tr>");

                    
                }
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
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
