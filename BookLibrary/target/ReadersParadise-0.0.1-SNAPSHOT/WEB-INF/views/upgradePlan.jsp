<%@page import="org.hibernate.Session"%>
<%@page import="javax.print.attribute.standard.Severity"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import=" static in.co.companyname.constants.Constants.*"%>
<%
    HttpSession session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("loginform.html");

    }

    final Logger LOGGER = LoggerFactory.getLogger("userName.jsp");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Readers Paradise</title>

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


<style>
.loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url('WebContent/images/page-loader.gif') 50% 50% no-repeat
		rgb(249, 249, 249);
}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	})
</script>




<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/plug-ins/725b2a2115b/integration/bootstrap/3/dataTables.bootstrap.css">

<!-- <script type="text/javascript" language="javascript" -->
<!-- 	src="http://code.jquery.com/jquery-1.10.2.min.js"></script> -->
<script src="<c:url value="/resources/mytheme/js/jquery-1.11.0.js"/>"></script>
<script type="text/javascript" language="javascript"
	src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"
	src="http://cdn.datatables.net/plug-ins/725b2a2115b/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable( {
            language: {
                searchPlaceholder: "Search within table "
            }
        } );
	});
</script>



</head>

<body>
	<div class="loader"></div>
	<!-- Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Confirm Address</h4>
				</div>
				<div class="modal-body">
					<c:if test="${not empty shelfresults}">

						<form action="deleteFromShelf" method="POST">
							<input type="hidden" name="bid" id="bookid" />
							<textarea name="address">
								<%
								    out.print((String) session.getAttribute("address"));
								%>
							</textarea>
							<input type="submit" data-toggle="modal" data-target="#regModal"
								class="btn btn-success" value="Request" id="button"
								name="button" />
						</form>
					</c:if>
					<c:if test="${not empty showHoldingBooks}">

						<form action="returnBookRequest" method="GET">
							<input type="hidden" name="requestId" id="requestid" />
							<textarea name="address">
								<%
								    out.print((String) session.getAttribute("address"));
								%>
							</textarea>
							<input type="submit" data-toggle="modal" data-target="#regModal"
								class="btn btn-success" value="Return Book" id="button"
								name="button" />
						</form>
					</c:if>
				</div>

				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


				</div>



			</div>
		</div>
	</div>

	<!-- Modal -->

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
				<a class="navbar-brand" href="#"> Welcome to Readers Paradise</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="j_spring_security_logout">Logout</a></li>



					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <B> <%
     out.println("+" + session.getAttribute("firstname"));
 %>
						</B> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="user_ViewProfile.html">View Profile</a></li>
							<li><a href="user_upgradeplan.htm">Upgrade Plan</a></li>


						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>


	<!-- Page Content -->
	<div class="container">

		<!-- 	<button onclick="func();">test</button> -->

		<!-- Page Heading/Breadcrumbs -->
		<div class="row clearfix">

			<div class="col-md-4 column">
				<div class="page-header">
					<h1>
						Hi,
						<%
					    out.println(session.getAttribute("firstname"));
					%>


					</h1>
				</div>
			</div>
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<form name="searchform" method="get" action="BookSearchHelper">

					<p>&nbsp;</p>
					<p>
						<input size="40" type="text" required="required"
							placeholder="Search by Category, Author or Book Title "
							name="searchCriteria"> <br>
					</p>
					<button type="submit" class="btn  btn-info">Search Books</button>
				</form>


			</div>
			<div class="col-lg-12">
				<ol class="nav nav-tabs">

					<%-- 					<c:if test="${not empty showSearchedBooks}"> --%>
					<li><a href="#">My Home</a></li>
					<li><a href="user_showBookShelf.html">My BookShelf</a></li>
					<li><a href="user_showRequestedBooks.html">Requested Books</a></li>
					<li><a href="user_showHoldingBooks.html">Books Holding</a></li>
					<li><a href="user_ViewHistory">Books Returned(History)</a></li>
					<li><a href="user_Recommendations.html">Books Recommended
							for You</a></li>
					<li><a href="user_SubscriptionHistory">Subscriptions
							History</a></li>



				</ol>

			</div>
		</div>
		<!-- /.row -->

		<!-- Content Row -->
		<div class="row">

			<div class="col-lg-12">
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>
					<c:if test="${!empty upgradeSuccess}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert"> &times; </a> <font
								style="color: green;"><B> ${upgradeSuccess} </B></font>
						</div>
					</c:if>
					<c:if test="${!empty upgradeFailure}">
						<div class="alert alert-danger">
							<a href="#" class="close" data-dismiss="alert"> &times; </a> <font
								style="color: red;"> <B> ${upgradeFailure}</B></font>
						</div>
					</c:if>
				</p>


				<c:if test="${!empty plansList}">
					<table id="example" class="table table-striped" cellspacing="0"
						width="100% 
			cellspacing="0">
						<caption>
							<h2>Subscriptions Plans</h2>
						</caption>
						<thead>
							<tr class="danger">



								<th>PlanName</th>
								<th>Maximum Books</th>
								<th>Price</th>
								<th>Maximum Days</th>
								<th>Subscribe</th>
								<th></th>


							</tr>
						</thead>
						<tbody>
							<c:forEach items="${plansList}" var="plans">
								<tr class="success">

									<td><c:out value="${plans.planName}" /></td>
									<td><c:out value="${plans.maxBooks}" /></td>
									<td><c:out value="${plans.price}" /></td>
									<td><c:out value="${plans.maxDays}" /></td>
									<form action="user_enrolNewPlan" method="POST">
										<td><input type="submit" value="Subscribe"
											class="btn btn-primary btn-sm"></td>
										<td><input type="hidden" name="planId"
											value="${plans.planId}"></td>
									</form>

								</tr>

							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; companyname 2000-2014</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery Version 1.11.0 -->
	<%-- 	<script src="<c:url value="/resources/mytheme/js/jquery-1.11.0.js"/>"></script> --%>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/mytheme/js/bootstrap.min.js"/>"></script>



	<script>
		function func(id) {
			//alert(id);
			$('#loginModal').modal('toggle');
			$('#bookid').val(id);
			$('#requestid').val(id);

		};
	</script>

</body>

</html>
