package entitiy;

public class Distance implements Comparable<Distance>{
	
	private int distance;
	private long count;

	public Distance() {
	}
	
	public Distance(int distance, long count) {
		this.distance = distance;
		this.count = count;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
	@Override
	public int compareTo(Distance d) {
		if(this.getDistance() < d.getDistance())
			return -1;
		else
			return 1;
	}
	
}
