/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 *
 * @author Oubaid
 */
public class Register extends GenericServlet {
    
            Connection con;//=DriverConnection.getConnection();
            PreparedStatement ps=null;//=con.createStatement();
            Statement st=null;
            ResultSet rs=null;
            public void init() throws ServletException
            {
                System.out.println("In Init");
                try
                {
/*                    ServletContext ctxt=getServletContext();
                    String driverClassName=ctxt.getInitParameter("driverClassName");
                    Class.forName(driverClassName);
                    
                    String url=ctxt.getInitParameter("url");
                    String dbuser=getInitParameter("dbuser");
                    String dbpass=getInitParameter("dbpass");
                    String sqlst=getInitParameter("sqlstatement");
                    con=DriverManager.getConnection(url,dbuser,dbpass);
                    
                    ps=con.prepareStatement(sqlst);
                    * */
                    String sqlstmt = "INSERT INTO customer"
			+ " VALUES "
			+ "(?,?,?,?,?,?,?,?)";
            con=DriverConnection.getConnection();
            ps=con.prepareStatement(sqlstmt);
                        
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    throw new ServletException("Initalization failed, Unable to get DB Connection");
                }
            }
            public void service(ServletRequest req,ServletResponse res)
                    throws ServletException, IOException
            {
                    System.out.println("In Service");
                    res.setContentType("text/html");
                    PrintWriter out=res.getWriter();
                    try
                    {
                                String uname=req.getParameter("username");
                                String email=req.getParameter("email");
                                String contactno=req.getParameter("contactno");
                                String pass=req.getParameter("passwd");
                                String repass=req.getParameter("repasswd");
                                int count=0;
                                if(uname==null||uname.equals("")||pass==null||pass.equals("")
                                        
                                        ||!(pass.equals(repass))||email==null||email.equals("")||contactno==null||contactno.equals(""))
                                        {        
                                               out.println("<html><body><center>");
                                               out.println("<li><i>Given Details are not valid to register</i></li><br/>");
                                               out.println("</center></body></html>");
                                               return;
                                        }
                                
                                 st=con.createStatement();
                                
            
                            rs = st.executeQuery("select * from customer where username = '"+uname+"' or email='"+email+"'");
                        if(rs.next())
                        {
                                               //out.println("<html><body><center>");
                                    out.println("<i style='text-align:center;color:red;'>Given Username and Email Id Already exists</i>");
                                    RequestDispatcher rd=req.getRequestDispatcher("register.html");
                                    rd.include(req, res);
                                            //(req, res);
                                    
                                    //           out.println("</center></body></html>");   
                                               return;
                        }
                                
                                    String cname=req.getParameter("cname");
                                
                                //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                //Date dob = new Date((req.getParameter("dob");
                                String dob=req.getParameter("dob");
                                String addr=req.getParameter("address");
                                String city=req.getParameter("city");
                                long pincode=Long.parseLong(req.getParameter("pincode"));
                                ps.setString(1, email);
                                ps.setString(2, uname);
                                ps.setString(3, pass);
                                ps.setString(4, dob);
                                ps.setString(5,addr);
                                ps.setString(6, city);
                                ps.setString(7, contactno);
                                ps.setLong(8,pincode);
                                count=ps.executeUpdate();
                                
                                if(count==1||count==Statement.SUCCESS_NO_INFO)
                                {
                                    out.println("<html><body><center>");
                                    out.println("Registered Successfully<br/>");
                                    out.println("<li><i>You are Welcome</i></li>");
                                    out.println("</center></body></html>");
                                }
                                
                     
                                else
                                {
                                    out.println("<html><body><center>");
                                    out.println("Given details are incorrect<br/>");
                                    out.println("<li><i>Please try again later</i></li>");
                                    out.println("</center></body></html>");
                                }
                            }
                    
                             catch(Exception e)       
                             {
                                 out.println("<html><body><center>");
                                    out.println("<h1>Unable to the process the request try after some time<br/>");
                                    out.println("<li><i>Please try again later</i></li>");
                                    out.println("</center></body></html>");
                             }
                
                    }
            public void destroy()
            {
                System.out.println("In Destroy");
                try
                {
                    con.close();
                    
                }
                catch(Exception e)
                {
                    
                }
            }
}
