package cs235.boggle;

public class Tile {
	private int i;
	private int j;
	private boolean path;

	public Tile(int i, int j, boolean path) {
		this.setI(i);
		this.setJ(j);
		this.setPath(path);
	}

	public void setPath(boolean path) {
		this.path = path;
	}

	public boolean getPath() {
		return path;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getJ() {
		return j;
	}
}
