package at.gwt.ccc.tw.util;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.gwt.ccc.tw.beans.Action;
import at.gwt.ccc.tw.beans.Command;
import at.gwt.ccc.tw.beans.Movement;

public class InputParserTest {

	private static final Path INPUT_FILE = Paths.get("src/test/resources/level1_1.in");

	@Test
	public void parseMoves() throws IOException {
		Movement movement = InputParser.parseMoves(INPUT_FILE);
		List<Command> moves = movement.getMoves();
		Point startPosition = movement.getStartPosition();

		Assert.assertNotNull(startPosition);
		Assert.assertEquals(0, startPosition.x);
		Assert.assertEquals(0, startPosition.y);

		Assert.assertEquals(5, moves.size());

		Command c1 = moves.get(0);
		Assert.assertEquals(Action.FORWARD, c1.getAction());
		Assert.assertEquals(5, c1.getSteps());

		Command c2 = moves.get(1);
		Assert.assertEquals(Action.TURN, c2.getAction());
		Assert.assertEquals(1, c2.getSteps());

		Command c3 = moves.get(2);
		Assert.assertEquals(Action.FORWARD, c3.getAction());
		Assert.assertEquals(10, c3.getSteps());

		Command c4 = moves.get(3);
		Assert.assertEquals(Action.TURN, c4.getAction());
		Assert.assertEquals(3, c4.getSteps());

		Command c5 = moves.get(4);
		Assert.assertEquals(Action.FORWARD, c5.getAction());
		Assert.assertEquals(2, c5.getSteps());
	}
}