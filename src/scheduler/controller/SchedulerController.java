package scheduler.controller;

import java.util.Date;

import scheduler.model.MemberDAO;
import scheduler.model.ParticipantDAO;
import scheduler.model.SchedulerDAO;
import scheduler.model.entity.Member;
import scheduler.model.entity.Scheduler;
import scheduler.view.EndView;

public class SchedulerController {
	private static SchedulerController instance = new SchedulerController();
	private SchedulerController() {}
	public static SchedulerController getInstance() {
		return instance;
	}
	
	// static 강사님께 여쭤보기
	MemberDAO memberDAO = MemberDAO.getInstance();
	SchedulerDAO schedulerDAO = SchedulerDAO.getInstance();
	ParticipantDAO participantDAO = ParticipantDAO.getInstance();
	
	// 회원가입
	public void register(String id, String pw, String name, String phone) {
		memberDAO.memberRegister(id, pw, name, phone);
	}
	
	// 로그인
	public void login(String id, String pw) {
		memberDAO.login(id, pw);		
	}
	
	// 스케출 작성 (기간)
	public Scheduler setSchedule(Date startDate, Date endDate, String category, String title, String info, String author) {
		Scheduler schedule = schedulerDAO.setSchedule(startDate, category, title, info, author);
		return schedule;
	}
	
	// 스케출 작성 (단일)
	public Scheduler setSchedule(Date startDate, String category, String title, String info, String author) {
		Scheduler schedule = schedulerDAO.setSchedule(startDate, category, title, info, author);
		return schedule;
	}
	
	// 참여자 설정
	public void setParticipant(Scheduler schedule, Member member) {
		if(participantDAO.setParticipant(schedule, member)) {
			EndView.successView("참여자를 추가 성공");
		} else {
			EndView.failView("참여자 추가 실패 : 없는 멤버입니다");
		}
	}
	
}
