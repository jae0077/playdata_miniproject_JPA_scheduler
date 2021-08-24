package scheduler.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import scheduler.model.entity.Member;
import util.PublicCommon;

public class SchedulerDAO {
	private static SchedulerDAO instance = new SchedulerDAO();
	private SchedulerDAO() {}
	public static SchedulerDAO getInstance() {
		return instance;
	}
	

//	public class Scheduler {
//		@Id
//		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scheduler_idx_seq")
//		private int idx;
//		
//		@Column(name="start_date", nullable=false)
//		private Date startDate;
//		
//		@Column(name="end_date", nullable=false)
//		private Date endDate;
//		
//		@Column(name="category", nullable=false)
//		private String category;
//		
//		@Column(name="title", nullable=false)
//		private String title;
//		
//		@Column(name="info", nullable=true)
//		private String info;
//		
//		@Column(name="author", nullable=false)
//		private String author;
//		
//		@Column(name="created_date", nullable=false)
//		private Date createdDate;
//		
//		@OneToMany(mappedBy="schedulerIdx")
//		private List<Participant> participants;
	
	public boolean setSchedule(Date startDate, Date endDate, String category, String title, String info, String Author, Date createdDate) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Scehduler schedule = new Scheduler();
		
		boolean result = false;
		try {
			Member member = (Member)em.createNamedQuery("Member.findByLogin").setParameter("id", id).setParameter("pw", pw).getSingleResult();
			System.out.println(member);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	
	
}
