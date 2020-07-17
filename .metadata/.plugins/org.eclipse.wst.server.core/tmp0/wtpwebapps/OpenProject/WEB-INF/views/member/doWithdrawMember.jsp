<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenProject</title>
<script type="text/css" src="<c:url value="css/default.css"/>"></script>
<script type="text/javascript" src="<c:url value="js/default.js"/>"></script>
</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<h1>Index Page</h1>
<hr>
<form action="memberWithdrawResult.do" method="post">
	<div>
		${dowithrawmember.uname}님을 탈퇴 처리 하시겠습니까?
	</div>
	<div>
		<input type="hidden" name="idx" value="${dowithrawmember.idx}">
		<input type="hidden" name="uid" value="${dowithrawmember.uid}">
		<input type="hidden" name="upw" value="${dowithrawmember.upw}">
		<input type="hidden" name="uname" value="${dowithrawmember.uname}">
		<input type="submit" value="탈퇴처리하기">
		<input type="button" value="돌아가기">
	</div>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>