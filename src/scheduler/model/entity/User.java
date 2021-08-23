package scheduler.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name="user_idx_seq", sequenceName="user_idx_seq", initialValue=1)
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_idx_seq")
	private int idx;
	
	@Column(name="id", length=15, nullable=false, unique=true)
	private String id;
	
	@Column(name="pw", length=20, nullable=false)
	private String pw;
	
	@Column(name="name", length=20, nullable=false)
	private String name;
	
	@Column(name="phone", length=11, nullable=false)
	private String phone;
}
