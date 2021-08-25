package scheduler.view;

import java.text.SimpleDateFormat;
import java.util.List;

import scheduler.model.entity.Scheduler;

public class EndView {

	// DML 성공시 출력
	public static void successView(String message) {
		System.out.println(message);
	}
	
	// 에러 출력
	public static void failView(String message) {
		System.out.println(message);
	}
	
	// 모든 일정 출력
	public static void scheduleListAll(List<Scheduler> schedulerAll) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		schedulerAll.forEach(v -> {
				System.out.println("================idx : " + v.getIdx() + "===========================");
				System.out.println("카테고리 : " + v.getCategory());
				System.out.println("제목 : " + v.getTitle());
				System.out.println("시작일 : " + dateFormat.format(v.getStartDate()));
				System.out.println("종료일 : " + dateFormat.format(v.getEndDate()));
				System.out.println("일정 내용 : " + v.getInfo());
				System.out.println("작성자 : " + v.getAuthor());
				System.out.println("작성일 : " + dateFormat.format(v.getCreatedDate()) + "\n");
			});
	}
}
