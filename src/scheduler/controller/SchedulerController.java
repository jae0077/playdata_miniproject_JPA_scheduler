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
	private SchedulerController() {	}
	public static SchedulerController getInstance() {
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

	// 스케줄 등록
	public Scheduler setSchedule(String startDate, String endDate, String category, String title, String info, String author) {
		Scheduler schedule = null;

		Date sDate;
		Date eDate;
		try {
			sDate = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
			eDate = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);

			schedule = schedulerDAO.setSchedule(new SchedulerDTO(sDate, eDate, category, title, info, author));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return schedule;
	}

	// 참여자 설정
	public void setParticipant(int idx, String id) {

		Member member = memberDAO.searchById(id);
		Scheduler schedule = schedulerDAO.searchByIdx(idx);

		if (participantDAO.setParticipant(schedule, member)) {
			EndView.successView("참여자 추가 성공");
		} else {
			EndView.failView("참여자 추가 실패 : 없는 멤버입니다");
		}
	}

	// 참여자 삭제
	public void deleteParticipant(int idx, String id) {

		Member member = memberDAO.searchById(id);
		Scheduler schedule = schedulerDAO.searchByIdx(idx);

		if (participantDAO.deleteParticipant(schedule, member)) {
			EndView.successView("참여자 삭제 성공");
		} else {
			EndView.failView("참여자 삭제 실패 : 참여자로 등록되지 않은 멤버입니다");
		}
	}

	// 스케줄 수정
	public void updateScheduler(int idx, String startDate, String endDate, String category, String title, String info, String author) {

		Date sDate = null;
		Date eDate = null;

		try {
			if (startDate != null) {
				sDate = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
			}
			if (endDate != null) {

				eDate = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);
			}

			if (schedulerDAO.updateScheduler(idx, new SchedulerDTO(sDate, eDate, category, title, info, author))) {
				EndView.successView("수정 완료");
			} else {
				EndView.failView("수정 실패");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// 스케줄 삭제
	public void deleteScheduler(int idx, String author) {
		if (schedulerDAO.deleteScheduler(idx, author)) {
			EndView.successView("삭제 완료");
		} else {
			EndView.failView("삭제 실패");
		}
	}

	// 유저 작성 일정 조회 (전체)
	public void getSchedulerAll(String author) {
		try {
			EndView.scheduleListAll(schedulerDAO.getSchedulerAll(author));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.failView("에러 발생");
		}
	}

	// 유저 작성 일정 조회 (카테고리)
	public void getSchedulerCategory(String category, String author) {
		try {
			EndView.scheduleListAll(schedulerDAO.getSchedulerCategory(category, author));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.failView("에러 발생");
		}
	}

	// 유저 작성 일정 조회 (일정)
	public void getSchedulerDate(String startDate, String author) {

		Date sDate = null;

		try {
			sDate = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
			EndView.scheduleListAll(schedulerDAO.getSchedulerDate(sDate, author));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.failView("에러 발생");
		}
	}

	// 유저 작성 일정 조회 (제목)
	public void getSchedulerTitle(String title, String author) {

		try {
			EndView.scheduleListAll(schedulerDAO.getSchedulerTitle(title, author));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.failView("에러 발생");
		}
	}

	// 유저 참여 일정 조회
	public void getSchedulerParticipant(String id) {

		try {
			EndView.scheduleListAll(schedulerDAO.getSchedulerParticipant(id));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.failView("에러 발생");
		}
	}
}
