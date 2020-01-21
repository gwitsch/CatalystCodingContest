package at.gwt.cc.sr.level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.cc.sr.beans.Border;
import at.gwt.cc.sr.beans.CountryId;
import at.gwt.cc.sr.beans.Level2Input;
import at.gwt.cc.sr.util.InputParser;
import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.base.util.FileUtils;
import at.gwt.ccc.sc.App;

public class Level2 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void solve() {
		logger.info("Solving level 2");

		try (Stream<Path> files = Files.list(App.RESOURCES.resolve("level2/input"))) {
			files.filter(p -> p.toString().endsWith(".in")).forEach(this::solveLevel2File);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private void solveLevel2File(Path inputFile) {
		try {
			Level2Input level2Input = InputParser.parseLevel2(inputFile);

			String outFileName = inputFile.toFile().getName().replace(".in", ".out");
			Path outputPath = App.RESOURCES.resolve("level2/output").resolve(outFileName);

			Set<Border> countryBorders = level2Input.getCountryBorders();
			
			Comparator<CountryId> cc = Comparator.comparing(CountryId::getId);
			Comparator<Border> bc = (b1, b2) -> cc.compare(b1.getCountryId(), b2.getCountryId());
			
			FileUtils.writeSolution(outputPath, w -> {
				countryBorders.stream()
				.sorted(bc)
				.forEach(v -> {
					try {
						w.write(String.format("%d", v.getNumBorderCells()));
						w.newLine();
					} catch (IOException e) {
						logger.error(e);
					}
				});
			});
		} catch (IOException e) {
			logger.error("Cannot handle file {}", inputFile.toString(), e);
		}
	}
}
