package at.gwt.ccc.ef.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.gwt.ccc.ef.beans.PlanetCandidate;

public final class InputParser {
	private InputParser() {
		// utility class
	}

	public static List<PlanetCandidate> parseCandidates(Path keplerInputData) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(keplerInputData)) {
			String[] comps = reader.readLine().trim().split(" ");

			int numPlanets = Integer.parseInt(comps[0]);

			if (numPlanets > 0) {
				List<PlanetCandidate> candidates = new ArrayList<>(numPlanets);

				for (int i = 1; i < comps.length; i++) {
					String name = comps[i++];
					int numBrightnessValues = Integer.parseInt(comps[i++]);
					
					PlanetCandidate candidate = new PlanetCandidate(name);

					for (int j = 0; j < numBrightnessValues; j++) {
						candidate.addBrightness(Long.parseLong(comps[i++]));
					}
					i--;
					candidates.add(candidate);
				}

				return candidates;
			} else {
				return Collections.emptyList();
			}
		}
	}
}
