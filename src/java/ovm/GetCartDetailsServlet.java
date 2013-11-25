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
public class GetCartDetailsServlet extends HttpServlet {
public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException
{
        HttpSession hs=req.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
            rd.forward(req, res);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
          double totamt=(Double)hs.getAttribute("TotalAmount");
          int totqty=(Integer)hs.getAttribute("TotalQty");
        res.setContentType("text/html");
       PrintWriter out=res.getWriter();
        
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
"	<body style=\"background-image: url(./images/bg1.jpg);\">\n" +
"		<div class=\"container\">	\n" +
"			<!-- Codrops top bar -->\n" +
"			<div class=\"codrops-top clearfix\">\n" +
"				<a href=\"CustomerDetails\"><strong>&laquo; Welcome,</strong>"+uname+"</a>\n" +
                    "<a href=\"index\"><strong>Home</strong></a>"+
                                "<a href=\"./GetProductsServlet?cate=Fruit\"><strong>Fruits</strong></a>"+
                                "<a href=\"./GetProductsServlet?cate=Vegetable\"><strong>Vegetable</strong></a>"+
                                "<a href=\"./GetCartDetails\"><strong>Cart[0]</strong></a>"+
                                "<a href=\"./BuyServlet\"><strong>Buy Items</strong></a>"+
"				<span class=\"right\"><a href=\"Logout\"><strong>Logout</strong></a></span>\n" +
"			</div><!--/ Codrops top bar -->\n" +
"			<header class=\"clearfix\">\n" +
"				<h1>Online Vegetable Market <span style=\"color:#0C6\">Buy Fruits and Vegetable Online</span></h1>\n" +
"			</header>\n");
out.println("			<div class=\"main\">");
        
        out.println("<form method='post' action='"+res.encodeURL("BuyServlet")+"'>");
        out.println("<table width='100%' border='1'>");
        out.println("<tr>");
        out.println("<th width='10%'>Check</th>");
        out.println("<th width='20%'>Product Code</th>");
        out.println("<th width='30%'>Product Name</th>");
        out.println("<th width='20%'>Price </th>");
        out.println("<th width='20%'>Requires Qty</th>");
        //out.println("<th width='30%'>Images of Product</th>");
        out.println("</tr>");
        Collection products=null;
        products=(Collection)hs.getAttribute("products");
        if(products==null)
        {
            out.println("<tr><td colspan='5' align='center'>");
            out.println("You have not Added Any Products");
            out.println("</td></tr>");
        }
        else
        {
            Iterator i=products.iterator();
            while(i.hasNext())
            {
                
                    Product p=(Product)i.next();
                    out.println("<tr>");
                    out.println("<td align='center'>");
                    out.println("<input type='checkbox' name='products' value='"+p.getPId()+"'/></td>");
                    out.println("<td>"+p.getPId()+"</td>");
                    out.println("<td>"+p.getPName()+"</td>");
                    out.println("<td>"+p.getPrice()+"</td>");
                    out.println("<td>"+p.getQty()+"</td>");
                   // out.println("<td>"+p.getImage()+"</td>");
                    //res.setContentType("text/jpg");
                    //out.println("<td><img src='./GetImage?p_id="+p.getPId()+"' width='150' height='150'/>");
                    out.println("</td></tr>");
                
                    }
            
        }
        out.println("</table><h3>Total Amount : "+totamt+"</h3><h4>Total Quantity : "+totqty);

           out.println("<br/><center>");
           out.println("<input type='submit' value='Buy Cart'/>");
           out.println("</center></form></td></tr>");
           out.println("<tr align='center'> ");
           out.println("<td colspan='2'>");
           out.println("<em>&copy;Copyright 2013-2014</em></td>");
           out.println("</tr></table></div></div></body></html>");
           out.flush();
           out.close();
}
    
}
