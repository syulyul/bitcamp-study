<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>

<form action='login' method='post'>
<table border='1'>
<tr>
<th>전화번호</th> <td><input type='tel' name='phone' value='${cookie.phone.value}'></td>
</tr>
<tr>
<th>암호</th> <td><input type='password' name='password'></td>
</tr>
</table>
<button>로그인</button>
<input type='checkbox' name='savePhone' ${cookie.phone != null ? "checked" : ""}> 전화번호 저장
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>