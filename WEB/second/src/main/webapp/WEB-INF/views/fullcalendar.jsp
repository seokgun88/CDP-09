<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/loading.css" type="text/css">
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
			url : "add",
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
	function deleteSchedule(title, start, end) {
		$.ajax({
			url : "delete",
			type : "POST",
			data : {
				"title" : title,
				"start" : start,
				"end" : end
			},
			success : function(response) {
				//get the response from server and process it
			}
		});
	}
	function updateSchedule(title, cstart, cend, start, end) {
		$.ajax({
			url : "update",
			type : "POST",
			data : {
				"title" : title,
				"cstart" : cstart,
				"cend" : cend,
				"start" : start,
				"end" : end
			},
			success : function(response) {
				//get the response from server and process it
			}
		});
	}
	$(document).ready(
			function() {
				var currentStart = null;
				var currentEnd = null;
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
							                   textColor: 'black',  // an option!
							                   editable : false
							               },
							               {
							                   url: '/second/calendarholiday', // use the `url` property
							                   color: 'red',    // an option!
							                   textColor: 'black',  // an option!
							                   editable : false
							               },
							               {
							                   url: '/second/calendartimetable', // use the `url` property
							                   color: 'green',    // an option!
							                   textColor: 'black',  // an option!
							                   editable : false
							               },
							               {
							            		url: '/second/calendarusertime'   
							               }
							           ],	
					        loading: function(isLoading) {				
								if (isLoading){
									$('#loading').show();
								}
								else{
									$('#loading').hide();
								}
							},
					        longPressDelay : 150,
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
									addSchedule(JSON.stringify(title), JSON
											.stringify(start), JSON
											.stringify(end));
									$('#calendar').fullCalendar('renderEvent',
											eventData, true); // stick? = true
								}
								$('#calendar').fullCalendar('unselect');
							},
							editable : true,
							eventDragStart : function(event) {
								//var myResource = $('#calendar').fullCalendar('getResourceById', event.resourceId);
								//currentType = myResource.type; // the variable of your resource 
								currentStart = event.start;
								currentEnd = event.end;
							},
							eventDrop : function(event, delta, revertFunc) {
								if (confirm("일정을 변경 할까요?")) {
									updateSchedule(JSON.stringify(event.title),
											JSON.stringify(currentStart), JSON
													.stringify(currentEnd),
											JSON.stringify(event.start), JSON
													.stringify(event.end));
								} else {
									revertFunc(); //cancel change
								}
							},
							eventResizeStart : function(event) {
								currentStart = event.start;
								currentEnd = event.end;
							},
							eventResize : function(event, delta, revertFunc) {
								if (confirm("시간을 변경 할까요?")) {
									updateSchedule(JSON.stringify(event.title),
											JSON.stringify(currentStart), JSON
													.stringify(currentEnd),
											JSON.stringify(event.start), JSON
													.stringify(event.end));
								} else {
									revertFunc(); //cancel change
								}
							},
							eventClick : function(event, jsEvent, view) {
								if ("rgb(58, 135, 173)" == $(this).css(
										'background-color')) { // check user event using bg-color
									var r = confirm(event.title
											+ " 일정을 삭제 할까요?");
									if (r === true) {
										$('#calendar').fullCalendar(
												'removeEvents', event._id);
										deleteSchedule(JSON
												.stringify(event.title), JSON
												.stringify(event.start), JSON
												.stringify(event.end));
									}
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
					<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>
						Lgout</a>
					</li>
				</ul>
			</div>
		</nav>
		
		<div id="loading">
			<img id="loading-image" src="resources/KNUPLAN.png" alt="Loading..." />
		</div>
		
		<div id='calendar'></div>
	</div>
</body>
</html>