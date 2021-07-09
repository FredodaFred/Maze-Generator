package maze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Screen extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton createNew;
	private JButton increaseSize;
	private JButton decreaseSize;
	private MazeGenerator gen;
	private Window mazePanel;
	private int rowcount;
	
	public Screen() {
		this.setTitle("MazeGenerator");
		rowcount = 25; //default row count
		
		createNew = new JButton("Generate New Maze");
		createNew.setVisible(true);
		
		increaseSize = new JButton("+1");
		decreaseSize = new JButton("-1");
		
		
		gen = new MazeGenerator(rowcount);
		gen.generateMaze();
		gen.printCells();
		
		Dimension dimensions= new Dimension(600, 600);
		this.setMaximumSize(dimensions);
		this.setMinimumSize(dimensions);		
		this.setLayout(new BorderLayout());
		
		
		Cell.CELL_DIM = 500/rowcount;
		mazePanel = new Window(500, 500, gen.getCells());	
		mazePanel.setVisible(true);
		
		createNew.addActionListener(new ActionListener() {
			//create a new maze and paint it
			@Override
			public void actionPerformed(ActionEvent e) {
				gen.clearMaze();		
				gen.generateMaze();
				//gen.printCells();
				mazePanel.setCells(gen.getCells());
				mazePanel.update();
			}		
		});		
		increaseSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//clear the screen
				gen.clearMaze();
				mazePanel.setCells(gen.getCells());
				mazePanel.update();
				//the new maze
				rowcount++;
				Cell.CELL_DIM = 500/rowcount;
				gen.setDim(rowcount);
				gen.clearMaze();
				gen.generateMaze();
				mazePanel.setCells(gen.getCells());
				mazePanel.update();	
			}
			
		});
		decreaseSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {		
				//clear the screen
				gen.clearMaze();
				mazePanel.setCells(gen.getCells());
				mazePanel.update();
				//the new maze
				rowcount--;
				Cell.CELL_DIM = 500/rowcount;
				gen.setDim(rowcount);
				gen.clearMaze();
				gen.generateMaze();
				mazePanel.setCells(gen.getCells());
				mazePanel.update();
			}			
		});
		
		this.add(mazePanel, BorderLayout.NORTH);
		this.add(createNew, BorderLayout.SOUTH);
		this.add(increaseSize, BorderLayout.WEST);
		this.add(decreaseSize, BorderLayout.EAST);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		
	}
	
	private void resizeMaze() {
		
	}
	public static void main(String[] args) {
		new Screen();
	}
}
