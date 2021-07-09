package maze;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
	
	private int cols; // x axis
	private int rows; //y axis
	
	private Cell[][] cells; //REMEMBER: [y][x]
	
	/**
	 * 
	 * @param side length (mazes are square, we only need one param)
	 */
	public MazeGenerator(int dim) {
		this.cols = this.rows = dim;
		
		this.cells = new Cell[rows][cols];
		
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {		
				cells[j][i] = new Cell(i, j); //cells[y][x], but Cells(x, y) DONT CONFUSE THE ORDER
			}	
		}	
	}
	
	/**
	 * clearMaze()
	 * fill the cells with new/fresh cell objects
	 */
	public void clearMaze() {
		this.cells = new Cell[rows][cols];
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {			
				cells[j][i] = new Cell(i, j); //cells[y][x], but Cells(x, y) DONT CONFUSE THE ORDER
			}	
		}
		System.gc(); //get rid of the old cell objects
	}
	
	/**
	 * back tracking algorithm
	 */
	public void generateMaze() {
		Random rng = new Random(System.currentTimeMillis()); //System current time millis for improved seeding
		Stack<Cell> trav  = new Stack<Cell>();
		
		trav.push(cells[0][0]); //start the algorithm at 0,0 (top left)
			
		while (!visitedAllCells()) {
			//1) create a set of unvisited neighbors
			Cell currentCell = trav.peek(); //reference the top
			ArrayList<Cell> freeCells = new ArrayList<Cell>();
 			
			//North Wall
			//cant check the northern neighbor if we are at the top
			if(currentCell.getY() > 0) {
 				//check if our northern neighbor was NOT visited
				if(! cells[currentCell.getY() -1][currentCell.getX()].getVisited()) {
					freeCells.add(cells[currentCell.getY() -1][currentCell.getX()]);
				}		
			}	
			//East Wall  
			if(currentCell.getX() < cols-1) {
				if(!cells[currentCell.getY()][currentCell.getX()+1].getVisited()) {
					freeCells.add(cells[currentCell.getY()][currentCell.getX()+1]);
				}		
			}		
			//South Wall
			if(currentCell.getY() < rows-1) {
				if(!cells[currentCell.getY()+1][currentCell.getX()].getVisited()) {
					freeCells.add(cells[currentCell.getY() +1][currentCell.getX()]);
				}		
			}		
			//West Wall
			if(currentCell.getX() > 0) {
				if(!cells[currentCell.getY()][currentCell.getX()-1].getVisited()) {
					freeCells.add(cells[currentCell.getY()][currentCell.getX()-1]);
				}		
			}
			
			if(!freeCells.isEmpty()) {
				//The path where we travel to the next cell
				
				//(psuedo)randomly choose our next cell
				Cell nextCell = freeCells.get(rng.nextInt(freeCells.size()));
							
				//create a path between the next cell and current cell by DESTROYING a wall (setting it false uwu)
				if(currentCell.getY() == nextCell.getY() +1) { //N
					nextCell.breakWall(2); //Remember 0-N, 1-E, 2-S, 3-W 
					currentCell.breakWall(0);//ALSO remember this method edits the wall array within the cell object
				}
				else if(currentCell.getX() == nextCell.getX() -1) { //E
					nextCell.breakWall(3);
					currentCell.breakWall(1);
				}
				else if(currentCell.getY() == nextCell.getY() -1) { //S
					nextCell.breakWall(0);
					currentCell.breakWall(2);
				}
				else if(currentCell.getX() == nextCell.getX() +1) { //W
					nextCell.breakWall(1);
					currentCell.breakWall(3);
				}
				
				trav.push(nextCell); // our next current cell
				currentCell.setVisited(true);

			}
			else {
				//The backtracking path
				if(currentCell.getVisited() == false) {
					currentCell.setVisited(true);
				}			
				trav.pop(); //pops and repeats	
			}	
		}
	}
	public void generateMazeWKruskal() {
		Random rng = new Random(System.currentTimeMillis());
		ArrayList<Cell> visited = new ArrayList();
	}
	
	/*
	 * Checks if all the cells were visited
	 */
	private boolean visitedAllCells() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].getVisited())
					return false;
			}
		}
		return true;
	}
	/**
	 * Prints the maze in text
	 */
	public void printCells() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				System.out.print("["+cells[i][j]+"]");
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
	public int getCols() {return cols;}
	public int getRows() {return rows;}
	public void setDim(int dim) {cols = rows = dim;}
	
	/**
	 * getCells()
	 * @return Cells - a COPIED object of the cells with the generator
	 */
	public Cell[][] getCells(){ 
		Cell[][] copiedCells = new Cell[rows][cols];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				copiedCells[i][j] = this.cells[i][j].duplicate();
			}
		}
		return copiedCells;
	}
	
	/**
	 * Constructor for 2 dims. 
	 * @param cols
	 * @param rows
	 */
	public MazeGenerator(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		
		this.cells = new Cell[rows][cols];
		
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {		
				cells[j][i] = new Cell(i, j); //cells[y][x], but Cells(x, y) DONT CONFUSE THE ORDER
			}	
		}	
	}
	

}
