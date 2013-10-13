/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Oubaid
 */
public class DriverConnection {
    public static Connection getConnection()throws Exception
  {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/ovm_main","root","");
      
  }
}
