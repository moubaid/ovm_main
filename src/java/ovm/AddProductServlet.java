/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Oubaid
 */
public class AddProductServlet extends HttpServlet {

    public void doPost(HttpServletRequest req,HttpServletResponse res) 
            throws ServletException, IOException
    {
        HttpSession hs=req.getSession(false);
        if(hs==null)
        {
            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
            rd.forward(req,res);
            return;
        }
        String uname=(String)hs.getAttribute("UserName");
        ArrayList products=(ArrayList)hs.getAttribute("products");
        if(products==null)
        {
            products=new ArrayList();
            hs.setAttribute("products",products);
            
        }
        String []pcodes=req.getParameterValues("products");
        for(int i=0;i<pcodes.length; i++)
        {
            if(req.getParameter(pcodes[i]).equals(""))
            {
                continue;
            }
            Product p=new Product();
            p.pId=Integer.parseInt(pcodes[i]);
            int j=products.indexOf(p);
            if(j!=-1)
            {
                p=(Product)products.get(j);
                p.qty+=Double.parseDouble(req.getParameter(pcodes[i]));
            }
            else
            {
                p.pName=req.getParameter(pcodes[i]+"Name");
                p.qty=Double.parseDouble(req.getParameter(pcodes[i]));
                products.add(p);
                
            }
            
        }
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<html><head><title>-- { OVM : Add Products } --</title></head>");
        out.println("<body>");
        out.close();
        
    }
}
