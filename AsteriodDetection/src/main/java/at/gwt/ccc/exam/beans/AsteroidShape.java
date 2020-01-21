package at.gwt.ccc.exam.beans;

import java.awt.Polygon;

import at.gwt.ccc.exam.util.Utils;

public class AsteroidShape {

	private Polygon shape;

	public AsteroidShape(Polygon shape) {
		this.shape = shape;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AsteroidShape that = (AsteroidShape) o;

		return Utils.polyonsEqual(shape, that.shape);
	}

	@Override
	public int hashCode() {
		return Utils.hashCode(this.shape);
	}
}
