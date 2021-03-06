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
	<c:if test="${withdrawResult eq true}">
		<div>
			${withrawMemberName}님이 탈퇴 처리 되었습니다.
		</div>
	</c:if>
	<c:if test="${withdrawResult eq false}">
		<div>
			탈퇴 처리에 실패 하였습니다.
		</div>
	</c:if>
	<div>
		<a href="<c:url value="memberListView.do"/>">관리자 페이지로 돌아가기</a>
	</div>	

<%@include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>