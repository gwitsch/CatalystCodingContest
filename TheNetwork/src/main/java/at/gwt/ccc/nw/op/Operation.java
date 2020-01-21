package at.gwt.ccc.nw.op;

import java.util.Arrays;

public enum Operation {
	ANCESTORS("ancestors");

	private String name;

	private Operation(String name) {
		this.name = name;
	}

	public static Operation fromName(String name) {
		return Arrays.stream(Operation.values()).filter(op -> op.name.equalsIgnoreCase(name)).reduce((v1, v2) -> {
			throw new IllegalStateException("Operations must be unique");
		}).orElseThrow(() -> new IllegalArgumentException("Invalid name for operation given"));
	}
}
