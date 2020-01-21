package at.gwt.ccc.nw;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.ccc.base.AbstractApp;
import at.gwt.ccc.nw.level.Level1;
import at.gwt.ccc.nw.util.PersonRegistry;

/**
 * Main application.
 * 
 * @author gotthard witsch
 *
 */
public class App extends AbstractApp {
	public static final Path RESOURCES = Paths.get("src/main/resources");

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws IOException {
		logger.info("The Social Network");
		
		PersonRegistry.init(Paths.get("src/main/resources/social_network.txt"));
		
		App app = new App();
		app.registerLevelSolver(new Level1());
		app.run();
		
		logger.info("Shutting down the network");
	}

}
