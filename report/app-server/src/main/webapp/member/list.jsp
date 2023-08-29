<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>

<%@ page import="java.io.IOException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>

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
<%
    MemberDao memberDao = (MemberDao) application.getAttribute("memberDao");

    List<Member> list = memberDao.findAll();
%>
<tbody>
<%
    for (Member m : list) {
%>
<tr>
<td><%=m.getNo()%></td>
<td>
<img src='http://urnfabxxeceu19010753.cdn.ntruss.com/member/<%=m.getPhoto()%>?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
<a href='/member/detail.jsp?no=<%=m.getNo()%>'><%=m.getName()%></a></td>
 <td><%=m.getPhone()%></td></tr>
<%
    }
%>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>
