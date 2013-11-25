<%-- 
    Document   : productDetail
    Created on : Nov 18, 2013, 9:14:29 AM
    Author     : Oubaid
--%>

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
			<!-- Codrops top bar -->
			<div class="codrops-top clearfix">
				<a href="register.html"><strong>&laquo; Click to Register </strong></a>
				
                                   <a href="index.html"><strong>Home</strong></a>
                                <a href="./GetProductsServlet?cate=Fruit"><strong>Fruits</strong></a>
                                <a href="./GetProductsServlet?cate=Vegetable"><strong>Vegetable</strong></a>
                                <a href="./GetCartDetailsServlet"><strong>Cart[0]</strong></a>
                                <a href="./BuyServlet"><strong>Buy Items</strong></a>
			</div><!--/ Codrops top bar -->
			<header class="clearfix" style='background-image: url(./images/hd1.jpg);background-color: whitesmoke;background-position: left ;'>
				<h1>Online Vegetable Market <span style="color:#0C6">Buy Fruits and Vegetable Online</span></h1>
			</header>
			<div class="main">
                            <div>
                                <table border="0" cellspacing="20px">
                                    <tr>
                                        <td rowspan="5">
                                            <img src="./images/4.jpg" alt="img" width="300px" height="300px"/>
                                        </td>
                                        <td cellpadding="20px">
                                            Product ID: 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px">Name : </td>    
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px"> Price : </td>    
                                    </tr>
                                     <tr>
                                        <td cellpadding="30px">Category: </td>    
                                    </tr>
                                    <tr>
                                        <td cellpadding="30px">Description : </td>    
                                    </tr>
                                    
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