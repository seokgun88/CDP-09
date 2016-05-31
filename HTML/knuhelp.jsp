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
			<tbody>
                <tr>
					<th><center><font size=5>1. 일정 페이지</font></center></th>
				</tr>
				<tr>
                    <td><center><font size=4>1.1 한 달</font></center></td>
				</tr>
				<tr>
					<td>
                        1.1.1 날짜를 클릭하면 해당 날짜의 일정이 표시된다.<br>
                        1.1.2 사용자가 일정을 추가하려면 한 주 혹은 하루의 일정 페이지로 들어가야한다.<br>
                        1.1.3 한 달 스케줄을 보여주는 페이지에서 하루 일정을 추가하는 것은 불가능하지만 추가한 일정을 확인은 가능하다.<br>
                        1.1.4 추가한 일정을 클릭하면 삭제할 수 있다.<br>
                        1.1.5 드래그로 여러 날짜 일정 추가는 가능하다.<br>
                        1.1.6 개인일정 기간 변경 가능하다.<br>
                        1.1.7 좌측상단의 화살표 버튼을 누르는 것으로 달을 바꿀 수 있다.<br>
                        1.1.8 달을 바꿨을 때 today버튼을 누르는 것으로 오늘 날짜로 바로 돌아올 수 있다.
                    </td>
				</tr>
				<tr>
                    <td><center><font size=4>1.2 한 주</font></center></td>
				</tr>
				<tr>
					<td>
                        1.2.1 한 주의 시간표가 표시된다.<br>
                        1.2.2 일정을 추가하려면 원하는 칸을 클릭하면 일정을 입력할 수 있는 칸이 추가된다.<br>
                        1.2.3 추가한 일정을 확인할 수 있다.<br>
                        1.2.4 추가한 일정을 클릭하면 삭제할 수 있다.<br>
                        1.2.5 드래그로 여러 날짜 일정 추가가 가능하다.<br>
                        1.2.6 개인일정 기간 변경 가능하다.<br>
                        1.2.7 좌측상단의 화살표 버튼을 누르는 것으로 주를 바꿀 수 있다.<br>
                        1.2.8 주를 바꿨을 때 today버튼을 누르는 것으로 이번 주로 바로 돌아올 수 있다.
                    </td>
				</tr>
				<tr>
                    <td><center><font size=4>1.3 하루</font></center></td>
				</tr>
				<tr>
					<td>
                        1.3.1 하루의 시간표가 표시된다.<br>
                        1.3.2 일정을 추가하려면 원하는 칸을 클릭하면 일정을 입력할 수 있는 칸이 추가된다.<br>
                        1.3.3 추가한 일정을 확인할 수 있다.<br>
                        1.3.4 추가한 일정을 클릭하면 삭제할 수 있다.<br>
                        1.3.5 개인일정 기간 변경 가능하다.<br>
                        1.3.6 좌측상단의 화살표 버튼을 누르는 것으로 요일을 바꿀 수 있다.<br>
                        1.3.7 요일을 바꿨을 때 today버튼을 누르는 것으로 오늘로 바로 돌아올 수 있다.
                    </td>
				</tr>
				<tr>
                    <td><center><font size=5><b>2. 빈강의실 페이지</b></font></center></td>
				</tr>
				<tr>
                    <td><center><font size=4>2.1 학교건물</font></center></td>
				</tr>
				<tr>
					<td>
                        2.1.1 학교건물을 클릭했을 때, 초록 색은 비어있는 강의실, 붉은 색은 수업 중인 강의실이다.<br>
                        2.1.2 강의실 호수를 클릭했을 때, 그 강의실의 시간표를 볼 수 있다.<br>
                        2.1.3 지도 확대축소 및 이동이 가능하다.</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>