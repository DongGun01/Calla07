package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	
	// @Autowired ������ �����Ѱ��� �޼���ȿ��� MemberDAO.login(select, searchId, ...) ����� �� �ִ°��� 
	// ex) ������ ������ ������ ��� : �� �޼���ȿ��� MemberDAO memberDAO = new MemberDAO(); ��ü�� �����ؾ� ��������
	@Autowired 
	private MemberDAO MemberDAO;
	
	
	@Override
	public String login(String memberId, String memberPw) {
		logger.info("login()  memberId : " + memberId);
		return MemberDAO.login(memberId, memberPw);
	}
	
	@Override
	public MemberVO read(String memberId) {
		logger.info("read(memberId)  memberId : " + memberId);
		return MemberDAO.select(memberId);
	}
	
	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId()  memberName : " + memberName);
		logger.info("searchId()  memberEmail : " + memberEmail);
		return MemberDAO.searchId(memberName, memberEmail);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw()  memberId : " + memberId);
		logger.info("searchPw()  memberPhone : " + memberPhone);
		return MemberDAO.searchPw(memberId, memberPhone);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete() ");
		return MemberDAO.delete(memberId);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() ");
		return MemberDAO.select();
	}
	
	@Override
	public int create(MemberVO vo) {
		logger.info("create() " + vo.toString());
		int result = 0;
		try {
			return result = MemberDAO.insert(vo);
		} catch (DataIntegrityViolationException e) { // �����ͺ��̽����� ���Ἲ ���� ������ ����� �� �߻��ϴ� ���� Data integrity constraint violation during member registration.
			throw new IllegalStateException("ȸ�� ���� �߿� ������ ���Ἲ ���� ���� ����."); // ȸ�� ���� �߿� ������ ���Ἲ ���� ���� ����: �ߺ��� ����� �̸� �Ǵ� �̸����Դϴ�. 
			
		}
	}

	@Override
	public int checkId(String memberId) {
		int result = 0;
		result = MemberDAO.checkId(memberId);
		return result;
	}

	@Override
	public int checkNick(String memberNickname) { //
		logger.info("checkNick() : memberNickname = " + memberNickname);
		return MemberDAO.checkNickname(memberNickname);
	}
	
	@Override
	public int update(String memberId, String newData, String category) {
		logger.info("update() memberId : " + memberId + "newData = " + newData + "category = " + category);
		return MemberDAO.update(memberId, newData, category);
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("update()  memberPw : " + memberPw);
		return MemberDAO.updatePw(memberId, memberPw);
	}
	
	@Override
	public int updateLevel(String memberId, int amount) {
		return MemberDAO.updateLevel(memberId, amount);
	}

	@Override
	public int deleteUProduct(int uProductId) {
		logger.info("deleteUProduct() ");
		return MemberDAO.deleteUProduct(uProductId);
	}




	/*
	 * @Override public void registMailAuthentication(String memberEmail, String
	 * authenticationKey) { logger.info("registMailAuthentication() ȣ��");
	 * logger.info("memberEmail" + memberEmail + ", authenticationKey : " +
	 * authenticationKey); MemberDAO.insertMailAuthentication(memberEmail,
	 * authenticationKey);
	 * 
	 * }
	 * 
	 * @Override public int mailAuthenticationConfirm(MailVO mailAuth) {
	 * logger.info("mailAuthenticationConfirm() ȣ��"); return null; }
	 */

}
