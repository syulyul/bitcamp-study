<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원 목록</h1>
<div style='margin:5px;'>
<a href='/member/form.jsp'>새 회원</a>
</div>
<table border='1'>
  <tr><th>번호</th> <th>이름</th> <th>전화번호</th></tr>
</thead>
<tbody>
<jsp:useBean id="memberDao" type="bitcamp.report.dao.MemberDao" scope="application"/>
<c:set var="list" value="${memberDao.findAll()}" scope="page"/>
<c:forEach items="${list}" var="member">
    <tr>
    <td>${member.no}</td>
    <td>
        <img src='http://urnfabxxeceu19010753.cdn.ntruss.com/member/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
        <a href='/member/detail.jsp?no=<%=m.getNo()%>'>${member.name}</a></td>
    <td>${member.phone}</td></tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>
