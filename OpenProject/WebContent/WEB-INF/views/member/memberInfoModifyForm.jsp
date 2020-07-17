<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회 원 가 입</title>
</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<div>
	<h1 class="subtitle">회 원 정 보 수 정</h1>
	<hr>
	<form id="regForm" action="memberInfoModifyResult.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>아이디(EMAIL)</td>
				<td><input type="email" id="uid" name="uid" value="${modMember.uid}" disabled="disabled"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="upw" value="${modMember.upw}" required="required" disabled="disabled">
					<br>
					<input type="checkbox" id="isPasswordModify"> 비밀번호 수정을 원하시면 체크해주세요.
				</td>
			</tr>
			<tr>
				<td>이  름 (닉네임)</td>
				<td><input type="text" name="uname" value="${modMember.uname}" required="required"></td>
			</tr>
			<tr>
				<td>사  진</td>
				<td><input type="file" name="uphoto" value="${modMember.uphoto}"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" id="submit" value="정보수정">
					<input type="button" id="return" value="돌아가기">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>