<%-- 
    Document   : feedback
    Created on : Nov 21, 2013, 8:32:57 AM
    Author     : Oubaid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <link rel="stylesheet" type="text/css" href="css/form.css" />
                <link rel="stylesheet" type="text/css" href="css/demo.css" />
		<script src="js/modernizr.custom.63321.js"></script>
	</head>
	<body style="background-image: url(./images/bg1.jpg);">
		<div class="container">	
                    <%
                    HttpSession hs=request.getSession(false);
                    if(hs==null)
                        {
                        response.sendRedirect("Login.html");
                                               }
                    String uname=(String)hs.getAttribute("UserName");
                    int totqty=(Integer)hs.getAttribute("TotalQty");
                    %>
			<!-- Codrops top bar -->
			<div class="codrops-top clearfix">
                            <a href="register.html"><strong>Welcome, <%=uname%></strong></a>
				<a href="index.html"><strong>Home</strong></a>
                                <a href="./GetProductsServlet?cate='Fruit'"><strong>Fruits</strong></a>
                                <a href="./GetProductsServlet?cate='Vegetable'"><strong>Vegetable</strong></a>
                                <a href="./GetCartDetailsServlet"><strong>Cart[<%=totqty %>]</strong></a>
                                <a href="./BuyServlet"><strong>Buy Items</strong></a>
			</div><!--/ Codrops top bar -->
			<header class="clearfix" style='background-image: url(./images/hd1.jpg);background-color: whitesmoke;background-position: left ;' >
				<h1>Online Vegetable Market <span style="color:#0C6">Buy Fruits and Vegetable Online</span></h1>
			</header>
			<div class="main">
                            <div class="errorpage">
                            <h3 align="center">Feedback</h3>
                            <form name="form1" action="AddFeedback" method="post">
                            <br/>
                        <table border="0" class="formdata">
                	<label><tr><td>Subject </td><td> : <input class="forminput" type="text" name="subject" size="20"/></label>
                    <br />
                    <label><tr><td>Feedback </td><td> : <textarea class="forminput" name="text" cols="36" rows="3">Type Here</textarea> </label>
                    
           </table>
                    <button class="login1" name="login" ><span class="login">Submit</span></button>
            </form>
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