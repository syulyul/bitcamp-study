<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"%>

<%@ page import="java.io.IOException"%>
<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");

    Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>
<%
    if (member == null) {
%>
  <p>해당 번호의 회원이 없습니다!</p>
<%
    } else {
%>
  <form action='/member/update.jsp' method='post' enctype='multipart/form-data'>
  <table border='1'>
<tr><th style='width:120px;'>사진</th>
<td style='width:300px;'><%=
        member.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>" :
            String.format("<a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-25/member/%s'>"
            + "<img src='http://urnfabxxeceu19010753.cdn.ntruss.com/member/%1$s?type=f&w=60&h=80&faceopt=true&ttype=jpg'>"
            + "</a>", member.getPhoto())%>
            <input type='file' name='photo'></td></tr>

<tr>
    <th style='width:120px;'>번호</th>
    <td style='width:300px;'><input type='text' name='no' value='<%=member.getNo()%>' readonly></td></tr>
<tr>
    <th>이름</th>
    <td><input type='text' name='name' value='<%=member.getName()%>'></td></tr>
<tr>
    <th>전화번호</th>
    <td><input type='tel' name='phone' value='<%=member.getPhone()%>'></td></tr>
<tr>
    <th>암호</th>
    <td><input type='password' name='password'></td></tr>
<tr>
    <th>직책</th>
    <td><select name='position'>
        <option value='0' <%=member.getPosition() == '0' ? "selected" : ""%>>관리자</option>
        <option value='1' <%=member.getPosition() == '1' ? "selected" : ""%>>일반직원</option></select></td></tr>
  </table>

  <div>
  <button>변경</button>
  <button type='reset'>초기화</button>
        <a href='/member/delete.jsp?no=<%=member.getNo()%>'>삭제</a>
  <a href='/member/list.jsp'>목록</a>
  </div>
  </form>
<%
    }
%>

<jsp:include page="../footer.jsp"/>

</body>
</html>