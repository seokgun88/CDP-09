<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="icon" type="image/ico"  href="${pageContext.request.contextPath}/resources/images/hobanwoo.ico"/>
<title>KNU PLAN 사용 방법</title>
</head>

<body style="overflow-x:hidden; overflow-y:auto;">
	<div class="container-fluid bgimage" style="background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-helpbanner.jpg);">
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
				<li class="active"><a href="help"><span class="glyphicon glyphicon-question-sign"></span> Help</a></li>
				<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Lgout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container" >
		<table class="table table-striped table-bordered">
			<tbody>
                <tr>
					<th><center><font size=5>1. 일정 페이지</font></center></th>
				</tr>
				<tr>
					<td>
						*모바일에서는 클릭을 하기 위해 1초 정도 터치를 하면 되요.<br>
						*캘린더에서는 학사 일정, 공휴일, 개인 일정을 볼 수 있어요.<br>
						*사용자 개인 일정 같은 경우 추가, 수정, 삭제 가능 해요.
					</td>
				</tr>
				<tr>
                    <td><center><font size=4>전체</font></center></td>
				</tr>
				<tr>
					<td>
                        1 추가한 일정을 클릭하면 내용 확인과 삭제를 할 수 있어요.<br>
                        2 드래그로 여러 날짜, 시간 일정 추가 가능해요.<br>
                        3 일정 제일 위나 아래를 클릭하여 일정 기간을 변경 할 수 있어요.<br>
                        4 today버튼을 누르는 것으로 오늘 날짜로 바로 돌아올 수 있어요.
                    </td>
				</tr>
				<tr>
                    <td><center><font size=4>한 달 (Month)</font></center></td>
				</tr>
				<tr>
					<td>
                        1 날짜를 클릭하면 해당 날짜의 일정이 표시되요.<br>
                        2 사용자가 하루 일정을 추가하려면 한 주 혹은 하루의 일정 페이지로 들어가야 해요.<br>
						3 하루 일정이 아닌 여러 날짜 일정은 드래그로 추가 가능 해요.<br>
                        4 좌측상단의 화살표 버튼을 누르는 것으로 달을 바꿀 수 있어요.
                    </td>
				</tr>
				<tr>
                    <td><center><font size=4>한 주, 하루 (Week, Day)</font></center></td>
				</tr>
				<tr>
					<td>
                        1 한 주, 하루의 시간표가 표시되요.<br>
                        2 일정을 추가하려면 원하는 칸을 클릭하면 일정을 입력할 수 있는 칸이 추가되요.<br>
                        3 좌측상단의 화살표 버튼을 누르는 것으로 날짜를 바꿀 수 있어요.<br>
                    </td>
				</tr>
				<tr>
                    <td><center><font size=5><b>2. 빈강의실 페이지</b></font></center></td>
				</tr>
				<tr>
					<td>
                        1 학교건물을 클릭했을 때, 초록 색은 비어있는 강의실, 붉은 색은 수업 중인 강의실이에요.<br>
                        2 강의실 호수를 클릭했을 때, 그 강의실의 시간표를 볼 수 있어요.<br>
                        3 지도 확대축소 및 이동이 가능해요.</td>
				</tr>
				
				<tr>
                    <td><center><font size=5><b>3. 오늘의 식단표</b></font></center></td>
				</tr>
				<tr>
					<td>
                        *모든 학생식당의 오늘 식단표를 한번에 모아 볼 수 있어요.
				</tr>
				
				
				<tr>
                    <td><center><font size=5><b>4. 도서관 빈 자리 찾기</b></font></center></td>
				</tr>
				<tr>
					<td>
                        *모든 열람실의 현재 빈 자리 상황을 볼 수 있어요.
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>