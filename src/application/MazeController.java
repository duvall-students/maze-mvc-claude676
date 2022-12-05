package application;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import searches.SearchAlgorithm;
import searches.SearchFactory;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the control of the Maze, start and end points, and search algorithms.
 * 
 * @revised by Xu Yan
 */

public class MazeController {
	
	private Rectangle[][] mirrorMaze;	// the Rectangle objects that will get updated and drawn.  It is 
	// called "mirror" maze because there is one entry per square in 
	// the maze.
	
	/*
	 * Maze color settings
	 */
	private Color[] color  = new Color[] {
			Color.rgb(200,0,0),		// wall color
			Color.rgb(128,128,255),	// path color
			Color.WHITE,			// empty cell color
			Color.rgb(200,200,200)	// visited cell color
	};
	
	/* 
	 * Logic of the program
	 */
	// The search algorithms
	private SearchAlgorithm search;
	private SearchFactory searchFactory;
	
	// Where to start and stop the search
	private Point start;
	private Point goal;
	
	// The maze to search
	private Maze maze;
	
	// size of the maze
	private int rows;
	private int cols;
	
	
	public MazeController(int numRows, int numColumns) {
		// Initializing logic state
		start = new Point(1, 1);
		goal = new Point(numRows - 2, numColumns - 2);
		maze = new Maze(numRows, numColumns);
		
		// Initializing coordinates space size
		rows = numRows;
		cols = numColumns;
	}
	
	public Point getMazeDimensions() {
		return new Point(rows, cols);
	}

	/*
	 * Setup the maze part for drawing. In particular,
	 * make the mirrorMaze.
	 */
	protected Group setupMaze(int numBlockSize) {
		Group drawing = new Group();
		mirrorMaze = new Rectangle[rows][cols];
		for(int i = 0; i< rows; i++){
			for(int j =0; j < cols; j++){
				Rectangle rect = new Rectangle(j*numBlockSize, i*numBlockSize, numBlockSize, numBlockSize);
				rect.setFill(color[getCellState(new Point(i,j))]);
				mirrorMaze[i][j] = rect;
				drawing.getChildren().add(rect);
			}	
		}
		return drawing;
	}
	
	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		search = null;
		redraw();
	}
	
	/*
	 * resets all the rectangle colors according to the 
	 * current state of that rectangle in the maze.  This 
	 * method assumes the display maze matches the model maze
	 */
	public void redraw() {
		for(int i = 0; i< mirrorMaze.length; i++){
			for(int j =0; j < mirrorMaze[i].length; j++){
				mirrorMaze[i][j].setFill(color[getCellState(new Point(i,j))]);
			}
		}
	}
	
	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime) {
		if (search != null) search.step();
		redraw();
	}
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		
		// Restart the search.
		searchFactory = new SearchFactory();
		search = searchFactory.makeSearch(searchType, maze, start, goal);
	}

	public int getCellState(Point position) {
		return maze.get(position);
	}
	
}
