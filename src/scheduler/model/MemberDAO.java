package scheduler.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import scheduler.model.dto.MemberDTO;
import scheduler.model.entity.Member;
import util.PublicCommon;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return instance;
	}

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
	
//	@Test
	// 단위테스트용
	public void test() {
		
		System.out.println("--- 단위테스트 start ---");

		boolean register = memberRegister(new MemberDTO("hello", "hello", "hello", "01012345678"));

		System.out.println(register);
//		Member login = login("test", "testpw");
		
//		System.out.println(login);
		System.out.println("--- 단위테스트 end ---");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Member searchById(String id) {
		EntityManager em = PublicCommon.getEntityManager();
		
		Member member = null;
		
		try {
			member = new Member();
			member = (Member)em.createNamedQuery("Member.findById").setParameter("id", id).getSingleResult();
			System.out.println(member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return member;
	}
}
