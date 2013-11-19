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
public class GetProductsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException
    {
        productsdao=new ProductDAO();
        
    }
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)
            throws ServletException, IOException
    {
        HttpSession hs=req.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
            rd.forward(req, res);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
        res.setContentType("text/html");
       PrintWriter out=res.getWriter();
        
        out.println("<html><head>");
        out.println("<title>Online Vegetable Market</title>");
        out.println("<head><body>");
        out.println("<table width='100%' height='90%' border='1px'>");
        out.println("<tr align='center'> "
                + "<td height='39%' colspan='2'><strong><font size='5'>Online Vegetable Market</font></strong></td></tr>");
        out.println("<tr> <td width='18%' height='500px' valign='top'><p>&nbsp;</p>");out.println("<blockquote><p><a href='"+res.encodeURL("index")+"'>Home</a></p><p>");
        out.println("<a href='"+res.encodeURL("GetProductsServlet")+"'>Vegetables</a></p><p>");
        out.println("<a href='"+res.encodeURL("GetProductsServlet")+"'>Fruits</a></p><p>");
        out.println("<a href='"+res.encodeURL("GetCartDetailsServlet")+"'>Cart Details</a></p><p>");
        out.println("<a href='"+res.encodeURL("BuyServlet")+"'>Buy Cart</a></p><p>");
        out.println("<a href='"+res.encodeURL("Logout")+"'>Logout</a></p></blockquote></td>");
        out.println("<td width='82%' align='left' valign='top'><p>");
        out.println("<font size='6'>Welcome, "+uname+"</font></p>");
        out.println("<form method='post' action='"+res.encodeURL("AddProductServlet")+"'>");
        out.println("<table width='100%' border='1'>");
        out.println("<tr>");
        out.println("<th width='5%'>Check</th>");
        out.println("<th width='10%'>Product Code</th>");
        out.println("<th width='30%'>Product Name</th>");
        out.println("<th width='10%'>Price</th>");
        out.println("<th width='15%'>Requires Qty</th>");
        out.println("<th width='30%'>Images of Product</th>");
        out.println("</tr>");
        Collection products=productsdao.getProducts("Vegetable");
        if(products==null)
        {
            out.println("<tr><td colspan='5' align='center'>");
            out.println("No Products Available");
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
                    out.println("<td><input type='text' name='"+p.getPId()+"'/></td>");
                   // out.println("<td>"+p.getImage()+"</td>");
                    //res.setContentType("text/jpg");
                    out.println("<td><img src='./GetImage?p_id="+p.getPId()+"' width='150' height='150'/>");
                    out.println("</td></tr>");
                
                    }
        }
            out.println("</table>");
           out.println("<br/><center>");
           out.println("<input type='submit' value='Add Products to Cart'/>");
           out.println("</center></form></td></tr>");
           out.println("<tr align='center'> ");
           out.println("<td colspan='2'>");
           out.println("<em>&copy;Copyright 2013-2014</em></td>");
           out.println("</tr></table></body></html>");
           out.flush();
           out.close();
        
        
    }
   ProductDAO productsdao;
}
