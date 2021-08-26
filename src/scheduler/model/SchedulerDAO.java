package scheduler.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import scheduler.model.dto.SchedulerDTO;
import scheduler.model.entity.Participant;
import scheduler.model.entity.Scheduler;
import util.PublicCommon;

public class SchedulerDAO {
	private static SchedulerDAO instance = new SchedulerDAO();
	private SchedulerDAO() {}
	public static SchedulerDAO getInstance() {
		return instance;
	}
	
	// 스케줄 등록
	public Scheduler setSchedule(SchedulerDTO schedulerDTO) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
				
		Scheduler schedule = null;
		
		tx.begin();
		
		try {
			schedule = new Scheduler();
			
			schedule.setStartDate(schedulerDTO.getStartDate());
			schedule.setEndDate(schedulerDTO.getEndDate());
			schedule.setCategory(schedulerDTO.getCategory());
			schedule.setTitle(schedulerDTO.getTitle());
			schedule.setInfo(schedulerDTO.getInfo());
			schedule.setAuthor(schedulerDTO.getAuthor());
			schedule.setCreatedDate(new Date());
			
			em.persist(schedule);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return schedule;
	}
	
	
  // 아이디 찾기
	public Scheduler searchByIdx(int idx) {
		
		EntityManager em = PublicCommon.getEntityManager();
		Scheduler schedule = null;
		
		schedule = (Scheduler) em.createNamedQuery("Scheduler.searchByIdx").setParameter("idx", idx).getSingleResult();
		
		em.close();
		em = null;
		
		return schedule;
	}
  
	// 스케줄 수정
	public Boolean updateScheduler(int idx, SchedulerDTO schedulerDTO) {
		
		boolean result = false;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
				
		tx.begin();

		try {
			Scheduler schedule = em.find(Scheduler.class, idx);
			if (schedulerDTO.getAuthor().equals(schedule.getAuthor())) {
				if (schedulerDTO.getStartDate()!= null) {
					schedule.setStartDate(schedulerDTO.getStartDate());			
				}
				
				if (schedulerDTO.getEndDate() != null) {
					schedule.setEndDate(schedulerDTO.getEndDate());
				}
				
				if (schedulerDTO.getCategory()!= null) {
					schedule.setCategory(schedulerDTO.getCategory());				
				}
				if (schedulerDTO.getTitle()!= null) {
					schedule.setTitle(schedulerDTO.getTitle());
				}
				if (schedulerDTO.getInfo()!= null) {
					schedule.setInfo(schedulerDTO.getInfo());	
				}
				tx.commit();
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
		
	// 스케줄 삭제
	public Boolean deleteScheduler(int idx, String author) {
		boolean result = false;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			int flag = em.createNamedQuery("Scheduler.deleteByschedule").setParameter("idx", idx).setParameter("author", author).executeUpdate();
			
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
  
	// 본인의 일정만(로그인)전체조회 
	public List<Scheduler> getSchedulerAll(String author) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Scheduler> all = null;
		
		String jpql = "select s from scheduler s where s.author=:author";
		
		all = (List<Scheduler>) em.createQuery(jpql).setParameter("author", author).getResultList();
		
		em.close();
		em = null;
		
		return all;
	}

	// 특정일정조회 - 카테고리로 조회
	public List<Scheduler> getSchedulerCategory(String category, String author) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Scheduler> all = null;
		
		all = (List<Scheduler>)em.createNamedQuery("Scheduler.findByCategory").setParameter("category", category).setParameter("author", author).getResultList();
		
		em.close();
		em = null;
		
		return all;
	}
	
	// 특정일정조회 - 날짜로 조회
	public List<Scheduler> getSchedulerDate(Date startDate, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		List<Scheduler> all = null;
		
		all = (List<Scheduler>)em.createNamedQuery("Scheduler.findByDate").setParameter("startDate", startDate).setParameter("author", author).getResultList();
		
		em.close();
		em = null;
		
		return all;
	}
	
	// 특정일정조회 - 일정 제목으로 조회
	public List<Scheduler> getSchedulerTitle(String title, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		List<Scheduler> all = null;
		
		all = new ArrayList<Scheduler> ();
		
		all = (List<Scheduler>)em.createNamedQuery("Scheduler.findByTitle").setParameter("title", title).setParameter("author", author).getResultList();
		
		em.close();
		em = null;
		
		return all;
	}
	
	// 특정일정조회 - 참여자로 조회
	public List<Scheduler> getSchedulerParticipant(String id) {
		
		EntityManager em = PublicCommon.getEntityManager();

		List<Participant> p = null;
		List<Scheduler> all = new ArrayList<>();
		
		p = (List<Participant>)em.createNamedQuery("Participant.findByParticipant").setParameter("id", id).getResultList();
		
		for(Participant pSchedule : p) {
			all.add(pSchedule.getSchedulerIdx());
		}
		
		em.close();
		em = null;
		
		return all;
	}
}
