package scheduler.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import scheduler.controller.SchedulerController;
import scheduler.model.entity.Member;

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
	
	// 화면 시작
	public void start() {
		trigger = true;
			
		try {
			while(trigger) {
				
				System.out.println("======================Scheduler======================");
				System.out.println("1, 로그인			2,회원가입			0,종료");
				
				int inputNum = Integer.parseInt(br.readLine());
				
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
		System.out.println(newId);
		System.out.println(newPw);
		System.out.println(newName);
		System.out.println(newPhone);
		
		boolean register = sc.register(newId, newPw, newName, newPhone);
		
		if(register) {
			System.out.println("회원가입 성공");
			logInPage();
		}
		
	}
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
		LoggedInPage();
	}

	private void LoggedInPage() {
		System.out.println("loggedIn");
	}
	
	
}
