package searches;

import java.awt.Point;
import java.util.HashMap;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This superclass implements the general functionalities of the BFS-based (Breadth-First Search) search algorithms.
 * 
 * @revised by Xu Yan
 */

public abstract class BFS_Series extends SearchAlgorithm {	

	// Keeps up with the child-parent trail so we can recreate the chosen path
	HashMap<Point,Point> childParent;

	public BFS_Series(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
		childParent = new HashMap<>();
	}
	
	// Implement the process when the goal is reached or determined impossible.
	@Override
	protected boolean searchOver() {
		colorPath();
		return searchResult;
	}

	/*
	 * Use the trail from child to parent to color the actual chosen path
	 */
	private void colorPath(){
		Point step = goal;
		maze.markPath(step);
		while(step!=null){
			maze.markPath(step);
			step = childParent.get(step);
		}
	}

}
