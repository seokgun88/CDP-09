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
<style type="text/css">
@media (max-width: 500px) {
	.table-condensed>thead>tr>th, .table-condensed>tbody>tr>th, .table-condensed>tfoot>tr>th, .table-condensed>thead>tr>td, .table-condensed>tbody>tr>td, .table-condensed>tfoot>tr>td{
	    padding: 1px;
		font-size: 5px;
		min-width:5px;
	}
}
</style>
<title>시간표</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><center></center></th>
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
						<td><center>${schedule.time}</center></td>
						<td><center>${schedule.monday}</center></td>
						<td><center>${schedule.tuesday}</center></td>
						<td><center>${schedule.wednesday}</center></td>
						<td><center>${schedule.thirsday}</center></td>
						<td><center>${schedule.friday}</center></td>
						<td><center>${schedule.saturday}</center></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>