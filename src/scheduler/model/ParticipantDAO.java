package scheduler.model;

public class ParticipantDAO {
	private static ParticipantDAO instance = new ParticipantDAO();
	private ParticipantDAO() {}
	public static ParticipantDAO getInstance() {
		return instance;
	}
	
}
