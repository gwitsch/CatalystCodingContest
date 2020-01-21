package at.gwt.cc.sr.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import at.gwt.cc.sr.beans.CountryCell;
import at.gwt.cc.sr.beans.CountryId;
import at.gwt.cc.sr.beans.Level1Input;
import at.gwt.cc.sr.beans.Level2Input;

public final class InputParser {

	private InputParser() {

	}

	public static Level1Input parse(Path data) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(data)) {
			// read first line
			String[] dimension = reader.readLine().trim().split(" ");
			int rows = Integer.parseInt(dimension[0]);
			int cols = Integer.parseInt(dimension[1]);

			Level1Input input = new Level1Input(rows, cols);

			String row = null;

			// read next lines and parse them to altitude values
			while (Objects.nonNull(row = reader.readLine())) {
				String[] alts = row.trim().split(" ");

				for (int i = 0; i < alts.length; i++) {
					int alt = Integer.parseInt(alts[i]);
					input.addAltitude(alt);
				}
			}

			return input;
		}
	}

	public static Level2Input parseLevel2(Path level2Data) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(level2Data)) {
			// read first line
			String[] dimension = reader.readLine().trim().split(" ");
			int rows = Integer.parseInt(dimension[0]);
			int cols = Integer.parseInt(dimension[1]);

			Level2Input l2i = new Level2Input(rows, cols);

			// read lines and fill data
			String row = null;

			int rowIndex = 0;
			while (Objects.nonNull(row = reader.readLine())) {
				int colIndex = 0;
				String[] data = row.trim().split(" ");

				for (int dataIndex = 0; dataIndex < data.length; dataIndex += 2) {
					int altitude = Integer.parseInt(data[dataIndex]);
					int countryId = Integer.parseInt(data[dataIndex + 1]);

					l2i.addCell(rowIndex, colIndex, new CountryCell(altitude, new CountryId(countryId)));
					colIndex++;
				}

				rowIndex++;
			}

			return l2i;
		}
	}
}
