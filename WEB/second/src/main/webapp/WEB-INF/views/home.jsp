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
<style type="text/css">
.bgimage{
background-image: url(${pageContext.request.contextPath}/resources/images/knuplan-main.png);
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
</head>
<body>
	<c:if test="${param.success eq false}">
	</c:if>
	<!-- Modal -->
	<div id="loginFailModal" class="modal fade">
		<div class="modal-dialog modal-vertical-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span> <span class="sr-only">close</span>
					</button>
					<h4 id="modalTitle" class="modal-title">로그인 실패</h4>
					<input id="modalNewTitle" type="text" class="form-control"
						name="new_title" style="display: none"
						placeholder="새로운 일정을 입력하세요" required>
				</div>
				<div class="modal-footer">
					<input id="modalTitleData" type="hidden" class="form-control"
						name="title"> <input id="modalStart" type="hidden"
						class="form-control" name="start"> <input id="modalEnd"
						type="hidden" class="form-control" name="end">
					<button type="submit" id="modalButton" class="btn btn-default">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	
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