package scheduler.view;

import lombok.extern.slf4j.Slf4j;
import scheduler.model.StartPage;

@Slf4j
public class StartView {
	public static void main (String[] args) {
		StartPage sp = StartPage.getInstance();
		
		sp.start();
		
	}
}
