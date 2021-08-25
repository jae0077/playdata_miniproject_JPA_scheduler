package scheduler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParticipantDTO {
	private SchedulerDTO schedulerIdx;
	
	private MemberDTO memberIdx;
}
