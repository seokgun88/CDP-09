<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/jquery.min.js'></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" />
<script
	src='${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/moment.min.js'></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.min.css' />
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.min.js'></script>
<link rel="stylesheet" href="resources/loading.css" type="text/css">
<link rel="stylesheet" href="resources/bgImg.css" type="text/css">
<style type="text/css">
.modal-vertical-centered {
	transform: translate(0, 100%) !important;
	-ms-transform: translate(0, 100%) !important; /* IE 9 */
	-webkit-transform: translate(0, 100%) !important;
	/* Safari and Chrome */
}

#calendar .fc-day-number {
    text-decoration: underline;
    text-align: right;
}

#calendar .fc-day-number:hover {
    cursor: pointer;
}
#calendar .fc-today{
   background-color: #ECF5F5;
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
	function init_modal(modal) {
		// Setup up the modal dialog
		var $modal = $(modal)
		
		$(modalNewTitle).val('');
		$(modalNewTitle).focus();
		$modal.modal('show');
	}
	$(document)
			.ready(
					function() {
						var currentStart = null;
						var currentEnd = null;
						var source = new Array();
						source[0] = {
							url : '${pageContext.request.contextPath}/calendarcollege', // use the `url` property
							color : '#9ac2b7', // an option!
							textColor : 'white', // an option!
							editable : false
						};
						source[1] = {
							url : '${pageContext.request.contextPath}/calendarholiday', // use the `url` property
							color : '#F98E9D', // an option!
							textColor : 'white', // an option!
							editable : false
						};
						source[2] = {
							url : '${pageContext.request.contextPath}/calendartimetable', // use the `url` property
							color : '#C9B37D', // an option!
							textColor : 'white', // an option!
							editable : false
						};
						source[3] = {
							url : '${pageContext.request.contextPath}/calendarusertime',
							color : '#A3A6BD', // an option!
							textColor : 'white' // an option!
						};
						// page is now ready, initialize the calendar...
						$('#calendar')
								.fullCalendar(
										{
											header : {
												left : 'prev,next today',
												center : 'title',
												right : 'month,agendaWeek,agendaDay'
											},
											eventSources : [ source[0],
													source[1], source[2],
													source[3] ],
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
													$('#calendar')
															.fullCalendar(
																	'removeEventSource',
																	source[2]); //월 단위에서는 학교 시간표 숨김
													if ($(window).height() < 800) {
														h = 'auto';
													} else
														h = 800; // high enough to avoid scrollbars
												} else {
													$('#calendar')
															.fullCalendar(
																	'removeEventSource',
																	source[2]);
													$('#calendar')
															.fullCalendar(
																	'addEventSource',
																	source[2]);
													h = 'auto';
												}
												$('#calendar').fullCalendar(
														'option',
														'contentHeight', h);
											},
											longPressDelay : 1000,
											selectable : true,
											selectHelper : true,
											select : function(start, end) {
												if (checkOnedaySchedule(start,
														end)
														&& $('#calendar')
																.fullCalendar(
																		'getView').name == "month") {
													$('#calendar')
															.fullCalendar(
																	'gotoDate',
																	start);
													$('#calendar')
															.fullCalendar(
																	'changeView',
																	'agendaDay');
												} else {
													document
															.getElementById('modalTitle').style.display = "none";
													document
															.getElementById('modalNewTitle').style.display = ""; // 보임
													if (checkOnedaySchedule(
															start, end))
														$('#modalBody')
																.html(
																		moment(
																				start)
																				.format(
																						'YYYY/MM/DD'));
													else {
														if (end != null) {
															$('#modalBody')
																	.html(
																			moment(
																					start)
																					.format(
																							'YYYY/MM/DD HH:mm')
																					+ " - "
																					+ moment(
																							end)
																							.format(
																									'YYYY/MM/DD HH:mm'));
														} else {
															$('#modalBody')
																	.html(
																			moment(
																					start)
																					.format(
																							'YYYY/MM/DD HH:mm - '));
														}
													}
													$('#modalStart')
															.val(
																	JSON
																			.stringify(start));
													$('#modalEnd')
															.val(
																	JSON
																			.stringify(end));
													$('#modalButton')
															.html("등록");
													document
															.getElementById('modalButton').style.display = ""; // 보임
													document
															.getElementById('modalForm').action = "insert";
													init_modal('#calendarModal');
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
													$('#calendar')
															.fullCalendar(
																	'unselect');
												}
											},
											editable : true,
											eventDragStart : function(event) {
												currentStart = event.start;
												currentEnd = event.end;
											},
											eventDrop : function(event, delta,
													revertFunc) {
												$('#modalTitle').html(
														event.title + " 일정 변경");
												document
														.getElementById('modalTitle').style.display = "";
												document
														.getElementById('modalNewTitle').style.display = "none";
												document
														.getElementById('modalButton').style.display = "none"; // 숨김
												if (event.allDay == true) {
													if (checkOnedaySchedule(
															currentStart,
															currentEnd))
														$('#modalBody')
																.html(
																		moment(
																				event.start)
																				.format(
																						'YYYY/MM/DD'));
													else
														$('#modalBody')
																.html(
																		moment(
																				event.start)
																				.format(
																						'YYYY/MM/DD - ')
																				+ moment(
																						event.end)
																						.format(
																								'YYYY/MM/DD'));
												} else if (event.end == null) {
													$('#modalBody')
															.html(
																	moment(
																			event.start)
																			.format(
																					'YYYY/MM/DD HH:mm - '));
												} else {
													$('#modalBody')
															.html(
																	moment(
																			event.start)
																			.format(
																					'YYYY/MM/DD HH:mm')
																			+ " - "
																			+ moment(
																					event.end)
																					.format(
																							'YYYY/MM/DD HH:mm'));
												}
												$('#calendarModal').modal();
												updateSchedule(
														JSON
																.stringify(event.title),
														JSON
																.stringify(currentStart),
														JSON
																.stringify(currentEnd),
														JSON
																.stringify(event.start),
														JSON
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
											eventResize : function(event,
													delta, revertFunc) {
												$('#modalTitle').html(
														event.title + " 시간 변경");
												document
														.getElementById('modalTitle').style.display = "";
												document
														.getElementById('modalNewTitle').style.display = "none";
												$('#modalBody')
														.html(
																moment(
																		event.start)
																		.format(
																				'YYYY/MM/DD HH:mm')
																		+ " - "
																		+ moment(
																				event.end)
																				.format(
																						'YYYY/MM/DD HH:mm'));
												document
														.getElementById('modalButton').style.display = "none"; // 숨김
												$('#calendarModal').modal();
												updateSchedule(
														JSON
																.stringify(event.title),
														JSON
																.stringify(currentStart),
														JSON
																.stringify(currentEnd),
														JSON
																.stringify(event.start),
														JSON
																.stringify(event.end));
												/* if (confirm("시간을 변경 할까요?")) {
												} else {
													revertFunc(); //cancel change
												} */
											},
											eventClick : function(event,
													jsEvent, view) {
												$('#modalTitle').html(
														event.title);
												document
														.getElementById('modalTitle').style.display = "";
												document
														.getElementById('modalNewTitle').style.display = "none";
												document
														.getElementById('modalNewTitle').required = false;
												if (event.allDay == true) {
													if (checkOnedaySchedule(
															event.start,
															event.end)
															|| event.end == null)
														$('#modalBody')
																.html(
																		moment(
																				event.start)
																				.format(
																						'YYYY/MM/DD'));
													else
														$('#modalBody')
																.html(
																		moment(
																				event.start)
																				.format(
																						'YYYY/MM/DD - ')
																				+ moment(
																						event.end)
																						.format(
																								'YYYY/MM/DD'));
												} else {
													$('#modalBody')
															.html(
																	moment(
																			event.start)
																			.format(
																					'YYYY/MM/DD HH:mm')
																			+ " - "
																			+ moment(
																					event.end)
																					.format(
																							'YYYY/MM/DD HH:mm'));
												}
												if ("rgb(163, 166, 189)" == $(
														this).css(
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
													$('#modalTitleData')
															.val(
																	JSON
																			.stringify(event.title));
													$('#modalStart')
															.val(
																	JSON
																			.stringify(event.start));
													$('#modalEnd')
															.val(
																	JSON
																			.stringify(event.end));
													$('#modalButton')
															.html("삭제");
													document
															.getElementById('modalButton').style.display = ""; // 보임
													document
															.getElementById('modalForm').action = "delete";
												} else {
													document
															.getElementById('modalButton').style.display = "none"; // 숨김
												}
												init_modal('#calendarModal');
											},
											eventLimit : 5, // allow "more" link when too many events
										})
					});
</script>
<title>full calendar</title>
</head>
<body>
	<div class="container-fluid bgimage" 
	style="background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-homebanner.png);">
		<div class="row">
			<div class="col-xs-1 col-md-4"></div>
			<div class="col-xs-10 col-md-4">
				<img class="img-responsive"
					src="${pageContext.request.contextPath}/resources/images/knuplan-title.png"
					alt="KNU PLAN" style="min-height: 50px;">
			</div>
		</div>
	</div>

	<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="row navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="col-xs-1">
				<div id="loading"></div>
			</div>
		</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">일정</a></li>
						<!-- <li><a href="knumap">빈강의실</a></li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<div class="hidden-xs">
								<div style="float: right; margin: 15px 0 0 0;">
									<script id="_waume7">
										var _wau = _wau || [];
										_wau.push([ "small", "rmvhnojtde5v",
												"me7" ]);
										(function() {
											var s = document
													.createElement("script");
											s.async = true;
											s.src = "http://widgets.amung.us/small.js";
											document
													.getElementsByTagName("head")[0]
													.appendChild(s);
										})();
									</script>
								</div>
							</div>
						</li>
						<li><a href="logout"><span
								class="glyphicon glyphicon-log-out"></span> Lgout</a>
						</li>
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
		
	<div class="container">
		<div id='calendar'></div>
	</div>

	<div><br /><br /><br /></div>
	</div>
</body>
</html>