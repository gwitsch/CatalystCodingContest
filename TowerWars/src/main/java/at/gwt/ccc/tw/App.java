package at.gwt.ccc.tw;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.AbstractApp;

public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("Starting Alien Defense Systems");

		App app = new App();
		app.registerLevelSolver(new Level1());
		app.run();
		
		logger.info("Handled all levels");
	}
}
