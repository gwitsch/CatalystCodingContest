package at.gwt.ccc.exam.beans;

public class Image {

	private int timestamp;
	private Pixeldata pixeldata;

	public Image(int timestamp, int rows, int cols) {
		this.timestamp = timestamp;

		this.pixeldata = new Pixeldata(rows, cols);
	}

	public int getTimestamp() {
		return timestamp;
	}

	public Pixeldata getPixeldata() {
		return pixeldata;
	}
}
