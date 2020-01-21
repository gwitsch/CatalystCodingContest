package at.gwt.cc.sr.beans;

import lombok.Data;

@Data
public class Border {
	private CountryId countryId;
	private int numBorderCells;

	public Border(CountryId countryId) {
		this.countryId = countryId;
	}

	public void incBorders() {
		this.numBorderCells++;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (countryId == null) {
			if (other.countryId != null)
				return false;
		} else if (!countryId.equals(other.countryId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		return result;
	}
}
