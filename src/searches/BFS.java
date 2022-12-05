package searches;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the functionalities of the standard BFS (Breadth-First Search) search algorithm.
 * 
 * @revised by Xu Yan
 */

public class BFS extends BFS_Series {	

	public BFS(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
		data = new LinkedList<>();
		data.add(startPoint);
	}

	// Implement the process when no next step is found.
	@Override
	protected void noNextStep() {
		maze.markVisited(current);
		Queue<Point> queue = (Queue<Point>) data;
		queue.remove();
	}

	/*
	 * In addition to putting the new node on the data structure, 
	 * we need to remember who the parent is.
	 */
	@Override
	protected void recordLink(Point next){	
		Queue<Point> queue = (Queue<Point>) data;
		queue.add(next);
		childParent.put(next,current);
	}

	/*
	 * The new node is the one next in the queue
	 */
	@Override
	protected void resetCurrent(){
		Queue<Point> queue = (Queue<Point>) data;
		current = queue.peek();
	}

}
