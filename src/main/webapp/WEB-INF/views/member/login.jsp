<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<form action="login" method="POST">
    <label for="memberId">아이디:</label>
    <input type="text" id="memberId" name="memberId"> 
    <br>
    <label for="memberPw">비밀번호:</label> 
    <input type="password" id="memberPw" name="memberPw"> 
    <br>
    <input type="submit" value="로그인">
    
    
    <c:if test="${not empty param.targetURL}">
    <input type="hidden" id="targetURL" name="targetURL" value="${param.targetURL}">
    </c:if>
</form>

<form action="searchId" method="get">
	<input type="submit" value="아이디 찾기">
</form>

<form action="searchPw" method="get">
	<input type="submit" value="비밀번호 찾기">
</form>

	<input type="hidden" name="searchResult" id="searchResult" value="${searchResult }">
	<input type="hidden" name="searchId" id="searchId" value="${searchId }">
	<input type="hidden" name="searchPw" id="searchPw" value="${searchPw }">
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchId = $('#searchId').val();
		var searchPw = $('#searchPw').val();
		console.log('searchResult = ' + searchResult);
		console.log('searchId = ' + searchId);
		console.log('searchPw = ' + searchPw);
		if(searchResult == 'idSearch') {
			alert('회원님의 아이디는   ' + searchId + '    입니다');
		} else if(searchResult == 'pwSearch') {
			alert('회원님의 비밀번호는   ' + searchPw + '    입니다');
		}
		
	</script>

</body>
</html>