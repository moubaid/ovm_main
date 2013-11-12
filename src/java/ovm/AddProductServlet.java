/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Oubaid
 */
public class AddProductServlet extends HttpServlet {

    public void doPost(HttpServletRequest req,HttpServletResponse res) 
            throws ServletException, IOException
    {
        HttpSession hs=req.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
            rd.forward(req,res);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
        ArrayList products=(ArrayList)hs.getAttribute("products");
        if(products==null)
        {
            products=new ArrayList();
            hs.setAttribute("products",products);
            
        }
        String []pcodes=req.getParameterValues("products");
        for(int i=0;i<pcodes.length; i++)
        {
            if(req.getParameter(pcodes[i]).equals(""))
            {
                continue;
            }
            Product p=new Product();
            p.pId=Integer.parseInt(pcodes[i]);
            int j=products.indexOf(p);
            if(j!=-1)
            {
                p=(Product)products.get(j);
                p.qty+=Double.parseDouble(req.getParameter(pcodes[i]));
            }
            else
            {
                p.pName=req.getParameter(pcodes[i]+"pName");
                p.qty=Double.parseDouble(req.getParameter(pcodes[i]));
                products.add(p);
                
            }
            
        }
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
         out.println("<html><head>");
        out.println("<title>Online Vegetable Market</title>");
        out.println("<head><body>");
        out.println("<table width='100%' height='90%' border='1px'>");
        out.println("<tr align='center'> "
                + "<td height='39%' colspan='2'><strong><font size='5'>Online Vegetable Market</font></strong></td></tr>");
        out.println("<tr> <td width='18%' height='500px' valign='top'><p>&nbsp;</p>");
        out.println("<blockquote><p><a href='"+res.encodeURL("GetCartDetailsServlet")+"'>View Products</a></p><p>");
        out.println("<a href='"+res.encodeURL("GetCartDetailsServlet")+"'>View Cart Details</a></p><p>");
        out.println("<a href='"+res.encodeURL("Logout")+"'>Logout</a></p></blockquote></td>");
        out.println("<td width='82%' align='left' valign='top'><p>");
        out.println("<font size='6'>Welcome, "+uname+"</font></p>");
        out.println("</td><h1>Product Added Successfully</h1>");
        
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
}
