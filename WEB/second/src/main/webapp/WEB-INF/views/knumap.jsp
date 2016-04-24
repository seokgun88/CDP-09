<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>KNU PLAN</title>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		
	<title>OpenAPI 2.0 - 지도 생성</title>
	<!-- prevent IE6 flickering -->
	<script type="text/javascript">
	try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
	</script>

<script type="text/javascript" src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=DelRqlwXcLGUB3dteF2y"></script>
</head>
    
<body>
	
	<div class="container">
		<a href="KNUPLAN_HOME.html">
			<img src="resources/KNUPLAN.png" class="img-rounded" alt="Cinque Terre">
		</a>
    
            	<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
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
		
		<div id = "testMap" style="border:1px solid #000; width:500px; height:400px; margin:20px;"></div>
			
	</div>
	<table cellpadding="0" cellspacing="0" width="536"> <tr> 
	
	<script type="text/javascript">
		var oPoint = new nhn.api.map.LatLng(35.8900362,128.610107);
		nhn.api.map.setDefaultPoint('LatLng');
		oMap = new nhn.api.map.Map('testMap' ,{
					point : oPoint,
					zoom : 11,
					enableWheelZoom : true,
					enableDragPan : true,
					enableDblClickZoom : false,
					mapMode : 0,
					activateTrafficMap : false,
					activateBicycleMap : false,
					minMaxLevel : [ 1, 14 ],
					size : new nhn.api.map.Size(1100, 600)
				});
	</script>
</body>
</html>
