/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
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
public class index extends HttpServlet {

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
    ProductDAO productsdao;
    @Override
     
    public void init() throws ServletException
    {
        productsdao=new ProductDAO();
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");
            rd.forward(request, response);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                Collection veg=productsdao.getProducts("Vegetable");
                Collection fruit=productsdao.getProducts("Fruit");
                if(veg==null||fruit==null){
                    out.println("<tr><td colspan='5' align='center'>");
                    out.println("No Products Available");
                    out.println("</td></tr>");
                }
                else{
                    Iterator i=veg.iterator();
                    Iterator j=fruit.iterator();
                    
                    
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
"				<a href=\"CustomerDetails\"><strong>&laquo; Welcome,</strong>"+uname+"</a>\n" +
"				<span class=\"right\"><a href=\"Logout\"><strong>Logout</strong></a></span>\n" +
"			</div><!--/ Codrops top bar -->\n" +
"			<header class=\"clearfix\">\n" +
"				<h1>Online Vegetable Market <span style=\"color:#0C6\">Buy Fruits and Vegetable Online</span></h1>\n" +
"			</header>\n<div class='menu'>" +
"			<div class=\"main\">\n" +
"				<div id=\"mi-slider\" class=\"mi-slider\">\n" +
"					<ul>\n" );
            int pp=0;
                    while(pp<8){
                    Product v=(Product)i.next();
                    pp++;
out.print("<li><a href=\"#\"><img src='./GetImage?p_id="+v.getPId()+"' width='100px' height='100px' alt='Image'><h6>"+v.getPName()+"</h6></a>MRP :"+v.getPrice()+" QTY : <input type=\"text\" name='"+v.getPId()+"' value=\"0.0KG\" size=\"3\"/> <br/>Add To Cart<input type=\"checkbox\" name='products' value='"+v.getPId()+"'/> </li>\n" );
        }
out.print("					</ul>\n" +
"					<ul>\n" );
    pp=0;
        while(pp<8){
                Product f=(Product)j.next();
                pp++;
out.print("<li><a href=\"#\"><img src='./GetImage?p_id="+f.getPId()+"' width='100px' height='100px' alt='Image'><h6>"+f.getPName()+"</h6></a>MRP : "+f.getPrice()+" QTY : <input type=\"text\" name='"+f.getPId()+"' value=\"0.0KG\" size=\"3\"/><br/>Add To Cart<input type=\"checkbox\" name='products' value='"+f.getPId()+"'/> </li>\n");
        }
out.print("					</ul>\n" +
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
