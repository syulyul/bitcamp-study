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

<h1>회원</h1>
<form action='/member/add.jsp' method='post' enctype='multipart/form-data'>
<table border='1'>
<tr>
    <th>이름</th> <td style='width:200px;'><input type='text' name='name'></td>
</tr>
<tr>
    <th>전화번호</th> <td><input type='tel' name='phone'></td>
</tr>
<tr>
    <th>암호</th> <td><input type='password' name='password'></td>
</tr>
<tr>
    <th>직책</th>
<td>
    <select name='position'>
        <option value='0'>관리자</option>
        <option value='1'>일반직원</option>
    </select>
</td>
</tr>
<tr>
    <th>사진</th> <td><input type='file' name='photo'></td>
</tr>
</table>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>

