package at.gwt.ccc.exam.level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.base.util.FileUtils;
import at.gwt.ccc.exam.Analyst;
import at.gwt.ccc.exam.App;
import at.gwt.ccc.exam.beans.AsteroidShape;
import at.gwt.ccc.exam.beans.Observation;
import at.gwt.ccc.exam.beans.ShapeAnalysis;
import at.gwt.ccc.exam.util.InputParser;

public class Level2 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void solve() {
		logger.info("Solving level 2");

		try (Stream<Path> files = Files.list(App.RESOURCES.resolve("level2/input"))) {
			files.filter(p -> p.toString().endsWith(".inp")).forEach(this::solveLevel2File);
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Finished level 2");
	}

	private void solveLevel2File(Path inputFile) {
		try {
			Observation missionData = InputParser.parseMissionData(inputFile);

			Map<AsteroidShape, ShapeAnalysis> analysis = new Analyst().analyseShapes(missionData);

			// write result to file
			String outFileName = inputFile.toFile().getName().replace(".inp", ".outp");
			Path outputPath = App.RESOURCES.resolve("level2/output").resolve(outFileName);

			Comparator<ShapeAnalysis> comparator = Comparator.comparing(ShapeAnalysis::getFirstTimestamp);

			FileUtils.writeSolution(outputPath,
					w -> analysis.entrySet().stream().map(Entry::getValue).sorted(comparator).forEach(a -> {
						try {
							w.write(String.format("%d %d %d", a.getFirstTimestamp(), a.getLastTimestamp(),
									a.getCount()));
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
