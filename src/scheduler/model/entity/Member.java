package scheduler.model.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="member")
@SequenceGenerator(name="member_idx_seq", sequenceName="member_idx_seq", initialValue=1, allocationSize=1)
@NamedQuery(query="select m from member m where m.id=:id and m.pw=:pw",name="Member.findByLogin")
@NamedQuery(query="select m from member m where m.id=:id",name="Member.findById")
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_idx_seq")
	private int idx;
	
	@Column(name="id", length=15, nullable=false, unique=true)
	private String id;
	
	@Column(name="pw", length=20, nullable=false)
	private String pw;
	
	@Column(name="name", length=20, nullable=false)
	private String name;
	
	@Column(name="phone", length=11, nullable=false)
	private String phone;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="memberIdx")
	private List<Participant> participants;
}
