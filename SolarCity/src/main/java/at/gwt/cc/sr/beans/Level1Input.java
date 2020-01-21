package at.gwt.cc.sr.beans;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Level1Input {
	private int rows;
	private int cols;
	private List<Integer> altitudes;

	public Level1Input(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		this.altitudes = new ArrayList<>(rows * cols);
	}

	public void addAltitude(int altitude) {
		this.altitudes.add(altitude);
	}

	public int getMinAltitude() {
		return this.altitudes.stream().mapToInt(Integer::intValue).min().orElse(0);
	}

	public int getMaxAltitude() {
		return this.altitudes.stream().mapToInt(Integer::intValue).max().orElse(0);
	}

	public int getAvgAltitude() {
		double avg = this.altitudes.stream().mapToInt(Integer::intValue).average().orElse(0L);
		return BigDecimal.valueOf(avg).round(new MathContext(0, RoundingMode.DOWN)).intValue();
	}
}
