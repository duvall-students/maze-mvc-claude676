package searches;

import java.awt.Point;
import java.util.Collection;
import java.util.PriorityQueue;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This superclass implements the general functionalities of the Greedy-based 
 * (essentially also a BFS-based (Breadth-First Search)) search algorithms.
 * 
 * @revised by Xu Yan
 */

public class Greedy_Series extends BFS_Series {
	
	public Greedy_Series(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
		data = new PriorityQueue<Point>(15, (p1, p2) -> distanceToGoal(p1)-distanceToGoal(p2));
		data.add(startPoint);
	}

	// Implement the process when no next step is found.
	@Override
	protected void noNextStep() {
		maze.markVisited(current);
		PriorityQueue<Point> queue = (PriorityQueue<Point>) data;
		queue.remove();
	}

	protected int distanceToGoal(Point p){
		return goal.x-p.x + goal.y-p.y;
	}

	/*
	 * Of all the neighbors, choose one with the smallest distance to goal.
	 */
	protected Point closestToGoal(Collection<Point> neighbors){
		int smallestDistance = Integer.MAX_VALUE;
		Point next = null;
		for(Point p: neighbors){
			int dist = distanceToGoal(p);
			if(dist < smallestDistance){
				next = p;
				smallestDistance = dist;
			}

		}
		return next;
	}

	/*
	 * When a next step is found, add it to the queue and remember the child-parent relationship
	 */
	@Override
	protected void recordLink(Point next){	
		data.add(next);
		childParent.put(next,current);
	}

	/*
	 * The current node is the one chosen by the priority queue
	 */
	@Override
	protected void resetCurrent(){
		PriorityQueue<Point> queue = (PriorityQueue<Point>) data;
		current = queue.peek();
	}
	
}
