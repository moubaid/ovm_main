/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shatabdi
 */
public class ProductDAO 
{
public Collection getProducts()
{
    try
    {
        Connection con=DriverConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from products");
        ArrayList products=new ArrayList();
        
        while(rs.next())
        {
            Product p=new Product();
            p.pId=rs.getInt(1);
            p.pName=rs.getString(2);
            p.price=rs.getDouble(3);
            p.desc=rs.getString(4);
            p.pCategory=rs.getString(5);
            p.qty=rs.getDouble(6);
            p.image = rs.getBlob(7);
            products.add(p);
        }
        return products;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return null;
}
}
