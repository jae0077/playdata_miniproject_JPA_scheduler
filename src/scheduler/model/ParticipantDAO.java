package scheduler.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
	
	// 참여자 삭제
	public boolean deleteParticipant(Scheduler schedule, Member member) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		boolean result = false;
				
		tx.begin();
		
		try {
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
}
