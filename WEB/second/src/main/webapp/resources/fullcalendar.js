/*----현재 경로 가져 오기----*/
//현재 주소를 가져오는 것 같다: http://localhost:8083/myproj/view/my.jsp
var curWwwPath=window.document.location.href;
//호스트 주소 목록 가져오기 후 같다: myproj/view/my.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//호스트 주소 가져오는 것 같다: http://localhost:8083
var localhostPaht=curWwwPath.substring(0,pos);
//테이프 가져오는 '/' 프로젝트 이름, 만일: /myproj
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//받다 http://localhost:8083/myproj
var realPath=localhostPaht+projectName;

/*--컨텍스트 메뉴금지--*/
document.oncontextmenu = function() { 
	return false;
};

var curEvent = null;
	var currentStart = null;
	var currentEnd = null;
	function checkOnedaySchedule(sdate, edate) {
	var s_Date = Number(moment(sdate).format('MMDD'));
	var s_Time = Number(moment(sdate).format('HHmm'));
	var e_Date = Number(moment(edate).format('MMDD'));
	var e_Time = Number(moment(edate).format('HHmm'));

	if (s_Date == e_Date - 1 && s_Time == e_Time)
		return true;
	else
		return false;
}
function insertSchedule() {
	$.ajax({
		type : "POST",
		url : "insert",
		data : $('#modalForm').serialize(),
		success : function() {
			eventData = {
				title : $('#modalNewTitle').val(),
				start : currentStart,
				end : currentEnd,
			};
			// stick? = false : when month wiil be changed event will disappear.
			$('#calendar').fullCalendar('renderEvent', eventData, false);
		},
		error : function() {
			alert("failure");
		}
	});
	$("#calendarModal").modal('hide');
	$("#insertButton").hide();
}
function deleteSchedule() {
	$.ajax({
		type : "POST",
		url : "delete",
		data : $('#modalForm').serialize(),
		success : function() {
			$('#calendar').fullCalendar('removeEvents', curEvent._id);
		},
		error : function() {
			alert("failure");
		}
	});
	$("#calendarModal").modal('hide');
	$("#deleteButton").hide();
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
function changeScheduleTitle() {
	$.ajax({
		type : "POST",
		url : "delete",
		data : $('#modalForm').serialize(),
		success : function() {
			$('#calendar').fullCalendar('removeEvents', curEvent._id);
		},
		error : function() {
			alert("failure");
		}
	});
	$.ajax({
		type : "POST",
		url : "insert",
		data : $('#modalForm').serialize(),
		success : function() {
			eventData = {
				title : $('#modalNewTitle').val(),
				start : curEvent._start,
				end : curEvent._end,
			};
			// stick? = false : when month wiil be changed event will disappear.
			$('#calendar').fullCalendar('renderEvent', eventData, false);
		},
		error : function() {
			alert("failure");
		}
	});
	$("#calendarModal").modal('hide');
	$("#deleteButton").hide();
	$("#insertButton").hide();
}
function init_modal(modal, action) {
	// Setup up the modal dialog
	var $modal = $(modal)

	if (action == 'insert') {
		$(insertButton).show();
		$(changeButton).hide();
		$(deleteButton).hide();
		$(modalNewTitle).val('');
	} else if (action == 'delete') {
		$(changeButton).show();
		$(insertButton).hide();
		$(deleteButton).show();
	} else if (action == 'update' || action == 'show') {
		$(changeButton).hide();
		$(insertButton).hide();
		$(deleteButton).hide();
	}
	$(modalNewTitle).focus();
	$modal.modal('show');
}
$(document).ready(
		function() {
			//ban enter key
			$(window).keydown(function(event) {
				if (event.keyCode == 13) {
					event.preventDefault();
					return false;
				}
			});
			
			/*--calendar event sources--*/
			var source = new Array();
			source[0] = {
				url : realPath+'/calendarcollege', // use the `url` property
				color : '#9ac2b7', // an option!
				textColor : 'white', // an option!
				editable : false
			};
			source[1] = {
				url : realPath+'/calendarholiday', // use the `url` property
				color : '#F98E9D', // an option!
				textColor : 'white', // an option!
				editable : false
			};
			source[2] = {
				url : realPath+'/calendartimetable', // use the `url` property
				color : '#C9B37D', // an option!
				textColor : 'white', // an option!
				editable : false
			};
			source[3] = {
				url : realPath+'/calendarusertime',
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
								eventSources : [ source[0], source[1], source[2], source[3] ],
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
										$('#calendar').fullCalendar('removeEventSource', source[2]); //월 단위에서는 학교 시간표 숨김
										if ($(window).height() < 800) {
											h = 'auto';
										} else
											h = 800; // high enough to avoid scrollbars
									} else {
										$('#calendar').fullCalendar('removeEventSource', source[2]);
										$('#calendar').fullCalendar('addEventSource', source[2]);
										h = 'auto';
									}
									$('#calendar').fullCalendar('option', 'contentHeight', h);
								},
								eventRender: function (event, element, view) {
									if(event.allDay == false){
										if (view.name == "agendaWeek") {
											$(element).css("margin-top", "8px");
											$(element).css("margin-bottom", "8px");
											$(element).css("margin-right", "5px");
										}
										if (view.name == "agendaDay") {
											$(element).css("margin-top", "8px");
											$(element).css("margin-bottom", "8px");
										}
									}
								},
								longPressDelay : 800,
								selectable : true,
								selectHelper : true,
								select : function(start, end) {
									currentStart = start;
									currentEnd = end;
									if (checkOnedaySchedule(start, end) && $('#calendar').fullCalendar('getView').name == "month") {
										$('#calendar').fullCalendar('gotoDate', start);
										$('#calendar').fullCalendar('changeView', 'agendaDay');
									} else {
										document.getElementById('modalTitle').style.display = "none";
										document.getElementById('modalNewTitle').style.display = ""; // 보임
										if (checkOnedaySchedule(start, end))
											$('#modalBody').html(moment(start).format('YYYY/MM/DD'));
										else {
											if (end != null) {
												$('#modalBody')
														.html(
																moment(start).format('YYYY/MM/DD HH:mm') + " - "
																		+ moment(end).format('YYYY/MM/DD HH:mm'));
											} else {
												$('#modalBody').html(moment(start).format('YYYY/MM/DD HH:mm - '));
											}
										}
										$('#modalStart').val(JSON.stringify(start));
										$('#modalEnd').val(JSON.stringify(end));
										$('#calendar').fullCalendar('unselect');
										document.getElementById('modalNewTitle').required = true;
										init_modal('#calendarModal', 'insert');
									}
								},
								editable : true,
								eventDragStart : function(event) {
									currentStart = event.start;
									currentEnd = event.end;
								},
								eventDrop : function(event, delta, revertFunc) {
									$('#modalTitle').html(event.title + " 일정 변경");
									document.getElementById('modalTitle').style.display = "";
									document.getElementById('modalNewTitle').style.display = "none";
									if (event.allDay == true) {
										if (checkOnedaySchedule(currentStart, currentEnd)
												|| event.end == null)
											$('#modalBody').html(moment(event.start).format('YYYY/MM/DD'));
										else
											$('#modalBody').html(
													moment(event.start).format('YYYY/MM/DD - ') + moment(event.end).format('YYYY/MM/DD'));
									} else if (event.end == null) {
										$('#modalBody').html(moment(event.start).format('YYYY/MM/DD HH:mm - '));
									} else {
										$('#modalBody').html(
												moment(event.start).format('YYYY/MM/DD HH:mm') + " - "
														+ moment(event.end).format('YYYY/MM/DD HH:mm'));
									}
									init_modal('#calendarModal', 'update');
									updateSchedule(JSON.stringify(event.title), JSON.stringify(currentStart), JSON.stringify(currentEnd), JSON
											.stringify(event.start), JSON.stringify(event.end));
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
									$('#modalTitle').html(event.title + " 일정 변경");
									document.getElementById('modalTitle').style.display = "";
									document.getElementById('modalNewTitle').style.display = "none";

									if (event.end != null) {
										$('#modalBody')
												.html(
														moment(event.start).format('YYYY/MM/DD HH:mm') + " - "
																+ moment(event.end).format('YYYY/MM/DD HH:mm'));
									} else {
										$('#modalBody').html(moment(event.start).format('YYYY/MM/DD HH:mm - '));
									}
									
									init_modal('#calendarModal', 'update');
									updateSchedule(JSON.stringify(event.title), JSON.stringify(currentStart), JSON.stringify(currentEnd), JSON
											.stringify(event.start), JSON.stringify(event.end));
								},
								eventClick : function(event, jsEvent, view) {
									curEvent = event;
									$('#modalTitle').html(event.title);
									document.getElementById('modalTitle').style.display = "";
									document.getElementById('modalNewTitle').style.display = "none";
									document.getElementById('modalNewTitle').required = false;
									if (event.allDay == true) {
										if (checkOnedaySchedule(event.start, event.end) || event.end == null)
											$('#modalBody').html(moment(event.start).format('YYYY/MM/DD'));
										else
											$('#modalBody').html(
													moment(event.start).format('YYYY/MM/DD - ')
															+ moment(event.end).subtract(1, 'day').format('YYYY/MM/DD'));
									} else {
										if (event.end != null) {
											$('#modalBody')
													.html(
															moment(event.start).format('YYYY/MM/DD HH:mm') + " - "
																	+ moment(event.end).format('YYYY/MM/DD HH:mm'));
										} else {
											$('#modalBody').html(moment(event.start).format('YYYY/MM/DD HH:mm - '));
										}
									}
									if ("rgb(163, 166, 189)" == $(this).css('background-color')
											|| "rgb(58, 135, 173)" == $(this).css('background-color')) { // check user event using bg-color
										document.getElementById('modalTitle').style.display = "none";
										document.getElementById('modalNewTitle').style.display = "";
										$('#modalNewTitle').val(event.title);
										$('#modalTitleData').val(JSON.stringify(event.title));
										$('#modalStart').val(JSON.stringify(event.start));
										$('#modalEnd').val(JSON.stringify(event.end))
										init_modal('#calendarModal', 'delete');
									} else {
										init_modal('#calendarModal', 'show');
									}
								},
								eventLimit : 5, // allow "more" link when too many events
							})
		});