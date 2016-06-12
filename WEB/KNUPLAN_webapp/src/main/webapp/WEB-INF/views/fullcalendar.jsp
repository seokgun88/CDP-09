<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
<script src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/jquery.min.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" />
<script src='${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/moment.min.js'></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.min.css' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/fullcalendar.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/fullcalendar.js'></script>
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
#calendar .fc-today {
	background-color: #ECF5F5;
}
@media (max-width: 500px) {
	body .fc-event { 
	   font-size: 5px;
	}
	body .fc-day-number { 
	   font-size: 8px;
	}	
}
</style>
<link rel="icon" type="image/ico"  href="${pageContext.request.contextPath}/resources/images/hobanwoo.ico"/>
<title>나의 일정</title>
</head>
<body>
	<div id="loading"></div>
	
	<div class="container-fluid bgimage" style="background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-homebanner.jpg);">
		<div class="row">
			<div class="col-xs-1 col-md-4"></div>
			<div class="col-xs-10 col-md-4">
				<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/knuplan-title.png" alt="KNU PLAN" style="min-height: 50px;">
			</div>
		</div>
	</div>

	<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="row navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="fullcalendar">일정</a></li>
				<li><a href="knumap">빈강의실</a></li>
				<li><a href="knumenu">오늘의 식단표</a></li>
				<li><a href="knulibrary">도서관 빈자리</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<div class="hidden-xs">
						<div style="float: right; margin: 18px 0 0 0;">
							<script id="_waume7">
								var _wau = _wau || [];
								_wau.push([ "small", "rmvhnojtde5v", "me7" ]);
								(function() {
									var s = document.createElement("script");
									s.async = true;
									s.src = "http://widgets.amung.us/small.js";
									document.getElementsByTagName("head")[0].appendChild(s);
								})();
							</script>
						</div>
					</div>
				</li>
				<li><a href="help"><span class="glyphicon glyphicon-question-sign"></span> Help</a></li>
				<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Lgout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<!-- Modal -->
	<div id="calendarModal" class="modal fade">
		<div class="modal-dialog modal-vertical-centered">
			<form id="modalForm" class="form-horizontal" action="" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span> <span class="sr-only">close</span>
						</button>
						<h4 id="modalTitle" class="modal-title"></h4>
						<input id="modalNewTitle" type="text" class="form-control" name="new_title" style="display: none" placeholder="새로운 일정을 입력하세요" required>
					</div>
					<div id="modalBody" class="modal-body"></div>
					<div class="modal-footer">
						<input id="modalTitleData" type="hidden" class="form-control" name="title"> 
						<input id="modalStart" type="hidden" class="form-control" name="start">
						<input id="modalEnd" type="hidden" class="form-control" name="end">
						<input type="button" id="insertButton" class="btn btn-default" value="등록"  onclick="insertSchedule()" />
						<input type="button" id="changeButton" class="btn btn-default" value="수정"  onclick="changeScheduleTitle()" />
						<input type="button" id="deleteButton" class="btn btn-default" value="삭제"  onclick="deleteSchedule()" />
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="container">
		<div id='calendar'></div>
	</div>

	<div>
		<br /> <br /> <br />
	</div>
		
</body>
</html>