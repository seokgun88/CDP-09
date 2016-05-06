<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.css' />
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/jquery.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.js'></script>

<script>
	$(document).ready(
			function() {
				// page is now ready, initialize the calendar...
				$('#calendar').fullCalendar(
						{
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							eventSources: [

							               // your event source
							               {
							                   url: '/second/calendarcollege', // use the `url` property
							                   color: 'yellow',    // an option!
							                   textColor: 'black'  // an option!
							               },
							               {
							                   url: '/second/calendarholiday', // use the `url` property
							                   color: 'red',    // an option!
							                   textColor: 'black'  // an option!
							               }

							               // any other sources...

							           ],			
							selectable : true,
							selectHelper : true,
							select : function(start, end) {
								var title = prompt('일정 제목:');
								var eventData;
								if (title) {
									eventData = {
										title : title,
										start : start,
										end : end
									};
									$(function addSchedule(title, start, end) {
										$.post("schedule", {
											"title" : title,
											"start" : start,
											"end" : end
										}, 
										function(jsonResult) {
											alert(jsonResult);
										}, 'json').done(function(jsonResult) {
											console.log(jsonResult);
										}).fail(function(jsonResult) {
											console.log(jsonResult);
										});
										alert("end of post");
									});
									$('#calendar').fullCalendar('renderEvent',
											eventData, true); // stick? = true
								}
								$('#calendar').fullCalendar('unselect');
							},
							editable : true,
							eventLimit : true, // allow "more" link when too many events
						})
			});
</script>
<title>full calendar</title>
</head>
<body>
	<div class="container">
		<a href="KNUPLAN_HOME.html"> <img src="resources/KNUPLAN.png"
			class="img-rounded" alt="Cinque Terre">
		</a>

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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="timetable">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<div id='calendar'></div>
</body>
</html>