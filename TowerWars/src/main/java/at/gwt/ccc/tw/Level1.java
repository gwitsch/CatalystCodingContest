package at.gwt.ccc.tw;

import java.awt.Point;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.tw.util.Consultant;
import at.gwt.ccc.tw.util.InputParser;

public class Level1 implements LevelSolver {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public void solve() {
		try (Stream<Path> files = Files.list(App.RESOURCES.resolve("level1/input"))) {
			files.filter(p -> p.toString().endsWith(".in")).forEach(this::handleLevel1);
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Finished level 1");
	}

	private void handleLevel1(Path level1Input) {
		logger.debug("Calculating endposition for {}", level1Input.toString());

		Consultant consultant = new Consultant("Glados' helper");

		try {
			Point endPosition = consultant.calculateEndPosition(InputParser.parseMoves(level1Input));

			// write result to file
			String outFileName = level1Input.toFile().getName().replace(".in", ".out");
			Path output = App.RESOURCES.resolve("level1/output").resolve(outFileName);

			try (Writer w = Files.newBufferedWriter(output)) {
				w.write(String.format("%d %d", endPosition.x, endPosition.y));
			} catch (IOException e) {
				logger.error("Could not write endpoint {} to file", endPosition);
			}

		} catch (IOException e) {
			logger.error(e);
		}
	}
}
