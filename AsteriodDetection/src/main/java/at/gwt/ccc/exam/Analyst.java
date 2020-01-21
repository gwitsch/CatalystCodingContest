package at.gwt.ccc.exam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import at.gwt.ccc.exam.beans.AsteroidShape;
import at.gwt.ccc.exam.beans.Image;
import at.gwt.ccc.exam.beans.Observation;
import at.gwt.ccc.exam.beans.ShapeAnalysis;

public class Analyst {

	public Collection<Integer> getAsteroidTimestamps(Observation missionData) {
		return missionData.getImages().stream()
				.filter(i -> !i.getPixeldata().isEmpty())
				.map(Image::getTimestamp)
				.sorted()
				.collect(Collectors.toList());
	}


	public Map<AsteroidShape, ShapeAnalysis> analyseShapes(Observation missionData) {
		Collection<Image> images = missionData.getImages().stream().filter(i -> !i.getPixeldata().isEmpty()).collect(Collectors.toList());

		Map<AsteroidShape, ShapeAnalysis> shapeAnalysis = new HashMap<>();

		for (Image image : images) {
			AsteroidShape shape = new AsteroidShape(image.getPixeldata().calculateShape());
			int timestamp = image.getTimestamp();

			if (!shapeAnalysis.containsKey(shape)) {
				shapeAnalysis.put(shape, new ShapeAnalysis(timestamp, timestamp, 0));
			}

			ShapeAnalysis analysis = shapeAnalysis.get(shape);
			analysis.incrementCount();

			// update timestamps

			if (analysis.getFirstTimestamp() > timestamp) {
				analysis.setFirstTimestamp(timestamp);
			}

			if (analysis.getLastTimestamp() < timestamp) {
				analysis.setLastTimestamp(timestamp);
			}
		}

		return shapeAnalysis;
	}
}
