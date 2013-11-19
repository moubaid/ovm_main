/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oubaid
 */
public class GetImage extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) 
  throws IOException,ServletException {
    

        Blob image = null;
        String name="p_id";
        ResultSet rs = null;
        int value=Integer.parseInt(request.getParameter(name));
        ServletOutputStream out = response.getOutputStream();
        Connection con=null;
        Statement st=null;
        try
        {
            con=DriverConnection.getConnection();
            st=con.createStatement();
            rs = st.executeQuery("select img from products where p_id="+value);
        if (rs.next()) 
        {
            image = rs.getBlob(1);
        } 
        else
        {
            response.setContentType("text/html");

            out.println("<font color='red'>image not found for given id</font>");

        return;
        }
        response.setContentType("image/jpg");
        InputStream in = image.getBinaryStream();
        int length = (int) image.length();
        int bufferSize = 1024;
           byte[] buffer = new byte[bufferSize];
        while ((length = in.read(buffer)) != -1) 
        {
            out.write(buffer, 0, length);
        }
        }
        catch(Exception e){}
        finally 
      {
            try {
            rs.close();
            st.close();
            con.close();
            }
            catch(Exception e){}
  
      }
    }
}