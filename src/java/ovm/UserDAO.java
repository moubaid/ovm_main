/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Oubaid
 */
public class UserDAO {
    public boolean validate(String uname,String passwd)
    {
        try
        {
            
            Connection con=DriverConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("select * from customer where email = '"+uname+"' and passwd='"+passwd+"'");
            return rs.next();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
