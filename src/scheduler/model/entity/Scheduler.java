package scheduler.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(query="select s from scheduler s where s.author=:author", name="Scheduler.findByAll")
@NamedQuery(query="select s from scheduler s where s.category=:category and s.author=:author", name="Scheduler.findByCategory")
@NamedQuery(query="select s from scheduler s where s.title=:title and s.author=:author", name="Scheduler.findByTitle")
@NamedQuery(query="select s from scheduler s where s.startDate = :startDate and s.author=:author", name="Scheduler.findByDate")
@NamedQuery(query="delete from scheduler s where s.idx=:idx and s.author=:author",name="Scheduler.deleteByschedule")
@NamedQuery(query="select s from scheduler s where s.idx=:idx",name="Scheduler.searchByIdx")
@Entity(name="scheduler")
@SequenceGenerator(name="scheduler_idx_seq", sequenceName="scheduler_idx_seq", initialValue=1, allocationSize=1)
public class Scheduler {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scheduler_idx_seq")
	private int idx;
	
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Column(name="end_date", nullable=false)
	private Date endDate;
	
	@Column(name="category", nullable=false)
	private String category;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="info", nullable=true)
	private String info;
	
	@Column(name="author", nullable=false)
	private String author;
																
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@OneToMany(fetch=FetchType.EAGER,mappedBy="schedulerIdx")
	private List<Participant> participants;
	
	public int getParticipantCount() {
		return participants.size();
	}
}
