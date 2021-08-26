package scheduler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberDTO {
	private String id;
	
	private String pw;
	
	private String name;
	
	private String phone;
}
