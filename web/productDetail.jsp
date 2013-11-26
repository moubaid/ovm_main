<%-- 
    Document   : productDetail
    Created on : Nov 18, 2013, 9:14:29 AM
    Author     : Oubaid
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="ovm.DriverConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>-=[ OVM : Home ]=-</title>
		<meta name="description" content="Online Vegetable Market: Buy Vegetable and fruits online" />
		<meta name="keywords" content="online, vegetable, fruits, market, buy" />
		<meta name="author" content="Obbu" />
		<link rel="shortcut icon" href="../favicon.ico"> 
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script src="js/modernizr.custom.63321.js"></script>
	</head>
	<body style="background-image: url(./images/bg1.jpg);">
		<div class="container">	
                    <%
                    HttpSession hs=request.getSession(false);
                    String uname=null;
                    int totqty=0;
                    int pid=Integer.parseInt(request.getParameter("P_ID"));
                    if(hs==null)
                        {
                        response.sendRedirect("Login.html");
                      }
                    else
                      {
                        uname=(String)hs.getAttribute("UserName");                    
                        totqty=(Integer)hs.getAttribute("TotalQty");
                        
                                               }
                    %>
			<!-- Codrops top bar -->
			<div class="codrops-top clearfix">
                            <%
                                if(uname==null){
				out.print("<a href=\"register.html\"><strong>&laquo; Click to Register </strong></a>");
                                out.print("<a href=\"index.html\"><strong>Home</strong></a>");
                    			}
                    else
                                               {
                        	out.print("<a href=\"CustomerDetails\"><strong>&laquo; Welcome, "+uname+" </strong></a>");
                                out.print("<a href=\"index\"><strong>Home</strong></a>");
                    }
                                        %>
                                   
                                <a href="./GetProductsServlet?cate=Fruit"><strong>Fruits</strong></a>
                                <a href="./GetProductsServlet?cate=Vegetable"><strong>Vegetable</strong></a>
                                <a href="./GetCartDetailsServlet"><strong>Cart[ <%=totqty%> ]</strong></a>
                                <a href="./BuyServlet"><strong>Buy Items</strong></a>
			</div><!--/ Codrops top bar -->
			<header class="clearfix" style='background-image: url(./images/hd1.jpg);background-color: whitesmoke;background-position: left ;'>
				<h1>Online Vegetable Market <span style="color:#0C6">Buy Fruits and Vegetable Online</span></h1>
			</header>
			<div class="main">
                            <%
                            
                            Connection con=DriverConnection.getConnection();
                            Statement st=con.createStatement();
                            ResultSet rs=st.executeQuery("SELECT * FROM  products WHERE  P_ID = "+pid);
         if(rs.next())
        {
             
         
                            %>
                            <div>
                                <table border="0" cellspacing="20px">
                                    <tr>
                                        <td rowspan="5">
                                            <% out.print("<img src='./GetImage?p_id="+rs.getInt(1)+"' alt='img' width='300px' height='300px' />");
                                            %>
                                        </td>
                                        <td cellpadding="20px">
                                            Product ID: <%= rs.getInt(1)%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px">Name : <%= rs.getString(2) %></td>    
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px"> Price : <%= rs.getString(3) %> </td>    
                                    </tr>
                                     <tr>
                                        <td cellpadding="30px">Category: <%= rs.getString(5) %></td>    
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px">Description : <%= rs.getString(4) %></td>    
                                    </tr>
                                    <%}%>
                                </table>
                            </div>
			</div>
		</div><!-- /container -->
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.catslider.js"></script>
		<script>
			$(function() {

				$( '#mi-slider' ).catslider();

			});
		</script>
	</body>
</html>