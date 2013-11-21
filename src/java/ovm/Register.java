/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
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
    @Override
            public void init() throws ServletException
            {
                System.out.println("In Init");
                try
                {

                    String sqlstmt = "INSERT INTO customer"
			+ " VALUES "
			+ "(?,?,?,?,?,?,?,?)";
            con=DriverConnection.getConnection();
            ps=con.prepareStatement(sqlstmt);
                        
                }
                catch(Exception e)
                {
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
                               // String uname=req.getParameter("username");
                                String uname=req.getParameter("email");
                                String contactno=req.getParameter("contactno");
                                String pass=req.getParameter("passwd");
                                String repass=req.getParameter("repasswd");
                                int count=0;
                                if(uname==null||uname.equals("")||pass==null||pass.equals("")
                                        
                                        ||!(pass.equals(repass))||contactno==null||contactno.equals(""))
                                        {        
                                               out.println("<html><body><center>");
                                               out.println("<li><i>Given Details are not valid to register</i></li><br/>");
                                               out.println("</center></body></html>");
                                               return;
                                        }
                                
                                 st=con.createStatement();
                                
            
                            rs = st.executeQuery("select * from customer where email = '"+uname+"'");
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
                                //String dob=req.getParameter("dob");
                                String addr=req.getParameter("address");
                                String area=req.getParameter("area");
                                String city=req.getParameter("city");
                                String pincode=req.getParameter("pincode");
                                ps.setString(1, uname);
                                
                                ps.setString(2, cname);
                                ps.setString(3, contactno);
                                ps.setString(4,addr);
                                ps.setString(5,area);
                                ps.setString(6, city);
                                ps.setString(7, pincode);
                                ps.setString(8,pass);
                                
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
