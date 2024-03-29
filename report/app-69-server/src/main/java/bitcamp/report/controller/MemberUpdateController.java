package bitcamp.report.controller;

import bitcamp.report.dao.MemberDao;
import bitcamp.report.service.NcpObjectStorageService;
import bitcamp.report.vo.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/member/update")
public class MemberUpdateController implements PageController {

  MemberDao memberDao;
  PlatformTransactionManager txManager;
  NcpObjectStorageService ncpObjectStorageService;

  public MemberUpdateController(MemberDao memberDao, PlatformTransactionManager txManager, NcpObjectStorageService ncpObjectStorageService) {
    this.memberDao = memberDao;
    this.txManager = txManager;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setPhone(request.getParameter("phone"));
      member.setPassword(request.getParameter("password"));
      member.setPosition(request.getParameter("position").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService
            .uploadFile("bitcamp-nc7-bucket-25", "member/", photoPart);
        member.setPhoto(uploadFileUrl);
      }


      if (memberDao.update(member) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        txManager.commit(status);
        return "redirect:list";
      }

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }

}
