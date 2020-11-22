package logic.pattern;

public class StateMachine {
	private AbstractState state;

	public StateMachine(AbstractState state) {
		this.state = state;

	}

	public void goNext() {
		state.goNext(this);
	}

	public void setState(AbstractState state) {
		this.state = state;
	}

	public AbstractState getState() {
		return state;
	}
}