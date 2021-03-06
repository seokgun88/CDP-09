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
					<th width="45"><center>시간</center></th>
					<th><center>월</center></th>
					<th><center>화</center></th>
					<th><center>수</center></th>
					<th><center>목</center></th>
					<th><center>금</center></th>
					<th><center>토</center></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="schedule">
					<tr>
						<td>${schedule.time}</td>
						<td>${schedule.monday}</td>
						<td>${schedule.tuesday}</td>
						<td>${schedule.wednesday}</td>
						<td>${schedule.thirsday}</td>
						<td>${schedule.friday}</td>
						<td>${schedule.saturday}</td>
					</tr>
				</c:forEach>
				<%--<tr>
					<td><center>
							1A<br>(09:00~09:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							1B<br>(09:30~10:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							2A<br>(10:00~10:30)
						</center></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><center>
							2B<br>(10:30~11:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							3A<br>(11:00~11:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							3B<br>(11:30~12:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							4A<br>(12:00~12:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							4B<br>(12:30~13:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							5A<br>(13:00~13:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							5B<br>(13:30~14:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							6A<br>(14:00~14:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							6B<br>(14:30~15:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							7A<br>(15:00~15:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							7B<br>(15:30~16:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							8A<br>(16:00~16:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							8B<br>(16:30~17:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							9A<br>(17:00~17:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							9B<br>(17:30~18:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							10A<br>(18:00~18:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							10B<br>(18:30~19:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							11A<br>(19:00~19:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							11B<br>(19:30~20:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							12A<br>(20:00~20:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							12B<br>(20:30~21:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							13A<br>(21:00~21:30)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr>
				<tr>
					<td><center>
							13B<br>(21:30~22:00)
						</center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
					<td><center></center></td>
				</tr> --%>
			</tbody>
		</table>
	</div>
</body>
</html>