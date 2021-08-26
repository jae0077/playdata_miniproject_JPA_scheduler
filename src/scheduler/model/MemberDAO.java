package scheduler.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import scheduler.model.dto.MemberDTO;
import scheduler.model.entity.Member;
import util.PublicCommon;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return instance;
	}

	// 회원가입
	public boolean memberRegister(MemberDTO user) {
		EntityManager em = PublicCommon.getEntityManager();	
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		boolean result = false;
		
		try {
			Member member = new Member();
			member.setId(user.getId());
			member.setPw(user.getPw());
			member.setName(user.getName());
			member.setPhone(user.getPhone());
			
			em.persist(member);
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
		return result;
	} 
	
	// 로그인
	public Member login(String id, String pw) {
		EntityManager em = PublicCommon.getEntityManager();	
		Member member = null;
		
		try {
			member = (Member)em.createNamedQuery("Member.findByLogin").setParameter("id", id).setParameter("pw", pw).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return member;
	}
	
	// 아이디 조회
	public Member searchById(String id) {
		EntityManager em = PublicCommon.getEntityManager();
		
		Member member = null;
		
		try {
			member = new Member();
			member = (Member)em.createNamedQuery("Member.findById").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return member;
	}
}
