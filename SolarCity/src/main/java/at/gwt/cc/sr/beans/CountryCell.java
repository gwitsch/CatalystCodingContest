package at.gwt.cc.sr.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryCell {
	private int altitude;
	private CountryId countryId;
}
