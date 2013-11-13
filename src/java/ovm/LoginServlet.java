/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
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
public class LoginServlet extends HttpServlet {

    public void init(){ud=new UserDAO();}
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        String uname=request.getParameter("username");
        String passwd=request.getParameter("passwd");
       /* String uname="m.oubaid";
        String passwd="oubaid";*/
        ud=new UserDAO();
        if(ud.validate(uname,passwd)){
            HttpSession hs=request.getSession();
            hs.setAttribute("UserName", uname);
            RequestDispatcher rd=request.getRequestDispatcher("UserHome");
            rd.forward(request, response);
        }
        else
        {
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");
            rd.forward(request, response);
        }
    }
    UserDAO ud;
}