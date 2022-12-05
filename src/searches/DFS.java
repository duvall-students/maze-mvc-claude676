package searches;

import java.awt.Point;
import java.util.Stack;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the functionalities of the DFS (Depth-First Search) search algorithm.
 * 
 * @revised by Xu Yan
 */

public class DFS extends SearchAlgorithm {
	
	public DFS(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
		// The data structure for DFS is a stack.
		Stack<Point> stack =new Stack<>();
		stack.push(startPoint);
		data = stack;
	}
	
	// Implement the process when no next step is found.
	@Override
	protected void noNextStep() {
		maze.markVisited(current);
		Stack<Point> stack = (Stack<Point>)data;
		stack.pop();
	}
	
	// When a new node is chosen, push it on the stack
	@Override
	protected void recordLink(Point next){
		Stack<Point> stack = (Stack<Point>)data;
		// FIXME: add try/catch for ClassCastException
		stack.push(next);
	}
	
	/*
	 * Get the next fringe point to consider.
	 * 
	 * This implementation resets the "current" instance variable 
	 * to be the next one on the fringe data structure.
	 */
	@Override
	protected void resetCurrent(){
		Stack<Point> stack = (Stack<Point>)data;
		current = stack.peek();
	}
	
}
