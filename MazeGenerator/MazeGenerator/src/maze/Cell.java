package maze;

public class Cell {
	
	public static int CELL_DIM = 50;
	private int x; // x coord
	private int y; // y coord
	
	private boolean walls[]; //0:N, 1:E, 2:S, 3:W
	private boolean visited;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		this.walls = new boolean[4];
		for(int i = 0; i < walls.length; i++) {
			walls[i] = true;
		}
		this.visited = false;
	}

	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getWall(int n) {
		if(n > 3 || n < 0) {
			throw new RuntimeException("Out of bounds, no such wall");
		}
		return walls[n];
	}
	public String printWalls() {
		String str = "[";
		for(int i = 0; i < 3; i++) {
			str += walls[i]+", ";
		}
		return str +walls[3]+"]";
	}
	public boolean[] getWalls() {
		return walls;
	}
	public void breakWall(int n) {
		if(n > 3 || n < 0) {
			throw new RuntimeException("Out of bounds, no such wall");
		}
		walls[n] = false;
	}
	
	public Cell duplicate() {
		Cell copy = new Cell(this.x, this.y);
		copy.setVisited(this.visited);
		copy.walls = this.walls;
		return copy;	
	}
	public void setVisited(boolean b) { visited = b;}
	public boolean getVisited() {return visited;}
	
	
	public String toString() {
		
		String str = "";
		
		if(walls[0])
			str += 'N';
		if(walls[1])
			str+= 'E';
		if(walls[2])
			str += 'S';
		if(walls[3])
			str += 'W';
		
		return str;
	}
	
	public static void changeCellDim(int c) {
		CELL_DIM = c;
	}

	

}
