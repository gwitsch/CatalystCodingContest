package at.gwt.ccc.nw.level;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.LevelSolver;
import at.gwt.ccc.nw.App;
import at.gwt.ccc.nw.beans.Person;
import at.gwt.ccc.nw.op.OperationHandler;

public class Level1 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

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
		logger.info("Answering questions in file {}", inputFile.toAbsolutePath().toString());
		String outFileName = inputFile.toFile().getName().replace(".in", ".out");

		OperationHandler opHandler = new OperationHandler();
		Path outputFile = LEVEL1_OUTPUT_DIR.resolve(Paths.get(outFileName));

		try (BufferedReader r = Files.newBufferedReader(inputFile);
				BufferedWriter w = Files.newBufferedWriter(outputFile)) {

			String line;

			while (Objects.nonNull((line = r.readLine()))) {
				Collection<Person> result = opHandler.handle(line);

				if (result.isEmpty()) {
					w.write("NONE");
				} else {
					w.write(result.stream().map(Person::getName).collect(Collectors.joining(",")));
				}

				w.newLine();
			}

		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("All questions are answered");
	}

}
