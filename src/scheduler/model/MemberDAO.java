package scheduler.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import scheduler.model.entity.Member;
import util.PublicCommon;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return instance;
	}

	public static boolean memberRegister(String id, String pw, String name, String phone) {
		EntityManager em = PublicCommon.getEntityManager();	
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		boolean result = false;
		try {
			Member member = new Member();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setPhone(phone);
			
			System.out.println(1);
			em.persist(member);
			System.out.println(2);
			tx.commit();
			System.out.println(3);
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
	
	@Test
	public void test() {
		boolean test = memberRegister("test", "testpw", "test", "01012345678");
		
		System.out.println(test);
	}
}
