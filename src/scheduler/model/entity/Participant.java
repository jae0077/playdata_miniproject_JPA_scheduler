package scheduler.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name="participant_idx_seq", sequenceName="participant_idx_seq", initialValue=1)
public class Participant {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="participant_idx_seq")
	@Column(name="participant_idx")
	private int idx;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduler_idx")
	private Scheduler schedulerIdx;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_idx")
	private User userIdx;
}
