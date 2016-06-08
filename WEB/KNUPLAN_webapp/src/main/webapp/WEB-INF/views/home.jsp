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
<style type="text/css">
.bgimage{
background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-main.jpg);
background-size: 100%;
background-repeat: no-repeat;
background-position: bottom;
-webkit-background-size: cover;
-moz-background-size: cover;
background-size: cover;
-o-background-size: cover;
height: 100%;
}		
.modal-vertical-centered {
	transform: translate(0, 100%) !important;
	-ms-transform: translate(0, 100%) !important; /* IE 9 */
	-webkit-transform: translate(0, 100%) !important;
	/* Safari and Chrome */
}
</style>
<script type="text/javascript">
	var getParameter = function getParameter(param) {
		var returnValue;
		var url = location.href;
		var parameters = (url.slice(url.indexOf('?') + 1, url.length))
				.split('&');
		for (var i = 0; i < parameters.length; i++) {
			var varName = parameters[i].split('=')[0];
			if (varName.toUpperCase() == param.toUpperCase()) {
				returnValue = parameters[i].split('=')[1];
				return decodeURIComponent(returnValue);
			}
		}
	};
	$(window).load(function() {
		if (getParameter("success") == "false") {
			$('#loginFailModal').modal('show');
		}
	});

	document.oncontextmenu = function() { // 컨텍스트 메뉴금지
		return false;
	};
	history.go(1);
</script>
<link rel="icon" type="image/ico"  href="${pageContext.request.contextPath}/resources/images/hobanwoo.ico"/>
<title>KNU PLAN</title>
</head>
<body>
	<!-- Login Failed Modal -->
	<div id="loginFailModal" class="modal fade">
		<div class="modal-dialog modal-vertical-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span> <span class="sr-only">close</span>
					</button>
					<h4 id="modalTitle" class="modal-title">로그인 실패</h4>
				</div>
				<div id="modalBody" class="modal-body">YES 아이디와 비밀번호를 다시 입력해주세요.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid bgimage">
		
		<div style="height: 25%"></div>
		
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<img class="img-responsive center-block"
					src="${pageContext.request.contextPath}/resources/images/knuplan-title.png"
					alt="KNU PLAN">
			</div>
		</div>

		<div style="height: 10%"></div>
		
		<form class="form-horizontal" role="form" action="login" method="post">
			<div class="row">
				<div class="col-xs-2 col-sm-4"></div>

				<div class="col-xs-8 col-sm-4">
					<div class="form-group">
						<div class="col-md-12">
							<input type="text" class="form-control" name="id"
								placeholder="YES ID를 입력해주세요" required autofocus>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12">
							<input type="password" class="form-control" name="pwd"
								placeholder="YES PW를 입력해주세요" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<button type="submit" class="btn btn-default btn-block">로그인</button>
						</div>
					</div>
				</div>
			</div>
		</form>

	</div>
</body>
</html>