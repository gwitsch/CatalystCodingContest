package at.gwt.ccc.tw.beans;

import java.awt.Point;
import java.util.Collections;
import java.util.List;

public class Movement {

	private List<Command> moves;
	private Point startPosition;

	public Movement() {
	}

	public Movement(List<Command> moves, Point startPosition) {
		this.moves = moves;
		this.startPosition = startPosition;
	}

	public List<Command> getMoves() {
		return Collections.unmodifiableList(moves);
	}

	public void setMoves(List<Command> moves) {
		this.moves = moves;
	}

	public Point getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}
}
