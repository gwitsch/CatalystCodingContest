package at.gwt.ccc.tw.beans;

public class Command {
	private Action action;
	private int steps;

	public Command(Action action, int steps) {
		this.action = action;
		this.steps = steps;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
}
