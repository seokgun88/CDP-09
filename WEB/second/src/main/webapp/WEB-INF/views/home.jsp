<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Home</title>
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
					<li><a href="fullcalendar">일정</a>
					<li><a href="timetable">시간표</a></li>
					<li><a href="knumap">빈강의실</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</nav>

		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>
		<div class="row">
			<p></p>
		</div>

		<form class="form-horizontal" role="form" action="login" method="post">
			<div class="row">
				<div class="col-md-4"></div>

				<div class="col-md-6">
					<div class="panel-heading">
						<h1>LOGIN</h1>
					</div>
					<div class="row">
						<p></p>
					</div>
					<div class="row">
						<p></p>
					</div>

					<div class="form-horizontal" role="form">
						<div class="col-md-2">
							<label class="control-label col-md-2" for="ID">ID:</label>
						</div>

						<div class="col-md-7">
							<input type="ID" class="form-control" name="id"
								placeholder="아이디를 입력해주세요">
						</div>

					</div>

				</div>
			</div>
			<div class="row">
				<P></P>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<div class="col-md-6">
					<div class="col-md-2">
						<label class="control-label col-md-2" for="pwd">Password:</label>
					</div>

					<div class="col-md-7">
						<input type="password" class="form-control" name="pwd"
							placeholder="비밀번호를 입력해주세요">
					</div>

					<div class="form-group">
						<div class="com-md-offset-2 col-md-10">
							<div class="checkbox">
								<label><input type="checkbox"> 로그인 상태 유지</label>
							</div>
						</div>
					</div>

					<div class="row">
						<p></p>
					</div>
					<div class="row">
						<p></p>
					</div>
					<div class="row">
						<p></p>
					</div>
                    
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-default btn-lg">로그인</button>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-default btn-lg">ID찾기</button>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-default btn-lg">PW찾기</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </form>
    </div>
</body>
</html>