package logic.pattern;

import logic.entity.Message;

public abstract class AbstractState {
	protected String state;
	
	//public static final String 
	public abstract void goNext(StateMachine stateMachine);
	
	public abstract Message notifyStudent(String reason);
	public String getState() {
		return state;
	}

}