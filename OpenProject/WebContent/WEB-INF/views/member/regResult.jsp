<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 결과</title>
</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<div>
	<h1 class="subtitle">회 원 가 입 결 과</h1>
	<hr>
	<h3>
		<c:if test="${result gt 0 && member ne null} ">
			<div>
				회원 가입 완료, 환영합니다! ${member.uid} 님!
			</div>
		</c:if>
		<c:if test="${not (result gt 0 && member ne null)} ">
			<div>
				회원 가입에 실패하였습니다.
				관리자에게 문의해주세요.
			</div>
		</c:if>
	</h3>
</div>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/css" src="<c:url value="/css/default.css" />"></script>
<script type="text/javascript" src="<c:url value="/js/default.js" />"></script>
</html>