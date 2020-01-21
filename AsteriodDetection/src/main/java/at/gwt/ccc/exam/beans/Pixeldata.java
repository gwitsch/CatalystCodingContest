package at.gwt.ccc.exam.beans;

import java.awt.Polygon;
import java.util.Arrays;
import java.util.Objects;

public class Pixeldata {

	private int rows;
	private int cols;

	private int[][] data;

	public Pixeldata(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		this.data = new int[rows][cols];
	}

	public void setIntensity(int row, int col, int intensity) {
		this.data[row][col] = intensity;
	}

	/**
	 * @return true if all pixels do have the intensity of 0. This method does fail fast on the first occurrence of a non-zero value.
	 */
	public boolean isEmpty() {
		boolean empty = true;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				empty = this.data[i][j] == 0;

				if (!empty) {
					return empty;
				}
			}
		}

		return empty;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pixeldata pixeldata = (Pixeldata) o;
		return rows == pixeldata.rows &&
				cols == pixeldata.cols &&
				Arrays.equals(data, pixeldata.data);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(rows, cols);
		result = 31 * result + Arrays.hashCode(data);
		return result;
	}

	public Polygon calculateShape() {
		Polygon polygon = new Polygon();

		boolean empty = true;

		// add all non-zero intensity coordinates to the polygon
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int intensity = this.data[i][j];

				if (intensity != 0) {
					polygon.addPoint(i, j);
					empty = false;
				}
			}
		}

		if (!empty) {
			int[] xs = polygon.xpoints;
			Arrays.sort(xs);
			int lowestX  = xs[0];

			int[] ys = polygon.ypoints;
			Arrays.sort(ys);
			int lowestY = ys[0];

			polygon.translate(lowestX * -1, lowestY * -1);
		}

		return polygon;
	}
}
