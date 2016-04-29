<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Readers Pasadise</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value="/resources/mytheme/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<c:url value="/resources/mytheme/css/modern-business.css" />"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value="/resources/mytheme/font-awesome-4.1.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Welcome to Readers
					Paradise</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li></li>
					<li></li>
					<li></li>
					<li class="dropdown">

						<ul class="dropdown-menu">
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</li>
					<li class="dropdown">

						<ul class="dropdown-menu">
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</li>
					<li class="dropdown">

						<ul class="dropdown-menu">
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</li>


					<li><a href="loginform.html"><b>Login</b></a></li>

					<li><a href="registrationForm.html"><b>Register </b></a></li>
				</ul>

			</div>

			<!-- /.navbar-collapse -->

		</div>
		<!-- /.container -->
	</nav>

	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill" style="background-image: url(images/slider_1.jpg)"></div>
				<div class="carousel-caption">
					
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url(images/slider_2.jpg);"></div>
				<div class="carousel-caption">
					
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url(images/slider_3.jpg);"></div>
				<div class="carousel-caption">
					
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>

	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Welcome to Readers Paradise</h1>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-check"></i> Check Out Our Collection Here
						</h4>
					</div>
					<div class="panel-body">
						<form name="searchform" method="get" action="AnonymousSearch">

							<p>&nbsp;</p>
							<p>
								<input size="40" type="text" required="required"
									placeholder="Search by Category, Author or Book Title "
									name="searchCriteria"> <br>
							</p>
							<button type="submit" class="btn  btn-info">Search Books</button>
						</form>

					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-gift"></i> Easy &amp; Fast Access to Books
						</h4>
					</div>
					<div class="panel-body">
						<p>You can easily search your favourite books and put a
							request for them. The Books will be delivered to your door step
							in a short time.</p>
						<p>
						<hr>
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-compass"></i> Biggest Collection of Books
						</h4>
					</div>
					<div class="panel-body">
						<p>We are having a huge collection of the popular books. Our
							colllection of books is greater than any other online shop in
							companyname freshers batch.</p>
						<p>
						<hr>
						</p>

					</div>
				</div>
			</div>
		</div>


		<!-- Features Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">What we offer</h2>
			</div>
			<div class="col-md-6">
				<p>The Readers Paradise e-Library provides you with the
					following functionalities:</p>
				<ul>
					<li style="margin-bottom: 15px;"><strong>Search
							Latest Collection of Books</strong></li>

					<li style="margin-bottom: 15px;">Free pickup and delivery at
						your doorstep.</li>
					<li style="margin-bottom: 15px;">No due dates or late fees.</li>
					<li style="margin-bottom: 15px;">Service available pan India.</li>
					<li style="margin-bottom: 15px;">Multiple membership plans to
						suit your reading requirements.</li>
					<li style="margin-bottom: 15px;">Over 6000+ titles to choose
						from.</li>


				</ul>
				<ul style="margin-bottom: 15px;">
					<img class="img-responsive" src="images/home_functionalities.jpg"
						alt="">
				</ul>
				<p>The Readers Paradise website is developed and managed by Yash
					Khatri. You can contact Yash.Khatri@companyname.co.in for any further
					enquiry.</p>
			</div>
			<div class="col-md-6">
				<img class="img-responsive" src="images/love-books-1.jpg" alt="">
			</div>

		</div>
		<!-- /.row -->

		<hr>

		<!-- Call to Action Section -->
		<div class="well">
			<div class="row">
				<div class="col-md-8">
					<p>
						You are Visitor number: <B> <%
Integer hitsCount = 
      (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* First visit */
       System.out.println("Welcome to my website!");
       hitsCount = 100;
    }else{
       /* return visit */
         System.out.println("Welcome back to my website!");
       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
out.println(hitsCount);
    %></B>
					</p>
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="col-md-4">
				<a class="btn btn-block btn-social btn-twitter"
					href="http://www.twitter.com"> <i class="fa fa-twitter"></i>
					Follow us on Twitter
				</a>
			</div>
			<div class="col-md-4">
				<a class="btn btn-block btn-social btn-facebook"
					href="http://www.facebook.com"> <i class="fa fa-facebook"></i>
					Follow us on Facebook
				</a>
			</div>
			<div class="col-md-4">
				<a class="btn btn-block btn-social btn-linkedin"
					href="https://in.linkedin.com/in/yashkhatri"> <i class="fa fa-linkedin"></i>
					Follow us on Linkedin
				</a>
			</div>

		</div>

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; @companyname 2000-2014</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->
	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/mytheme/js/jquery-1.11.0.js"/>"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/mytheme/js/bootstrap.min.js"/>"></script>

	<!-- Script to Activate the Carousel -->
	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>



</body>

</html>
