package scheduler.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import scheduler.controller.SchedulerController;
import scheduler.model.entity.Member;
import scheduler.model.entity.Scheduler;

public class StartPage {
	
	private static StartPage instance = new StartPage();
	private StartPage() {}
	public static StartPage getInstance() {
		return instance;
	}
	
	SchedulerController sc = SchedulerController.getInstance();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static boolean trigger = false;
	static boolean loggedIn = false;
	
	static Member user = null;
	
	static String id = null;
	static String pw = null;
	
	static int inputNum = 0;
	static String input = "";
	
	// 화면 시작
	public void start() {
		
		trigger = true;
			
		try {
			while(trigger) {
				
				System.out.println("====================== Scheduler ======================");
				System.out.println("1, 로그인			2,회원가입			0,종료");
				
				inputNum = Integer.parseInt(br.readLine());
				
				if(inputNum == 1) {
					logInPage();
				} else if (inputNum == 2) {
					registerPage();
				} else if (inputNum == 0) {
					System.out.println("종료합니다");
					br.close();
					br = null;
					trigger = false;
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}	
	}
	
	// 회원가입 페이지
	private void registerPage() {
		
		String newId = null;
		String newPw = null;
		String newName = null;
		String newPhone = null;
		
		System.out.println("===================== 회원가입 =====================");
		System.out.print("사용하실 아이디를 입력해주세요 : ");
		
		try {
			newId = br.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.print("사용하실 비밀번호를 입력해주세요 : ");
			newPw = br.readLine();
			System.out.print("이름을 입력해주세요 : ");
			newName = br.readLine();
			System.out.print("전화번호를 입력해주세요 : ");
			newPhone = br.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		boolean register = sc.register(newId, newPw, newName, newPhone);
		
		if(register) {
			System.out.println("회원가입 성공");
			logInPage();
		}
		
	}
	
	// 로그인 페이지
	private void logInPage() {
		
		System.out.println("===================== 로그인 =====================");
		
		try {
			System.out.print("아이디를 입력해주세요 : ");
			id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.print("비밀번호를 입력해주세요 : ");
			pw = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		user = sc.login(id, pw);
		loggedIn = true;
		loggedInPage();
	}
	
	
	// 로그인 후 페이지
	private void loggedInPage() {
		
		System.out.println("===================== 안녕하세요 "+ user.getName() + "님 =====================");
		
		while(loggedIn) {
			System.out.println("1,일정 조회		2,일정 등록		3,로그아웃			0,종료");
			try {
				inputNum = Integer.parseInt(br.readLine());
				
				if(inputNum == 1) {
					schedulerSearchPage();
				} else if(inputNum == 2) {
					try {
						System.out.print("일정 시작  ( YYYY / MM / DD ) : ");
						String startDate = br.readLine();
						System.out.print("일정 종료 ( YYYY / MM / DD ) : ");
						String endDate = br.readLine();
						System.out.print("일정 카테고리 : ");
						String category = br.readLine();
						System.out.print("제목 : ");
						String title = br.readLine();
						System.out.print("설명 : ");
						String info = br.readLine();
						
						Scheduler schedule = sc.setSchedule(startDate, endDate, category, title, info, user.getId());
						
						addParticipant(schedule.getIdx());
	
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else if (inputNum == 3) {
					loggedIn = false;
					id = null;
					pw = null;
					start();
				} else if (inputNum == 0) {
					System.out.println("종료합니다");
					br.close();
					loggedIn = false;
					trigger = false;
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 1, 일정조회 페이지
	private void schedulerSearchPage() {
		System.out.println("===================== 조회방법을 고르세요 =====================");
		System.out.println("1,전체일정 조회		2,카테고리별 조회		3,날짜 조회		4.참여일정 조회		5,일정수정			0,뒤로가기");
		
		try {
			inputNum = Integer.parseInt(br.readLine());
			
			if(inputNum == 1) { 		// 전체일정조회
				sc.getSchedulerAll(user.getId());
			} else if(inputNum == 2) {
				System.out.println("조회 할 카테고리를 입력해주세요");
				input = br.readLine();
				sc.getSchedulerCategory(input, user.getId());
			} else if(inputNum == 3) {	// 날짜 조회
				System.out.println("조회 할 일정 시작일을 입력해주세요( YYYY / MM / DD )");
				input = br.readLine();
				sc.getSchedulerDate(input, id);
			} else if(inputNum == 4) {	// user 가 참여자인 일정 조회
				sc.getSchedulerParticipant(user.getId());
			} else if(inputNum == 5) {
				updateSchedule();
			} else if(inputNum == 0) {
				loggedInPage();
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 일정 수정
	private void updateSchedule() {
		int idx;
		sc.getSchedulerAll(user.getId());
		System.out.println("===================== 수정할 일정의 idx를 입력하세요 =====================");
		try {
			idx = Integer.parseInt(br.readLine());
			
			System.out.println("===================== 수정할 정보를 고르세요 =====================");
			System.out.println("1,날짜		2,카테고리			3,제목		4,설명		5,참여자		0,뒤로가기");
			
			inputNum = Integer.parseInt(br.readLine());
			
			if(inputNum == 1) { 		// 날짜 수정
				
				System.out.println("1,시작 일정		2,종료 일정		0, 뒤로가기");
				inputNum = Integer.parseInt(br.readLine());
				
				if(inputNum == 1) {
					
					System.out.println("시작일정을 수정해주세요 ( YYYY / MM / DD )");
					input = br.readLine();
					
					sc.updateScheduler(idx, input, null, null, null, null, user.getId());
					
				} else if(inputNum == 2) {
					
					System.out.println("시작일정을 수정해주세요 ( YYYY / MM / DD )");
					input = br.readLine();
					
					sc.updateScheduler(idx, null, input, null, null, null, user.getId());
					input = br.readLine();
					
				} else if(inputNum == 0) {
					updateSchedule();
				}
				
			} else if(inputNum == 2) {	// 카테고리 수정
				
				System.out.println("카테고리를 다시 지정해주세요");
				input = br.readLine();
				
				sc.updateScheduler(idx, null, null, input, null, null, user.getId());

			} else if(inputNum == 3) {	// 제목 수정
				
				System.out.println("제목을 수정해주세요");
				input = br.readLine();
				
				sc.updateScheduler(idx, null, null, null, input, null, user.getId());

			} else if(inputNum == 4) {	// 설명
				
				System.out.println("설명을 수정해주세요");
				input = br.readLine();
				
				sc.updateScheduler(idx, null, null, null, null, input, user.getId());
				
			} else if(inputNum == 5) {	// 설명
				
				System.out.println("참여자 추가 삭제");
				participantUpdate(idx);

			} else if(inputNum == 6) {	// 참여자 수정
				
			} else if(inputNum == 0) {	// 뒤로가기
				loggedInPage();
			}
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// 참여자 수정
	private void participantUpdate(int idx) {
		
		System.out.println("1,참여자 추가		2,참여자 삭제");
		try {
			inputNum = Integer.parseInt(br.readLine());
			if(inputNum == 1) {
				addParticipant(idx);
			} else if(inputNum == 2) {
				deleteParticipant(idx);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 침야지 삭제
	private void deleteParticipant(int idx) {
		
		System.out.println("참여자를 삭제하겠습니까? (y/n)");
		
		try {
			input = null;
			input = br.readLine();
			
			if(input.equals("y")) {
				input = null;
				System.out.println("참여자를 삭제헤주세요 (아이디)");
				input = br.readLine();
				
				sc.deleteParticipant(idx, input);
				deleteParticipant(idx);
			} else if(input.equals("n")) {
				loggedInPage();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	// 참여자 추가
	private void addParticipant(int idx) {
		
		System.out.println("참여자를 추가하겠습니까? (y/n)");
		
		try {
			input = null;
			input = br.readLine();
			
			if(input.equals("y")) {
				input = null;
				System.out.println("참여자를 추가하세요 (아이디)");
				input = br.readLine();
				
				sc.setParticipant(idx, input);
				addParticipant(idx);
			} else if(input.equals("n")) {
				loggedInPage();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
