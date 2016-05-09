<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
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
		<div class="row">
			<div class="col-xs-12 col-sm-10">
				<a href="home"> <img src="resources/KNUPLAN.png"
					class="img-responsive" alt="Knu Plan">
				</a>
			</div>
			<div class="hidden-xs col-sm-2">
				<div style="float: right; margin: 25px 0 0 0;">
					<script id="_wauppr">
						var _wau = _wau || [];
						_wau.push([ "classic", "y6zuzfup1c67", "ppr" ]);
						(function() {
							var s = document.createElement("script");
							s.async = true;
							s.src = "http://widgets.amung.us/classic.js";
							document.getElementsByTagName("head")[0]
									.appendChild(s);
						})();
					</script>
				</div>
			</div>
		</div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">KNU PLAN</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</nav>

		<form class="form-horizontal" role="form" action="login" method="post">
			<div class="row">
				<div class="col-md-4"></div>

				<div class="col-md-4">
					<div class="well">
						<div class="form-group">
							<h1 align="center">LOGIN</h1>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input type="text" class="form-control" name="id"
									placeholder="학교 계정 아이디를 입력해주세요" required autofocus>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input type="password" class="form-control" name="pwd"
									placeholder="비밀번호를 입력해주세요" required> <label
									class="login-field-icon fui-lock" for="login-pass"></label>
							</div>
						</div>
<!-- 
						<div class="form-group">
							<div class="col-md-12">
								<div class="checkbox">
									<label><input type="checkbox"> 로그인 상태 유지</label>
								</div>
							</div>
						</div> -->
						<div class="form-group">
							<div class="col-md-12">
								<button type="submit" class="btn btn-danger btn-block btn-lg">로그인</button>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-4"></div>
			</div>
		</form>
	</div>
</body>
</html>