package at.gwt.ccc.exam.beans;

public class ShapeAnalysis {

	private int firstTimestamp;
	private int lastTimestamp;
	private int count;

	public ShapeAnalysis(int firstTimestamp, int lastTimestamp, int count) {
		this.firstTimestamp = firstTimestamp;
		this.lastTimestamp = lastTimestamp;
		this.count = count;
	}


	public int getFirstTimestamp() {
		return firstTimestamp;
	}

	public void setFirstTimestamp(int firstTimestamp) {
		this.firstTimestamp = firstTimestamp;
	}

	public int getLastTimestamp() {
		return lastTimestamp;
	}

	public void setLastTimestamp(int lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incrementCount() {
		this.count = this.count + 1;
	}
}
