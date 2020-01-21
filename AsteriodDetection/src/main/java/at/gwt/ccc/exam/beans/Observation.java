package at.gwt.ccc.exam.beans;

import java.util.Collection;
import java.util.Collections;

public class Observation {

	private int start;
	private int end;
	private Collection<Image> images;

	public Observation(int start, int end, Collection<Image> images) {
		this.start = start;
		this.end = end;
		this.images = images;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public Collection<Image> getImages() {
		return Collections.unmodifiableCollection(images);
	}
}
