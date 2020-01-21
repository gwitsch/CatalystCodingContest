package at.gwt.ccc.exam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import at.gwt.ccc.exam.beans.Image;
import at.gwt.ccc.exam.beans.Observation;
import at.gwt.ccc.exam.beans.Pixeldata;

public final class InputParser {

	private InputParser() {
//		 utility class
	}

	public static Observation parseMissionData(Path missionData) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(missionData)) {
			String[] comps = reader.readLine().trim().split(" ");

			// parse observation
			if (comps.length != 3) {
				throw new IllegalArgumentException(
						String.format("Missing observation data. Check the dataset in %s", missionData.toString()));
			}

			int start = Integer.parseInt(comps[0]);
			int end = Integer.parseInt(comps[1]);
			int numImages = Integer.parseInt(comps[2]);

			return new Observation(start, end, parseImages(reader, numImages));
		}
	}

	private static Collection<Image> parseImages(BufferedReader reader, int numImages) throws IOException {
		Collection<Image> images = new ArrayList<>();

		// parse images
		for (int i = 0; i < numImages; i++) {
			String[] comps = reader.readLine().trim().split(" ");

			int timestamp = Integer.parseInt(comps[0]);
			int rows = Integer.parseInt(comps[1]);
			int cols = Integer.parseInt(comps[2]);

			Image image = new Image(timestamp, rows, cols);
			fillPixelData(reader, image);
			images.add(image);
		}

		return images;
	}

	private static void fillPixelData(BufferedReader reader, Image image) throws IOException {
		Pixeldata data = image.getPixeldata();

		int rows = data.getRows();
		int cols = data.getCols();

		// read next rows with reader
		for (int i = 0; i < rows; i++) {
			String[] rowData = reader.readLine().trim().split(" ");

			if (rowData.length != cols) {
				throw new IllegalStateException("Missing pixel data");
			}

			for (int j = 0; j < cols; j++) {
				data.setIntensity(i, j, Integer.parseInt(rowData[j]));
			}
		}
	}
}
