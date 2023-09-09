package bitcamp.report.controller;

import bitcamp.report.service.MemberService;
import bitcamp.report.service.NcpObjectStorageService;
import bitcamp.report.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Controller("/member/add")
public class MemberAddController implements PageController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/member/form.jsp";
    }

    try {
      Member m = new Member();
      m.setName(request.getParameter("name"));
      m.setPhone(request.getParameter("phone"));
      m.setPassword(request.getParameter("password"));
      m.setPosition(request.getParameter("position").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-25", "member/", photoPart);
        m.setPhoto(uploadFileUrl);
      }
      memberService.add(m);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
