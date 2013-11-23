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
        String qry;
        HttpSession hs=req.getSession(false);
        if(hs==null)
        {
            res.sendRedirect("Login.html");
        }
        String uname=(String)hs.getAttribute("UserName");
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        ArrayList products=(ArrayList)hs.getAttribute("products");
          double totamt=(Double)hs.getAttribute("TotalAmount");
        int totqty=(Integer)hs.getAttribute("TotalQty");
        
        Connection con=null;
        try{
                   con=DriverConnection.getConnection();
                    
           // PreparedStatement ps=null;//=con.createStatement();
                Statement st=null;
                st=con.createStatement();
                ResultSet rs;//=null;

        
        if(hs==null)
        {
            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
            rd.forward(req,res);
            return;
        }
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
//            p.price=Double.parseDouble()
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
                //out.print("All is Well"+p.pId);
                qry="select p_Name,price from products where p_Id="+p.pId;
                //System.out.print(qry);
                rs = st.executeQuery(qry);
              if(rs.next())
              {
                System.out.print(qry);
                
                p.pName=rs.getString(1);
                p.price=rs.getDouble(2);
                totamt+=p.getQty()*p.getPrice();
                totqty++;
                products.add(p);
                
                hs.setAttribute("TotalAmount",totamt);
                hs.setAttribute("TotalQty",totqty);
              }
            }
            
        
        }
        
       // res.sendRedirect("GetCartDetailsServlet");
        con.close();
        
         out.println("<html><head>");
        out.println("<title>Online Vegetable Market</title>");
        out.println("<head><body style=\"background-image: url(./images/bg1.jpg);\">");
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
        catch(Exception e){
        out.println("Error In Database"+e);
        
                }
    }
}
