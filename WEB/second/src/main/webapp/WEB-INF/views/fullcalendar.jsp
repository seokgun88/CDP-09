<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
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
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.js'></script>
<style type="text/css">
.modal-vertical-centered {
	transform: translate(0, 100%) !important;
	-ms-transform: translate(0, 100%) !important; /* IE 9 */
	-webkit-transform: translate(0, 100%) !important;
	/* Safari and Chrome */
}
</style>
<script>
	/* function addSchedule(title, start, end) {
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
	} */
	function checkOnedaySchedule(sdate, edate){
		var s_Date = Number(moment(sdate).format('MMDD'));
		var s_Time = Number(moment(sdate).format('HHmm'));
		var e_Date = Number(moment(edate).format('MMDD'));
		var e_Time = Number(moment(edate).format('HHmm'));

		if(s_Date == e_Date-1 && s_Time == e_Time)
			return true;
		else
			return false;
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
				$('#calendar').fullCalendar(
						{
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							eventSources : [ // your event source
							{
								url : '/second/calendarcollege', // use the `url` property
								color : 'yellow', // an option!
								textColor : 'black', // an option!
								editable : false
							}, {
								url : '/second/calendarholiday', // use the `url` property
								color : 'red', // an option!
								textColor : 'black', // an option!
								editable : false
							}, {
								url : '/second/calendartimetable', // use the `url` property
								color : 'green', // an option!
								textColor : 'black', // an option!
								editable : false
							}, {
								url : '/second/calendarusertime'
							} ],
							loading : function(isLoading) {
								if (isLoading) {
									$('#loading').show();
								} else {
									$('#loading').hide();
								}
							},
							viewRender : function(view, element) {
								var h;
								if (view.name == "month") {
									h = 700; // high enough to avoid scrollbars
								} else {
									h = NaN;
								}
								$('#calendar').fullCalendar('option',
										'contentHeight', h);
							},
							longPressDelay : 200,
							selectable : true,
							selectHelper : true,
							select : function(start, end) {
								//var title = prompt('일정 제목:');
								//var eventData;
								document.getElementById('modalTitle').style.display  = "none";
								document.getElementById('modalNewTitle').style.display = ""; // 보임
								if(checkOnedaySchedule(start, end))
									$('#modalBody').html(
											moment(start).format(
											'YYYY/MM/DD'));
								else{
									if(end != null){
										$('#modalBody').html(
												moment(start).format(
												'YYYY/MM/DD HH:mm') + " - " + 
												moment(end).format(
												'YYYY/MM/DD HH:mm'));
									}
									else{
										$('#modalBody').html(
												moment(start).format(
												'YYYY/MM/DD HH:mm - '));
									}
								}
								$('#modalStart').val(JSON.stringify(start));
								$('#modalEnd').val(JSON.stringify(end));
								$('#modalButton').html("등록");
								document.getElementById('modalButton').style.display = ""; // 보임
								document.getElementById('modalForm').action = "insert";
								$('#calendarModal').modal();
								/* if (title) {
									eventData = {
										title : title,
										start : start,
										end : end
									};
									/addSchedule(JSON.stringify(title), JSON
											.stringify(start), JSON
											.stringify(end));
									$('#calendar').fullCalendar('renderEvent',
											eventData, true); // stick? = true
								} */
								$('#calendar').fullCalendar('unselect');
							},
							editable : true,
							eventDragStart : function(event) {
								currentStart = event.start;
								currentEnd = event.end;
							},
							eventDrop : function(event, delta, revertFunc) {
								$('#modalTitle').html(event.title + " 일정 변경");
								document.getElementById('modalTitle').style.display  = "";
								document.getElementById('modalNewTitle').style.display = "none";
								document.getElementById('modalButton').style.display = "none"; // 숨김
								if(event.allDay == true){
									if(checkOnedaySchedule(currentStart, currentEnd))
										$('#modalBody').html(
												moment(event.start).format(
												'YYYY/MM/DD'));
									else
										$('#modalBody').html(
												moment(event.start).format(
												'YYYY/MM/DD - ') + 
												moment(event.end).format(
												'YYYY/MM/DD'));										
								}
								else if(event.end == null){
									$('#modalBody').html(
											moment(event.start).format(
											'YYYY/MM/DD HH:mm - '));
								}
								else{
									$('#modalBody').html(
											moment(event.start).format(
											'YYYY/MM/DD HH:mm') + " - " + 
											moment(event.end).format(
											'YYYY/MM/DD HH:mm'));
								}
								$('#calendarModal').modal();
								updateSchedule(JSON.stringify(event.title),
										JSON.stringify(currentStart), JSON
										.stringify(currentEnd),
								JSON.stringify(event.start), JSON
										.stringify(event.end));
								/* if (confirm("일정을 변경 할까요?")) {									
								} else {
									revertFunc(); //cancel change
								} */
							},
							eventResizeStart : function(event) {
								currentStart = event.start;
								currentEnd = event.end;
							},
							eventResize : function(event, delta, revertFunc) {
								$('#modalTitle').html(event.title + " 시간 변경");
								document.getElementById('modalTitle').style.display  = "";
								document.getElementById('modalNewTitle').style.display = "none";
								$('#modalBody').html(moment(event.start).format('YYYY/MM/DD HH:mm') + " - " + 
										moment(event.end).format(
										'YYYY/MM/DD HH:mm'));
								document.getElementById('modalButton').style.display = "none"; // 숨김
								$('#calendarModal').modal();
								updateSchedule(JSON.stringify(event.title),
										JSON.stringify(currentStart), JSON
												.stringify(currentEnd),
										JSON.stringify(event.start), JSON
												.stringify(event.end));
								/* if (confirm("시간을 변경 할까요?")) {
								} else {
									revertFunc(); //cancel change
								} */
							},
							eventClick : function(event, jsEvent, view) {
								$('#modalTitle').html(event.title);
								document.getElementById('modalTitle').style.display  = "";
								document.getElementById('modalNewTitle').style.display = "none";
								document.getElementById('modalNewTitle').required = false;
								if(event.allDay == true){
									if(checkOnedaySchedule(event.start, event.end) || event.end == null)
										$('#modalBody').html(
												moment(event.start).format(
												'YYYY/MM/DD'));
									else
										$('#modalBody').html(
												moment(event.start).format(
												'YYYY/MM/DD - ') + 
												moment(event.end).format(
												'YYYY/MM/DD'));
								}
								else{
									$('#modalBody').html(
											moment(event.start).format(
											'YYYY/MM/DD HH:mm') + " - " + 
											moment(event.end).format(
											'YYYY/MM/DD HH:mm'));
								}
								if ("rgb(58, 135, 173)" == $(this).css(
										'background-color')) { // check user event using bg-color
									/* var r = confirm(event.title
											+ " 일정을 삭제 할까요?");
									if (r === true) {
										$('#calendar').fullCalendar(
												'removeEvents', event._id);
										deleteSchedule(JSON
												.stringify(event.title), JSON
												.stringify(event.start), JSON
												.stringify(event.end));
									} */
									$('#modalTitleData').val(JSON.stringify(event.title));
									$('#modalStart').val(JSON.stringify(event.start));
									$('#modalEnd').val(JSON.stringify(event.end));
									$('#modalButton').html("삭제");
									document.getElementById('modalButton').style.display = ""; // 보임
									document.getElementById('modalForm').action = "delete";
								} else {
									document.getElementById('modalButton').style.display = "none"; // 숨김
								}
								$('#calendarModal').modal();
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
			class="img-responsive" alt="Knu Plan">
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
				<div id="loading"></div>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">일정</a></li>
					<li><a href="timetable">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> Lgout</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<!-- Modal -->
		<div id="calendarModal" class="modal fade">
			<div class="modal-dialog modal-vertical-centered">
				<form id="modalForm" class="form-horizontal" role="form"
					action="delete" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span> <span class="sr-only">close</span>
							</button>
							<h4 id="modalTitle" class="modal-title"></h4>
							<input id="modalNewTitle" type="text" class="form-control"
								name="new_title" style="display:none" placeholder="새로운 일정을 입력하세요" required>
						</div>
						<div id="modalBody" class="modal-body"></div>
						<div class="modal-footer">
							<input id="modalTitleData" type="hidden" class="form-control"
								name="title"> <input id="modalStart" type="hidden"
								class="form-control" name="start"> <input id="modalEnd"
								type="hidden" class="form-control" name="end">
							<button type="submit" id="modalButton" class="btn btn-default">삭제</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">닫기</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div id='calendar'></div>
		<div><br /><br /><br /></div>
	</div>
</body>
</html>