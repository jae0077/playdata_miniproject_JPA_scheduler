package scheduler.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@SequenceGenerator(name="scheduler_idx_seq", sequenceName="user_idx_seq", initialValue=1)
public class Scheduler {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_idx_seq")
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
	
	@OneToMany(mappedBy="schedulerIdx")
	private List<Participant>변수명;
	
	
}
