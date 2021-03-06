<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 보기</title>
</head>
<body>
	<h1>회원목록 조회하기.</h1>
	<hr>
	<c:if test="${memberList eq null}">
		<h3>회원 정보가 존재하지 않습니다, 혹은 정보를 가져오는데 실패하였습니다.</h3>
	</c:if>
	<c:if test="${memberList ne null}">
		<table>
			<tr>
				<th>순번</th>
				<th>아이디(이메일)</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>가입일</th>
				<th>프로필 사진</th>
				<th>관리기능</th>
			</tr>
			<tbody>
				<c:forEach items="${memberList}" var="mList">
					<tr>
						<td>${mList.idx}</td>
						<td>${mList.uid}</td>
						<td>${mList.upw}</td>
						<td>${mList.uname}</td>
						<td>${mList.regdate}</td>
						<td><img src="<c:url value="${mList.uphoto}"/>" width="100px" height="75px" ></img></td>
						<td>
							<a href="memberInfoModify.do?manageIdx=${mList.idx}">회원정보 수정</a>
							<br>
							<a href="memberWithdraw.do?manageIdx=${mList.idx}">회원 탈퇴</a>
						</td>
					</tr>					
				</c:forEach>
			</tbody>
		</table>
		<c:forEach begin="1" end="${viewPageList.totalCount}" var="num">
			<a href="memberListView.do?pNum=${num}">${num}</a>
		</c:forEach>
	</c:if>
</body>
</html>