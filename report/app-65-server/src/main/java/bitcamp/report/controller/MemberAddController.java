package bitcamp.report.controller;

import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.NcpObjectStorageService;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/member/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class MemberAddController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    request.getRequestDispatcher("/member/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    try {
      Member m = new Member();
      m.setName(request.getParameter("name"));
      m.setPhone(request.getParameter("phone"));
      m.setPassword(request.getParameter("password"));
      m.setPosition(request.getParameter("position").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-25",
            "member/", photoPart);
        m.setPhoto(uploadFileUrl);
      }


        memberDao.insert(m);
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw new ServletException(e);
    }
  }
}