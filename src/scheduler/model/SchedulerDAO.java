package scheduler.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import scheduler.model.dto.SchedulerDTO;
import scheduler.model.entity.Scheduler;
import util.PublicCommon;

public class SchedulerDAO {
	private static SchedulerDAO instance = new SchedulerDAO();
	private SchedulerDAO() {}
	public static SchedulerDAO getInstance() {
		return instance;
	}
	
	public Scheduler setSchedule(Date startDate, Date endDate, String category, String title, String info, String author) {
		
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
			em.createNamedQuery("Scheduler.deleteByschedule").setParameter("idx", idx).setParameter("author", author).executeUpdate();
			
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
	
	@Test
	public void m1() throws ParseException {
		
		String startDate = "2013-04-08 10:10:00";
		String endDate = "2013-04-08 10:10:00";

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
//		setSchedule(transFormat.parse(startDate), transFormat.parse(endDate),"test2", "test", "test", "test3");
//		setSchedule(transFormat.parse(startDate), transFormat.parse(endDate),"test2", "test", "test", "test2");
//		setSchedule(transFormat.parse(startDate), transFormat.parse(endDate),"test2", "test", "test", "test");
		boolean test = deleteScheduler(5, "test");
		System.out.println(test);
//		Date to = transFormat.parse(startDate);
//		System.out.println(to);

	}	
}
