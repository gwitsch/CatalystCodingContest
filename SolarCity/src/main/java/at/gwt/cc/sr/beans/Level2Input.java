package at.gwt.cc.sr.beans;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.Data;

@Data
public class Level2Input {
	private int rows;
	private int cols;
	private CountryCell[][] data;

	public Level2Input(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.data = new CountryCell[rows][cols];
	}

	public void addCell(int row, int col, CountryCell cell) {
		this.data[row][col] = cell;
	}

	public Set<Border> getCountryBorders() {
		Set<Border> countryBorders = new HashSet<>();

		for (int rowIndex = 0; rowIndex < this.rows; rowIndex++) {
			for (int colIndex = 0; colIndex < this.cols; colIndex++) {
				CountryCell currentCell = this.data[rowIndex][colIndex];

				if (this.isBorderCell(currentCell, rowIndex, colIndex)) {
					// increment border cells
					CountryId countryId = currentCell.getCountryId();

					countryBorders.add(new Border(countryId));
					countryBorders.stream().filter(b -> b.getCountryId().equals(countryId)).reduce((b1, b2) -> {
						throw new IllegalStateException("Entries must be unique");
					}).ifPresent(Border::incBorders);
				}
			}
		}

		return countryBorders;
	}

	private CountryCell getNorthCell(int row, int col) {
		if (row == 0) {
			// first row, no north available
			return null;
		}

		return this.data[row - 1][col];
	}

	private CountryCell getEastCell(int row, int col) {
		if (col == this.cols - 1) {
			// last column, no east available
			return null;
		}

		return this.data[row][col + 1];
	}

	private CountryCell getSouthCell(int row, int col) {
		if (row == this.rows - 1) {
			// last row, no south available
			return null;
		}

		return this.data[row + 1][col];
	}

	private CountryCell getWestCell(int row, int col) {
		if (col == 0) {
			// first column, no west available
			return null;
		}

		return this.data[row][col - 1];
	}

	private boolean isBorderCell(CountryCell currentCell, int row, int col) {
		CountryCell north = this.getNorthCell(row, col);
		CountryCell east = this.getEastCell(row, col);
		CountryCell south = this.getSouthCell(row, col);
		CountryCell west = this.getWestCell(row, col);

		if (Objects.isNull(north) || Objects.isNull(east) || Objects.isNull(south) || Objects.isNull(west)) {
			return true;
		}

		CountryId countryId = currentCell.getCountryId();

		return !countryId.equals(north.getCountryId()) || !countryId.equals(east.getCountryId())
				|| !countryId.equals(south.getCountryId()) || !countryId.equals(west.getCountryId());
	}
}
