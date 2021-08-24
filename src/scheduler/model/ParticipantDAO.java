package scheduler.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public void setParticipant(Scheduler schedule, Member member) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Participant participant = null;
		
		tx.begin();
		
		try {
			participant = new Participant();
			
			participant.setSchedulerIdx(schedule);
			participant.setMemberIdx(member);
			
			em.persist(participant);
			tx.commit();
						
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	@Test
	public void m1() throws ParseException {
				
		EntityManager em = PublicCommon.getEntityManager();
		
		Member participant = null;
		
		Scheduler schedule = em.find(Scheduler.class, 2);
		participant = em.find(Member.class, 1);
		
		System.out.println(schedule);
		System.out.println(participant);
		
		
		setParticipant(schedule, participant);
	}
}
