package at.gwt.ccc.exam.level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.base.util.FileUtils;
import at.gwt.ccc.exam.Analyst;
import at.gwt.ccc.exam.App;
import at.gwt.ccc.exam.beans.Observation;
import at.gwt.ccc.exam.util.InputParser;

public class Level1 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void solve() {
		logger.info("Solving level 1");

		try (Stream<Path> files = Files.list(App.RESOURCES.resolve("level1/input"))) {
			files.filter(p -> p.toString().endsWith(".inp")).forEach(this::solveLevel1File);
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Finished level 1");
	}

	private void solveLevel1File(Path inputFile) {
		try {
			Observation missionData = InputParser.parseMissionData(inputFile);
			Collection<Integer> asteroidTimestamps = new Analyst().getAsteroidTimestamps(missionData);

			// write result to file
			String outFileName = inputFile.toFile().getName().replace(".inp", ".outp");
			Path outputPath = App.RESOURCES.resolve("level1/output").resolve(outFileName);

			FileUtils.writeSolution(outputPath, w -> asteroidTimestamps.forEach(t -> {
				try {
					w.write(t.toString());
					w.newLine();
				} catch (IOException e) {
					logger.error(e);
				}

			}));
		} catch (IOException e) {
			logger.error("Cannot handle file {}", inputFile.toString(), e);
		}
	}
}
