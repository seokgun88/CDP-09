<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>시간표</title>
</head>
<body>
	<div class="container">
		<a href="home"><img src="resources/KNUPLAN.png"
			class="img-responsive" alt="Knu Plan"> </a>

		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">KNU PLAN</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="fullcalendar">일정</a>
					<li class="active"><a href="#">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> Lgout</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					
					<th width="45"><center></center></th>
					<c:forEach items="${buildinglist}" var="buildinglist">
						<th><center>${buildinglist}</center></th>					
					</c:forEach>
				</tr>
			</thead>
			<tbody>				
					<tr>
						<c:forEach items="${breakfastlist}" var="breakfastlist">
							<td>${breakfastlist}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${lunchlist}" var="lunchlist">
							<td>${lunchlist}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${dinnerlist}" var="dinnerlist">
							<td>${dinnerlist}</td>
						</c:forEach>
					</tr>
				
			</tbody>
		</table>
	</div>
</body>
</html>