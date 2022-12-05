package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the functionalities of the standard Greedy (essentially also a Breadth-First Search) search algorithm.
 * 
 * @revised by Xu Yan
 */

public class Greedy extends Greedy_Series {	

	public Greedy(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
	}

	/*
	 * Of all the neighbors that are not a wall choose the one
	 * with the smallest distance to goal.
	 */
	@Override
	protected Point chooseNeighbor(Collection<Point> neighbors){
		List<Point> corridors = new ArrayList<>();
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				corridors.add(p);
			}
		}
		return closestToGoal(corridors);
	}

}
