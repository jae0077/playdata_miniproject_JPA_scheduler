package scheduler.view;

import java.util.List;

import scheduler.model.entity.Scheduler;

public class EndView {

	public static void successView(String message) {
		System.out.println(message);
	}
	
	public static void failView(String message) {
		System.out.println(message);
	}
	
		
	// 모든 일정 출력
	public static void scheduleListAll(List shedulerAll) {
		if (shedulerAll != null) {
			int length = shedulerAll.size();

			if (length != 0) {
				for (int index = 0; index < length; index++) {
					System.out.println("[일정]" + (index + 1) + shedulerAll.get(index));
				}
			} else {
				System.out.println("====================");
			}
		} else {
			System.out.println("====================");
		}
	}
	
	
	// 특정 일정 조회
	public static void scheduleListOne(Scheduler scheduler) {
		if (scheduler != null) {
			int length = ((List)scheduler).size();

			if (length != 0) {
				for (int index = 0; index < length; index++) {
					System.out.println("[일정]" + (index + 1) + ((List)scheduler).get(index));
				}
			} else {
				System.out.println("====================");
			}
		} else {
			System.out.println("====================");
		}
	}
	
	
	
}
