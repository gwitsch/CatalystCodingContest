package at.gwt.cc.sr.level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.cc.sr.beans.Level1Input;
import at.gwt.cc.sr.util.InputParser;
import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.base.util.FileUtils;
import at.gwt.ccc.sc.App;

public class Level1 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void solve() {
		logger.info("Solving level 1");

		try (Stream<Path> files = Files.list(App.RESOURCES.resolve("level1/input"))) {
			files.filter(p -> p.toString().endsWith(".in")).forEach(this::solveLevel1File);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private void solveLevel1File(Path inputFile) {
		try {
			Level1Input level1Input = InputParser.parse(inputFile);

			int min = level1Input.getMinAltitude();
			int max = level1Input.getMaxAltitude();
			int avg = level1Input.getAvgAltitude();

			String outFileName = inputFile.toFile().getName().replace(".in", ".out");
			Path outputPath = App.RESOURCES.resolve("level1/output").resolve(outFileName);

			FileUtils.writeSolution(outputPath, w -> {
				try {
					w.write(String.format("%d %d %d", min, max, avg));
				} catch (IOException e) {
					logger.error(e);
				}
			});

		} catch (IOException e) {
			logger.error("Cannot handle file {}", inputFile.toString(), e);
		}
	}
}
