package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import application.Maze;

/**
 * CSC3465(Software Design) - Lab: Maze Solving (Part I + Part II + Part III)
 * 
 * This class implements the functionalities of the RandomWalk search algorithm.
 * 
 * @revised by Xu Yan
 */

public class RandomWalk extends SearchAlgorithm {
	public final double EXPLORE_BIAS = .999;
	private Point next;
	private Random rand;
	
	public RandomWalk(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		next = startPoint;
		rand = new Random();
	}
	
	// Implement the process when no next step is found.
	@Override
	protected void noNextStep() {
		maze.markVisited(current);
	}
	
	/*
	 * Choose a random neighbor out of all the non-wall neighbors. 
	 * To make the algorithm prefer going to unexplored areas, make the EXPLORE_BIAS higher.
	 */
	@Override
	protected Point chooseNeighbor(Collection<Point> neighbors){
		List<Point> empties = new ArrayList<>();
		List<Point> possibles = new ArrayList<>();
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				empties.add(p);
			}
			if(maze.get(p) != Maze.WALL){	// includes empties - all candidates
				possibles.add(p);
			}
		}
		if((rand.nextDouble()<EXPLORE_BIAS && !empties.isEmpty())) {
			int randIndex = rand.nextInt(empties.size());
			return empties.get(randIndex);
		}
		if(!possibles.isEmpty()){
			int randIndex = rand.nextInt(possibles.size());
			return possibles.get(randIndex);
		}
		return null;
	}
	
	// Memorize and store the moving path.
	@Override
	protected void recordLink(Point next){	
		this.next = next;
		maze.markVisited(current);
	}
	
	// Reset the moving point.
	@Override
	protected void resetCurrent(){
		current = next;
	}
	
}
