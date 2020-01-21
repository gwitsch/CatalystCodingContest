package at.gwt.ccc.ef;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.AbstractApp;
import at.gwt.ccc.ef.level.Level1;

public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("The Exoplanet Finder");

		App app = new App();
		app.registerLevelSolver(new Level1());
		app.run();

		logger.info("Shutting down Exoplanet Finder");
	}
}
