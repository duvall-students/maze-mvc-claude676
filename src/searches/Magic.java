package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the functionalities of the Magic 
 * (also a Greedy search algorithm; essentially a Breadth-First Search) search algorithm.
 * 
 * @revised by Xu Yan
 */

public class Magic extends Greedy_Series {	

	public Magic(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		super(mazeBlocks, startPoint, goalPoint);
	}

	/*
	 * Rather than choosing the (first) closest NON-wall, choose 
	 * any of the closest next squares.
	 */
	@Override
	protected Point chooseNeighbor(Collection<Point> neighbors){
		Point closest = closestToGoal(neighbors);
		List<Point> possibles = new ArrayList<>();
		for(Point p: neighbors){
			if(distanceToGoal(p) == distanceToGoal(closest)){
				possibles.add(p);
			}
		}
		if(!possibles.isEmpty()){
			int randIndex = (int)(Math.random()*possibles.size());
			return possibles.get(randIndex);
		}
		return null;
	}
	
}
