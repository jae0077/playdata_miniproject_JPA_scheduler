package scheduler.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;

import org.junit.jupiter.api.Test;

import scheduler.model.entity.Scheduler;
import util.PublicCommon;

public class SchedulerDAO {
	private static SchedulerDAO instance = new SchedulerDAO();
	private SchedulerDAO() {}
	public static SchedulerDAO getInstance() {
		return instance;
	}
	
	public static Scheduler setSchedule(Date startDate, Date endDate, String category, String title, String info, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
				
		Scheduler schedule = null;
		
		tx.begin();
		
		try {
			schedule = new Scheduler();
			
			schedule.setStartDate(startDate);
			schedule.setEndDate(endDate);
			schedule.setCategory(category);
			schedule.setTitle(title);
			schedule.setInfo(info);
			schedule.setAuthor(author);
			schedule.setCreatedDate(new Date());
			
			em.persist(schedule);
			System.out.println("persist");
			tx.commit();
			System.out.println("commit");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return schedule;
	}
	
	// endDate, 설정 안할 시 startDate와 같은 날짜 set
	public Scheduler setSchedule(Date startDate, String category, String title, String info, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Scheduler schedule = null;
		
		tx.begin();
		
		try {
			schedule = new Scheduler();
			
			schedule.setStartDate(startDate);
			schedule.setEndDate(startDate);
			schedule.setCategory(category);
			schedule.setTitle(title);
			schedule.setInfo(info);
			schedule.setAuthor(author);
			schedule.setCreatedDate(new Date());
			
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
	
//	@Test
	public void m1() throws ParseException {
		
		String startDate = "2013-04-08 10:10:00";
		String endDate = "2013-04-08 10:10:00";

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		setSchedule(transFormat.parse(startDate), transFormat.parse(endDate),"test2", "test", "test", "info");
		
		
//		Date to = transFormat.parse(startDate);
//		System.out.println(to);

	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	@Test
	//본인의 일정만(로그인)전체조회 
	public static List getSchedulerAll(String author) {
		EntityManager em = PublicCommon.getEntityManager();
		
		List all = em.createNamedQuery("Scheduler.findByAll").setParameter("author", author).getResultList();
		
		em.close();
		em = null;
		
		return all;
	}
		
	@Test
	//특정일정조회 - 카테고리로 조회
	public static Scheduler getSchedulerOne(String category, String author) {
		EntityManager em = PublicCommon.getEntityManager();
		
		Scheduler sc = (Scheduler)em.createNamedQuery("Scheduler.findByCategory").setParameter("category", category).setParameter("author", author).getSingleResult();
		
		em.close();
		em = null;
		
		return sc;

	}
	
	//@Test
	//특정일정조회 - 날짜로 조회
	public static  Scheduler getSchedulerDate(String startDate, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();

		Scheduler sc = (Scheduler)em.createNamedQuery("Scheduler.findByDate").setParameter("startDate", startDate).setParameter("author", author).getSingleResult();
		
		em.close();
		em = null;
		
		return sc;
	}
	
	
	@Test
	//특정일정조회 - 일정 제목으로 조회
	public static Scheduler getSchedulerTitle(String title, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		
		Scheduler sc = (Scheduler)em.createNamedQuery("Scheduler.findByTitle").setParameter("title", title).setParameter("author", author).getSingleResult();
		
		em.close();
		em = null;
		
		return sc;
	}
	
	
	@Test
	//특정일정조회 - 참여자로 조회
	public static Scheduler getSchedulerParticipant(String id, String author) {
		
		EntityManager em = PublicCommon.getEntityManager();
		
		Scheduler sc = (Scheduler)em.createNamedQuery("Scheduler.findByParticipant").setParameter("id", id).setParameter("author", author).getSingleResult();

		em.close();
		em = null;
		
		return sc;
	}
	
	
}