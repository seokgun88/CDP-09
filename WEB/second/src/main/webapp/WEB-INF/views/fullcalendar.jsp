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
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lang/ko.js'></script>

<script>
	function my_func() {
	    var moment = $('#calendar').fullCalendar('getDate');
	    var res = moment.format().substr(5, 2);
	    alert("The current date of the calendar is " + moment.format());
	    console.log(res);
	}
	function addSchedule(title, start, end) {
		$.ajax({
			url : "schedule",
			type : "POST",
			data : {
				"title" : title,
				"start" : start,
				"end" : end
			},
			success : function(response) {
				//get the response from server and process it
				//alert("일정이 등록됬습니다.")
			}
		});
	}
	$(document).ready(
			function() {
				// page is now ready, initialize the calendar...
				$('#calendar').fullCalendar(	{
		            		lang: 'ko',
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							eventSources: [ // your event source
							               {							            	   
							                   url: '/second/calendarcollege', // use the `url` property
							                   color: 'yellow',    // an option!
							                   textColor: 'black'  // an option!
							               },
							               {
							                   url: '/second/calendarholiday', // use the `url` property
							                   color: 'red',    // an option!
							                   textColor: 'black'  // an option!
							               },
							               {
							            	   url: '/second/calendarusertime'
							               }
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
									addSchedule(JSON.stringify(title), JSON.stringify(start), JSON.stringify(end));
									$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
								}
								$('#calendar').fullCalendar('unselect');
							},
							editable : true,
							eventDragStart: function( event, jsEvent, ui, view ) { 
								
							},
						    eventDrop: function(event, delta, revertFunc) {
						        //alert(event.title + " was dropped on " + event.start.format());
						        addSchedule(JSON.stringify(event.title), JSON.stringify(event.start), JSON.stringify(event.end))

						        if (!confirm("일정을 변경 하시겠습니까?")) {
						            revertFunc(); //cancel change
						        }						        
						    },
							eventLimit : true, // allow "more" link when too many events
						})
			});
	
	
</script>
<title>full calendar</title>
</head>
<body>
	<div class="container">
		<a href="home"> <img src="resources/KNUPLAN.png"
			class="img-rounded" alt="Cinque Terre">
		</a>
		
        <nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">KNU PLAN</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">일정</a>
					<li><a href="timetable">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</nav>
		
		<div id='calendar'></div>
		<button id=my-button onclick="my_func()">Click me</button>
</body>
</html>