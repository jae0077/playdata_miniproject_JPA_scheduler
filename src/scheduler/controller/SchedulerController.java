package scheduler.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import scheduler.model.MemberDAO;
import scheduler.model.ParticipantDAO;
import scheduler.model.SchedulerDAO;
import scheduler.model.dto.MemberDTO;
import scheduler.model.dto.SchedulerDTO;
import scheduler.model.entity.Member;
import scheduler.model.entity.Scheduler;
import scheduler.view.EndView;

public class SchedulerController {
	private static SchedulerController instance = new SchedulerController();
	private SchedulerController() {}
	public static SchedulerController getInstance() {
		System.out.println("----- SchedulerController ");
		return instance;
	}
	
	MemberDAO memberDAO = MemberDAO.getInstance();
	SchedulerDAO schedulerDAO = SchedulerDAO.getInstance();
	ParticipantDAO participantDAO = ParticipantDAO.getInstance();
	
	// 회원가입
	public boolean register(String id, String pw, String name, String phone) {
		
		return memberDAO.memberRegister(new MemberDTO(id, pw, name, phone));
	}
	
	// 로그인
	public Member login(String id, String pw) {
		return memberDAO.login(id, pw);		
	}
	
	// 스케줄 작성 (기간)
	public Scheduler setSchedule(String startDate, String endDate, String category, String title, String info, String author) {
		
		Scheduler schedule = null;
		
		Date sDate;
		Date eDate;
		try {
			sDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			eDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
			
			schedule = schedulerDAO.setSchedule(sDate, eDate, category, title, info, author);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return schedule;
	}
	
	// 스케줄 작성 (단일)
	public Scheduler setSchedule(Date startDate, String category, String title, String info, String author) {
		
		Scheduler schedule = schedulerDAO.setSchedule(startDate, category, title, info, author);
		return schedule;
	}
	
	// 참여자 설정
	public void setParticipant(Scheduler schedule, String id) {
		
		Member member = memberDAO.searchById(id);
		
		if(participantDAO.setParticipant(schedule, member)) {
			EndView.successView("참여자를 추가 성공");
		} else {
			EndView.failView("참여자 추가 실패 : 없는 멤버입니다");
		}
	}
	
	// 스케줄 수정
	public void updateScheduler(int idx, String startDate, String endDate, String category, String title, String info, String author) {
		
		Date sDate;
		Date eDate;
		try {
			sDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			eDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
			
			if(schedulerDAO.updateScheduler(idx, new SchedulerDTO(sDate, eDate, category, title, info, author))) {
				EndView.successView("수정 완료");
			} else {
				EndView.failView("수정 실패");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
