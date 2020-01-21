package at.gwt.ccc.sc;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.cc.sr.level.Level2;
import at.gwt.ccc.base.AbstractApp;

public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("Solar City: Activated");

		App app = new App();
		app.registerLevelSolver(new Level2());
		app.run();

		logger.info("Shutting down");
	}
}
