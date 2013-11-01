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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oubaid
 */
public class CustomerDetails extends HttpServlet {
public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
{
    HttpSession hs=req.getSession(false);
    String uname=(String)hs.getAttribute("UserName");
    PrintWriter out=res.getWriter();
     try
        {
            
            Connection con=DriverConnection.getConnection();
          
            Statement st=con.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("select * from customer where email = '"+uname+"'");
            if(rs.next())
            {
             out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<link href=\"css/demo.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"<link href=\"css/form.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"<title>-=[ OVM : Registration ]=-</title>\n" +
"\n" +
"</head>\n" +
"\n" +
"<body>"+
"		<div class=\"container\">	\n" +
"			<!-- Codrops top bar -->\n" +
"			<div class=\"codrops-top clearfix\">\n" +
"				<a href=\"CustomerDetails\"><strong>&laquo; Welcome,  ");
                        
                        out.println(uname+" </strong>"+ "</a>\n" +
"				<span class=\"right\"><a href='"+res.encodeURL("Logout")+"'\"><strong>Logout</strong></a></span>\n" );
            out.println("<header class=\"clearfix\">\n" +
"				<h1>Online Vegetable Market <span style=\"color:#0C6\"><br/>Buy Fruits and Vegetable Online</span></h1>\n" +
"			</header>\n" +
"            <div class=\"main\">\n" +
"            <h1 class=\"formheader\">Customer Registration </h1>\n" +
"            <div class=\"formcontainer\">\n" +
"            	<form class=\"register\" action=\"Register\" method=\"post\">\n" +
"                <table class=\"formdata\" >\n" +
"                <br/>\n" +
"					<tr><label><td>Email ID          </td><td>: "+rs.getString(1)+"</td></label></tr>\n" +
"					\n" +
"					<tr><label><td>Customer Name     </td><td>: "+rs.getString(2)+"</td></label></tr>\n" +
"					<tr><label><td>Contact No.       </td><td>: +91 - "+rs.getString(3)+"</td></label></tr>\n" +
"					\n" +
"					<tr><label><td>Address           </td><td>: <textarea readonly rows=\"3\" cols=\"20\"/>"+rs.getString(4)+"</textarea></td></label></tr>\n" +
"					<tr><label><td>City              </td><td>: "+rs.getString(5)+"</td></label></tr>\n" +
"					<tr><label><td>PINCODE           </td><td>: "+rs.getString(6)+"</td></label></tr>\n" +                                
"					<tr><td><button class=\"login1\" name=\"update\" ><span class=\"login\">Update</span></button></td></tr>\n" +
"				</table>\n" +
"            	</form>\n" +
"                </div>\n" +
"            </div>\n" +
"</div>");
            
            out.println("</body></html>");
            }
          
        }
     
        catch(Exception e)
        {
            out.println("Error"+e.getMessage());
            e.printStackTrace();
        }
           finally
        {
            out.close();
        }
    }
    
}

