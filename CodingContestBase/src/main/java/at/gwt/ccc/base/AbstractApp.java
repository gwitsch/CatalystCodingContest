package at.gwt.ccc.base;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractApp {
	private static final Logger logger = LogManager.getLogger();

	private Collection<LevelSolver> levelSolvers;

	protected AbstractApp() {
		this.levelSolvers = new ArrayList<>();
	}

	public void run() {
		logger.info("Running application");
		this.levelSolvers.forEach(LevelSolver::solve);
		logger.info("Application finished");
	}

	public void registerLevelSolver(LevelSolver solver) {
		logger.debug("Adding level solver %s", solver.getClass().getSimpleName());
		this.levelSolvers.add(solver);
	}
}
