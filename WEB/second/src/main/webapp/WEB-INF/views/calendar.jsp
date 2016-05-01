<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>calendar</title>
<style type="text/css">
#wrap {
	width: 800px;
	height: 700px;
	margin: 0px auto;
}

#content {
	width: 800px;
	height: 535px;
}

/*------------------diary css 시작------------------------------- */
/* #= id , . = class*/
#diaryTitle {
	width: 800px;
	text-align: center;
	margin-top: 5px
}

#diaryContent {
	width: 800px;
	text-align: center
}

.dTable {
	border: 1px solid #E1E6F6;
	border-spacing: 0px
}

th {
	width: 120px;
	border: 1px solid #E1E6F6;
}

.sunTitle {
	background-color: #FF0000;
	font-weight: bold;
	color: #FFFFFF
}

.setTitle {
	background-color: #0000FF;
	font-weight: bold;
	color: #FFFFFF
}

#dayTitle {
	font-size: 20px;
	font-family: 돋움;
	font-weight: bold;
	vertical-align: top;
}

td {
	width: 120px;
	height: 50px;
	border: 1px solid #E6E6E7;
	text-align: left;
	vertical-align: top;
	padding-left: 3px;
	padding-top: 2px
}

.blank {
	background-color: #F1F1F1
}
/*------------------diary css 끝------------------------------- */
</style>
<script type="text/javascript">
	
</script>
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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="timetable">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</nav>
		<div id="wrap">
			<div id="content">
				<%
					Calendar cal = Calendar.getInstance();
					int nowYear = cal.get(Calendar.YEAR);
					int nowMonth = cal.get(Calendar.MONTH) + 1;
					int nowDay = cal.get(Calendar.DAY_OF_MONTH);
				%>
				<div id="diaryTitle">
					<span id="dayTitle"> <%=nowYear%>.<%=nowMonth%>
					</span>
				</div>
				<div id="diaryContent">
					<table class="dTable">
						<thead>
							<tr>
								<th class="sunTitle">일</th>
								<th>월</th>
								<th>화</th>
								<th>수</th>
								<th>목</th>
								<th>금</th>
								<th class="setTitle">토</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int tempDay = 1;; tempDay++) {
									//증가하는 임시날짜를 달력객체 설정
									cal.set(Calendar.DAY_OF_MONTH, tempDay);
									if (tempDay != cal.get(Calendar.DAY_OF_MONTH)) {
										//설정된 날짜와 임시날짜가 다르다면 끝 일이므로 
										//반복문을 빠져나간다.
										break;
									} //end if
									switch (tempDay) {
									case 1:
										//1일을 출력하기 전에 공백을 출력한다.
										int week = cal.get(Calendar.DAY_OF_WEEK);
										for (int blank = 1; blank < week; blank++) {
							%>
							<td class="blank">&nbsp;</td>
							<%
								} //end for
									}//end switch
							%>
							<td><%=tempDay%></td>
							<%
								//설정된 일자가 토요일이면 행을 변경한다.
									switch (cal.get(Calendar.DAY_OF_WEEK)) {
									case Calendar.SATURDAY:
							%>
							</tr>
							<tr>
								<%
									}
									} //end for​
								%>
							
						</tbody>
					</table>
				</div>​
			</div>
		</div>
	</div>
</body>
</html>