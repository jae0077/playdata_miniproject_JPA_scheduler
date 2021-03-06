package scheduler.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="participant")
@NamedQuery(query="delete from participant p where p.schedulerIdx =:sId and p.memberIdx =:mId",name="Participant.deleteParticipant")
@NamedQuery(query="select p from participant p where p.memberIdx = (select m.idx from member m where m.id=:id)", name="Participant.findByParticipant")
@SequenceGenerator(name="participant_idx_seq", sequenceName="participant_idx_seq", initialValue=1, allocationSize=1)
public class Participant {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="participant_idx_seq")
	@Column(name="participant_idx")
	private int idx;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="scheduler_idx")
	private Scheduler schedulerIdx;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="member_idx")
	private Member memberIdx;
}
