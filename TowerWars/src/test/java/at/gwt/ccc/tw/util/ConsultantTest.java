package at.gwt.ccc.tw.util;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import at.gwt.ccc.tw.beans.Movement;

public class ConsultantTest {
	private static final Path INPUT_FILE = Paths.get("src/test/resources/level1_1.in");

	@Test
	public void calculateEndPosition() throws IOException {
		Movement move = InputParser.parseMoves(INPUT_FILE);
		Point endPoint = new Consultant("John").calculateEndPosition(move);

		Assert.assertEquals(7, endPoint.x);
		Assert.assertEquals(10, endPoint.y);
	}
}