<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
<script src='${pageContext.request.contextPath}/resources/fullcalendar-2.7.1/lib/jquery.min.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" />
<script src='${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js'></script>
<link rel="stylesheet" href="resources/bgImg.css" type="text/css">
<script type="text/javascript">
	document.oncontextmenu = function() { // 컨텍스트 메뉴금지
		return false;
	};
</script>
<style type="text/css">
@media (min-width: 400px) {
	.table-condensed>thead>tr>th, .table-condensed>tbody>tr>th, .table-condensed>tfoot>tr>th, .table-condensed>thead>tr>td, .table-condensed>tbody>tr>td, .table-condensed>tfoot>tr>td{
	    max-width:150px;
	}
}
@media (max-width: 500px) {
    td{
		font-size: 5px;
    }
	.table-condensed>thead>tr>th, .table-condensed>tbody>tr>th, .table-condensed>tfoot>tr>th, .table-condensed>thead>tr>td, .table-condensed>tbody>tr>td, .table-condensed>tfoot>tr>td{
	    padding: 1px;
		max-width:150px;
	}
}
</style>
<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/images/hobanwoo.ico" />
<title>오늘의 식단표</title>
</head>

<body style="overflow-x: hidden; overflow-y: auto;">
	<div class="container-fluid bgimage" style="background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-cartebanner.jpg);">
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
				<li><a href="fullcalendar">일정</a></li>
				<li><a href="knumap">빈강의실</a></li>
				<li class="active"><a href="#">오늘의 식단표</a></li>
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

	<div class="container">
		<table class="table table-striped table-bordered  table-condensed">
			<thead>
				<tr>
					<th width="15"><center></center></th>
					<c:forEach items="${buildinglist}" var="buildinglist">
						<th><center>${buildinglist}</center></th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach items="${breakfastlist}" var="breakfastlist">
						<td>${breakfastlist}</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach items="${lunchlist}" var="lunchlist">
						<td>${lunchlist}</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach items="${dinnerlist}" var="dinnerlist">
						<td>${dinnerlist}</td>
					</c:forEach>
				</tr>

			</tbody>
		</table>
	</div>
</body>
</html>