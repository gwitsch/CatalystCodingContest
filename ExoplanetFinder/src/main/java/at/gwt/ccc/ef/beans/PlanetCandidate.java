package at.gwt.ccc.ef.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetCandidate {
	private String name;
	private List<Long> lightCurve;

	public PlanetCandidate(String name) {
		this.name = name;
		this.lightCurve = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<Long> getLightCurve() {
		return Collections.unmodifiableList(lightCurve);
	}

	public void addBrightness(long brightness) {
		this.lightCurve.add(brightness);
	}

	public int getBrightnessCount() {
		return this.lightCurve.size();
	}
}
