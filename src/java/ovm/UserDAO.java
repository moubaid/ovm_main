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
    public boolean validate(String uname,String passwd,String usertype)
    {
        try
        {
            if((usertype.equals("Admin"))&&(passwd.equalsIgnoreCase("Admin"))&&uname.equals("Admin")){
                return true;
            }
            Connection con=DriverConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("select * from "+usertype+" where email = '"+uname+"' and passwd='"+passwd+"'");
            return rs.next();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
