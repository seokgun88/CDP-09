<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>KNU PLAN</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>OpenAPI 2.0 - 지도 생성</title>
<script type="text/javascript"
	src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=DelRqlwXcLGUB3dteF2y"></script>
</head>

<body>
	<div class="container">
		<a href="home"> <img src="resources/KNUPLAN.png"
			class="img-responsive" alt="Knu Plan">
		</a>

		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">KNU PLAN</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="fullcalendar">일정</a>
				<li><a href="timetable">시간표</a></li>
				<li class="active"><a href="#">빈강의실</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Lgout</a></li>
			</ul>
		</div>
		</nav>

		<div id="testMap"
			style="border: 1px solid #000; width: 500px; height: 400px; margin: 20px;"></div>
		<script type="text/javascript" src="resources/navermap.js"></script>

	</div>
</body>
</html>
