
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oubaid
 */
public class UserHome extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\" class=\"no-js\">\n" +
"	<head>\n" +
"		<meta charset=\"UTF-8\" />\n" +
"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> \n" +
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \n" +
"		<title>-=[ OVM : Home ]=-</title>\n" +
"		<meta name=\"description\" content=\"Online Vegetable Market: Buy Vegetable and fruits online\" />\n" +
"		<meta name=\"keywords\" content=\"online, vegetable, fruits, market, buy\" />\n" +
"		<meta name=\"author\" content=\"Obbu\" />\n" +
"		<link rel=\"shortcut icon\" href=\"../favicon.ico\"> \n" +
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n" +
"		<script src=\"js/modernizr.custom.63321.js\"></script>\n" +
"	</head>\n" +
"	<body>\n" +
"		<div class=\"container\">	\n" +
"			<!-- Codrops top bar -->\n" +
"			<div class=\"codrops-top clearfix\">\n" +
"				<a href=\"register.html\"><strong>&laquo; Welcome Mr. ");
            String uname=request.getParameter("username");
            out.println(uname+" </strong>"+ "</a>\n" +
"				<span class=\"right\"><a href=\"index.html\"><strong>Login Out</strong></a></span>\n" +
"			</div><!--/ Codrops top bar -->\n" +
"			<header class=\"clearfix\">\n" +
"				<h1>Online Vegetable Market <span style=\"color:#0C6\">Buy Fruits and Vegetable Online</span></h1>\n" +
"			</header>\n" +
"			<div class=\"main\">\n" +
"				<div id=\"mi-slider\" class=\"mi-slider\">\n" +
"					<ul>\n" +
"						<li><a href=\"#\"><img src=\"images/1.jpg\" alt=\"img01\"><h4>Lady Finger</h4></a><a href='"+response.encodeURL("GetProductsServlet")+"'>Add Cart</a> </li>\n" +
"						<li><a href=\"#\"><img src=\"images/2.jpg\" alt=\"img02\"><h4>Tomato</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/3.jpg\" alt=\"img03\"><h4>Onion</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/4.jpg\" alt=\"img04\"><h4>Potato</h4></a></li>\n" +
"						\n" +
"					</ul>\n" +
"					<ul>\n" +
"						<li><a href=\"#\"><img src=\"images/5.jpg\" alt=\"img05\"><h4>Apple</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/6.jpg\" alt=\"img06\"><h4>Banana</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/7.jpg\" alt=\"img07\"><h4>Orange</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/8.jpg\" alt=\"img08\"><h4>Pomegranate</h4></a></li>\n" +
"					</ul>\n" +
"					<ul>\n" +
"						<li><a href=\"#\"><img src=\"images/9.jpg\" alt=\"img09\"><h4>Your Delivery</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/10.jpg\" alt=\"img10\"><h4>Your Returns</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/11.jpg\" alt=\"img11\"><h4>Feed Back</h4></a></li>\n" +
"					</ul>\n" +
"					<ul>\n" +
"						<li><a href=\"#\"><img src=\"images/12.jpg\" alt=\"img12\"><h4>Discounts</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/13.jpg\" alt=\"img13\"><h4>Offers</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/14.jpg\" alt=\"img14\"><h4>About Us</h4></a></li>\n" +
"						<li><a href=\"#\"><img src=\"images/15.jpg\" alt=\"img15\"><h4>Contact Us</h4></a></li>\n" +
"					</ul>\n" +
"					<nav>\n" +
"						<a href=\"#\">Vegetables</a>\n" +
"						<a href=\"#\">Fruits</a>\n" +
"						<a href=\"#\">Track Order</a>\n" +
"						<a href=\"#\">Offers &amp; Others</a>\n" +
"					</nav>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div><!-- /container -->\n" +
"		<script src=\"js/jquery.min.js\"></script>\n" +
"		<script src=\"js/jquery.catslider.js\"></script>\n" +
"		<script>\n" +
"			$(function() {\n" +
"\n" +
"				$( '#mi-slider' ).catslider();\n" +
"\n" +
"			});\n" +
"		</script>\n" +
"	</body>\n" +
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
