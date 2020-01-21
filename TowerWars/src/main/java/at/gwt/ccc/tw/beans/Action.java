package at.gwt.ccc.tw.beans;

import java.util.Arrays;

public enum Action {
	FORWARD("F"), TURN("T");

	private String abbr;

	Action(String abbr) {
		this.abbr = abbr;
	}

	public static Action fromAbbreviation(String abbr) {
		return Arrays.stream(Action.values())
				.filter(v -> v.abbr.equalsIgnoreCase(abbr))
				.reduce((v1, v2) -> {
					throw new IllegalStateException("Enum declaration error, abbreviations are not unique");
				})
				.orElse(null);
	}
}
