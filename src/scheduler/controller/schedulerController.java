package scheduler.controller;

import scheduler.model.ParticipantDAO;
import scheduler.model.SchedulerDAO;
import scheduler.model.MemberDAO;

public class SchedulerController {
	private static SchedulerController instance = new SchedulerController();
	private SchedulerController() {}
	public static SchedulerController getInstance() {
		return instance;
	}
	
	// static 강사님께 여쭤보기
	static MemberDAO memberDAO = MemberDAO.getInstance();
	SchedulerDAO schedulerDAO = SchedulerDAO.getInstance();
	ParticipantDAO participantDAO = ParticipantDAO.getInstance();
	
	public static void register(String id, String pw, String name, String phone) {
		memberDAO.memberRegister(id, pw, name, phone);
		
	}
	
}
