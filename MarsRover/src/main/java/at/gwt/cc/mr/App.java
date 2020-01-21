package at.gwt.cc.mr;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.cc.mr.util.level.Level1;
import at.gwt.ccc.base.AbstractApp;

public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();

	
	public static void main(String[] args) throws IOException {
		logger.info("Mars Rover started");
		
		App app = new App();
		app.registerLevelSolver(new Level1());
		app.run();
		
		logger.info("Shutting down Mars Rover");
	}
	
}
