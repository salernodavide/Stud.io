package logic.pattern;

import logic.entity.Message;

public class NotifiedState extends AbstractState {
    private String titleNotified = "Your info account was reported";
    private String titleFeedNotified = "Your feedback was reported";
    private String textNotified = "Update your info account,\nanother librarian has reported you";
    private String textFeedback = "Your feedback is inappropriate,\nbad behavior causes ban";
    
	public NotifiedState() {
		state = "Notified";
	}


	@Override
	public void goNext(StateMachine stateMachine) {
		stateMachine.setState(new BannedState());
	}


	@Override
	public Message notifyStudent(String reason) {
		Message message = null;
		if (reason.equals("feedback")) {
			message = new Message(titleFeedNotified,textFeedback);
		}
		if (reason.equals("infoAccount")) {
			message = new Message(titleNotified,textNotified);
		}
		return message;
	}
	
	
}
