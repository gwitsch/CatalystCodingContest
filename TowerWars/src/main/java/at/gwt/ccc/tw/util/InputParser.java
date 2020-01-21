package at.gwt.ccc.tw.util;

import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.tw.beans.Action;
import at.gwt.ccc.tw.beans.Command;
import at.gwt.ccc.tw.beans.Movement;

public final class InputParser {
	private static final Logger logger = LogManager.getLogger();

	private InputParser() {
		// utility class
	}

	public static Movement parseMoves(Path inputFile) throws IOException {
		logger.debug("Parsing enemy moves from file {}", inputFile.toString());

		// read line by line

		try (LineNumberReader reader = new LineNumberReader(new FileReader(inputFile.toFile()))) {
			String line;

			Movement move = new Movement();

			while (Objects.nonNull(line = reader.readLine())) {
				int ln = reader.getLineNumber();

				switch (ln) {
				case 1:
					Point startPosition = parseStartPosition(line);
					move.setStartPosition(startPosition);
					break;
				case 2:
					List<Command> commands = parseMoves(line);
					move.setMoves(commands);
					break;
				default:
					// nothing to do
					break;
				}
			}

			return move;
		}
	}

	private static List<Command> parseMoves(String line) {
		// e.g. F 5 T 1 F 10 T 3 F 2

		// remove leading and trailing whitespaces
		String trimmed = line.trim();
		String[] comComps = trimmed.split(" ");

		List<Command> moves = new ArrayList<>();

		for (int i = 0; i < comComps.length; i += 2) {
			int comPos = i;
			int stepPos = i + 1;

			Action action = Action.fromAbbreviation(comComps[comPos]);
			int steps = Integer.parseInt(comComps[stepPos]);

			moves.add(new Command(action, steps));
		}

		return moves;
	}

	private static Point parseStartPosition(String line) {
		// remove leading and trailing whitespaces

		String trimmed = line.trim();

		String[] posComps = trimmed.split(" ");

		if (posComps.length != 2) {
			throw new IllegalArgumentException("Invalid coordinates string given");
		}

		int x = Integer.parseInt(posComps[0]);
		int y = Integer.parseInt(posComps[1]);

		return new Point(x, y);
	}
}
