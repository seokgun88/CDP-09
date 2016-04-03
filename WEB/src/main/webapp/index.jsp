<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>로그인</legend>
		<label for="id">E-mail&nbsp;</label>
		<input type="text" name="id" id="id" title="E-mail" size="22" value="user@knu.ac.kr" /><br>
		<label for="pass">비밀번호</label>
		<input type="password" name="pass" id="pass" title="비밀번호" size="20" value="password" /><br>
		<input type="checkbox" name="remember" id="remember" />
		<label for="remember"> 아이디저장</label>
		<input type="submit" value="로그인" />
	</fieldset>
</body>
</html>