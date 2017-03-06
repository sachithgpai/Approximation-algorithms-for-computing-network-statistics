package entitiy;

public class RandomPair {
	
	private int fromId;
	private int toId;
	
	public RandomPair() {
	}
	
	public RandomPair(int fromId, int toId){
		this.fromId = fromId;
		this.toId = toId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}
	
}
