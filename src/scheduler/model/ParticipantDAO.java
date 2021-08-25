package scheduler.model;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import scheduler.model.entity.Member;
import scheduler.model.entity.Participant;
import scheduler.model.entity.Scheduler;
import util.PublicCommon;

public class ParticipantDAO {
	private static ParticipantDAO instance = new ParticipantDAO();
	private ParticipantDAO() {}
	public static ParticipantDAO getInstance() {
		return instance;
	}
	
	// 참여자 추가
	public boolean setParticipant(Scheduler schedule, Member member) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		boolean result = false;
		
		Participant participant = null;
		
		tx.begin();
		
		try {
			participant = new Participant();
			
			participant.setSchedulerIdx(schedule);
			participant.setMemberIdx(member);
			
			em.persist(participant);
			tx.commit();
			
			result = true;
						
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	public boolean deleteParticipant(Scheduler schedule, Member member) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Participant participant = null;
		
		boolean result = false;
				
		tx.begin();
		
		try {
			participant = new Participant();
			int flag = em.createNamedQuery("Participant.deleteParticipant").setParameter("sId", schedule).setParameter("mId", member).executeUpdate();
			
			tx.commit();
			
			if(flag == 1) {
				result = true;
			}
						
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	@Test
	public void m1() throws ParseException {
				
		EntityManager em = PublicCommon.getEntityManager();
		
		Member participant = null;
		
		Scheduler schedule = em.find(Scheduler.class, 2);
		participant = em.find(Member.class, 2);
		
		System.out.println(schedule);
		System.out.println(participant);
		
//		setParticipant(schedule, participant);
//		deleteParticipant(schedule, participant);
	}
	
}
