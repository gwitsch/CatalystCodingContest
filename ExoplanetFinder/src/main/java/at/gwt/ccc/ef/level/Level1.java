package at.gwt.ccc.ef.level;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.ef.App;
import at.gwt.ccc.ef.beans.PlanetCandidate;

public class Level1 implements LevelSolver {

	private static final Logger logger = LogManager.getLogger();
	private static final String SEPARATOR = " ";

	private static final Path LEVEL1_INPUT_DIR = App.RESOURCES.resolve("input/level1");
	private static final Path LEVEL1_OUTPUT_DIR = App.RESOURCES.resolve("output/level1");

	@Override
	public void solve() {
		try (Stream<Path> files = Files.list(LEVEL1_INPUT_DIR)) {
			files.filter(p -> p.toString().endsWith(".in")).forEach(this::answerQuestions);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private void answerQuestions(Path inputFile) {
		logger.info("Handling planet candidates in file {}", inputFile.toAbsolutePath().toString());
		String outFileName = inputFile.toFile().getName().replace(".in", ".out");

		Path outputFile = LEVEL1_OUTPUT_DIR.resolve(Paths.get(outFileName));

		try {
			Collection<PlanetCandidate> candidates = InputParser.parseCandidates(inputFile);

			List<String> out = new ArrayList<>();

			candidates.stream().forEach(c -> {
				out.add(c.getName());

				List<Long> lightCurve = c.getLightCurve();

				int numSameBrightness = 0;
				long previousValue = -1;

				for (int i = 0; i < lightCurve.size(); i++) {
					long currentBrightness = lightCurve.get(i);

					if (previousValue == currentBrightness) {
						numSameBrightness++;
						
						// on last loop add output
						if (i == lightCurve.size() - 1) {
							out.add(Long.toString(previousValue));
							out.add(Integer.toString(numSameBrightness));
						}
						
						continue;
					}

					// add previous value to output
					if (previousValue >= 0) {
						out.add(Long.toString(previousValue));
						out.add(Integer.toString(numSameBrightness));
					}

					previousValue = currentBrightness;
					numSameBrightness = 1;

					// on last loop add output
					if (i == lightCurve.size() - 1) {
						out.add(Long.toString(previousValue));
						out.add(Integer.toString(numSameBrightness));
					}
				}
			});
			
			// join output and write to file
			try (BufferedWriter w = Files.newBufferedWriter(outputFile))  {
				w.write(out.stream().collect(Collectors.joining(SEPARATOR)));
				w.flush();
			}
		} catch (IOException e) {
			logger.error(e);
		}
		
		logger.info("All exoplanets analyzed");

	}

}
