/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oubaid
 */
public class Logout extends HttpServlet {
public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException
{
    HttpSession hs=req.getSession(false);
    
    if(hs!=null)
            hs.invalidate();
    
    RequestDispatcher rd=req.getRequestDispatcher("Login.html");
    rd.forward(req,res);
}
}
