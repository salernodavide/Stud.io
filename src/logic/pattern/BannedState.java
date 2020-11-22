package logic.pattern;

import logic.entity.Message;

public class BannedState extends AbstractState {
	private String titleBanned = "Account banned";
    private String textBanned = "Your account is banned,\nyou've been behaving inappropriately";

	public BannedState() {
		state = "Banned";
	}

	@Override
	public void goNext(StateMachine stateMachine) {
		stateMachine.setState(new NormalState());

	}

	@Override
	public Message notifyStudent(String reason) {
		Message message = null;
		message = new Message(titleBanned,textBanned);
		return message;
	}
}
