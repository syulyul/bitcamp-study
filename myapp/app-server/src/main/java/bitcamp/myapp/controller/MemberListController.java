package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/list")
public class MemberListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");

    List<Member> list = memberDao.findAll();
    request.setAttribute("list", list);

    request.getRequestDispatcher("/member/list.jsp").include(request, response);

  }

}
