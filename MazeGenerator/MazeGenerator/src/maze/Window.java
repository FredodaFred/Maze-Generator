package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Window extends JPanel{
	
	private int height;
	private int width;
	private Cell[][] grid;
	
	public Window(int height, int width, Cell[][] grid) {
		
		this.height = height;
		this.width = width;
		this.grid = grid;

		Dimension dimensions= new Dimension(this.width, this.height);
		this.setPreferredSize(dimensions);
		this.setBackground(Color.black);
        this.setVisible(true);
	}
	
	public void setCells(Cell[][] grid) {
		this.grid = grid;
	}
	/**
	 * update()
	 * Gets rid of old maze, and paints a new one
	 */
	public void update() {
		Graphics g = this.getGraphics();
		g.clearRect(0, 0, width, height);
		this.paintComponent(g);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		
		if(grid[0][0].getVisited() ==  false) {
			g.clearRect(0, 0, width+(Cell.CELL_DIM+20), height+(Cell.CELL_DIM+20));
			return;
		}
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++){				
				Cell currentCell = grid[j][i];
				if(currentCell.getWall(0)) {//N
					g.fillRect(currentCell.getX()*Cell.CELL_DIM, currentCell.getY()*Cell.CELL_DIM,
							Cell.CELL_DIM+(Cell.CELL_DIM/5), (Cell.CELL_DIM/5));
				}
				if(currentCell.getWall(1)) {//E
					g.fillRect(currentCell.getX()*Cell.CELL_DIM+Cell.CELL_DIM, currentCell.getY()*Cell.CELL_DIM,
							(Cell.CELL_DIM/5), Cell.CELL_DIM+(Cell.CELL_DIM/5));
				}
				if(currentCell.getWall(2)) {//S
					g.fillRect(currentCell.getX()*Cell.CELL_DIM, currentCell.getY()*Cell.CELL_DIM
							+Cell.CELL_DIM, Cell.CELL_DIM+(Cell.CELL_DIM/5), (Cell.CELL_DIM/5));
				}
				if(currentCell.getWall(3)) {//W
					g.fillRect(currentCell.getX()*Cell.CELL_DIM, currentCell.getY()*Cell.CELL_DIM, 
							(Cell.CELL_DIM/5), Cell.CELL_DIM+(Cell.CELL_DIM/5));
				}		
			}
		}
		this.validate();
	}
	public int getDim() {return height;}
	public void changeDim(int dim) {
		width = height = dim;
	}

}
