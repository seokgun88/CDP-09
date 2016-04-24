<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType = "text/html;charset=utf-8" %>
<html>
<head>
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Home</title>
</head>
<body>
<div class="container">
    <img src="resources/KNUPLAN.png" class="img-rounded" alt="Cinque Terre">

    <div class="panel panel-default">
        <button type="button" class="btn btn-danger">시 간 표</button>
        <button type="button" class="btn btn-danger">빈강의실</button>
    </div>
    
    <div class="panel panel-danger">
        <div class="panel-heading">로그인</div>
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="login" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="ID">아이디:</label>
                <div class="col-sm-10">
                    <input type="ID" class="form-control" name="id" placeholder="아이디를 입력해주세요">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">비밀번호:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력해주세요">
                </div>
            </div>
            <div class="form-group">
                <div class="com-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox"> 로그인 상태 유지</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">로그인</button>
                    <%--<a href = "timetable" class="btn btn-default">로그인</a>--%>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>