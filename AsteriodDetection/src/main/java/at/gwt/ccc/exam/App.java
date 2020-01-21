package at.gwt.ccc.exam;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.AbstractApp;
import at.gwt.ccc.exam.level.Level1;
import at.gwt.ccc.exam.level.Level2;

public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();


	public static void main(String[] args) {
		logger.info("Detecting Asteroids");

		App app = new App();
		app.registerLevelSolver(new Level1());
		app.registerLevelSolver(new Level2());
		app.run();
		
		logger.info("Saved the planet");
	}
}
