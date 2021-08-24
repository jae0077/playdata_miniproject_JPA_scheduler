package scheduler.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchedulerDTO {
	private Date startDate;
	
	private Date endDate;
	
	private String category;
	
	private String title;
	
	private String info;
	
	private String author;
	
}
