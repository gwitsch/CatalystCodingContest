package at.gwt.ccc.tw.util;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import at.gwt.ccc.tw.beans.Action;
import at.gwt.ccc.tw.beans.Command;
import at.gwt.ccc.tw.beans.Direction;
import at.gwt.ccc.tw.beans.Movement;

public class Consultant {
	private String name;

	public Consultant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Point calculateEndPosition(Movement move) {
		Point startPosition = move.getStartPosition();
		List<Command> moves = move.getMoves();

		Point endPosition = new Point(startPosition);
		Direction direction = Direction.EAST;

		Iterator<Command> iterator = moves.iterator();

		while (iterator.hasNext()) {
			Command command = iterator.next();

			Action action = command.getAction();
			int steps = command.getSteps();

			switch (action) {
			case FORWARD:
				// update x/y
				updateEndposition(endPosition, direction, steps);
				break;
			case TURN:
				// update direction for next turn
				direction = getUpdatedDirection(direction, steps);
				break;
			default:
				// nothing to do
				break;
			}
		}

		return endPosition;
	}

	private void updateEndposition(Point p, Direction direction, int steps) {
		switch (direction) {
		case NORTH:
			// update y - substract steps from y
			p.move(p.x, p.y - steps);
			break;
		case EAST:
			// update x - add steps to x
			p.move(p.x + steps, p.y);
			break;
		case SOUTH:
			// update y - add steps to y
			p.move(p.x, p.y + steps);
			break;
		case WEST:
			// update x - substract steps from x
			p.move(p.x - steps, p.y);
			break;
		}
	}

	private Direction getUpdatedDirection(Direction currentDirection, int steps) {
		// all directions in declared order
		Direction[] allDirections = Direction.values();

		int currentIndex = Arrays.binarySearch(allDirections, currentDirection);
		int newIndex = (currentIndex + steps) % allDirections.length;

		return allDirections[newIndex];
	}
}
