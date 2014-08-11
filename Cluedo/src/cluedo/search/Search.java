package cluedo.search;

import java.awt.Point;
import java.util.ArrayList;

import cluedo.main.Data;
import cluedo.main.Tile;
import cluedo.main.Tile.Direction;

public class Search {
	private double totalCostToGoal;
	private QNode currentObj = null;
	private QNode goal;
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public void search(Tile from, Tile to, Data data){
		data.setSearching(true);
		PQueue fringe = new PQueue();
		totalCostToGoal = estimate(from,to);
		//System.out.println(totalCostToGoal);
		QNode q = new QNode(from, null, 0.0, totalCostToGoal);
		fringe.enqueue(q);		
		while(!fringe.isEmpty()){			
			QNode qObj = fringe.dequeue();			
			currentObj = qObj;
			if(!currentObj.isVisited()){
				currentObj.setVisited(true);
				if(isTheSame(currentObj,to)){
					break;
				}
				Tile curTile = currentObj.getTile();
				if(curTile.can_move_up()){
					Tile nTile = curTile.get_neigh(Direction.UP);
					double costToNeigh = currentObj.getCostToHere()+1; // The on represents the distance to the next tile
					double estTotal = costToNeigh + estimate(nTile, to);
					QNode n = new QNode(nTile, currentObj, costToNeigh, estTotal);
					fringe.enqueue(n);
				}
				if(curTile.can_move_down()){
					Tile nTile = curTile.get_neigh(Direction.DOWN);
					double costToNeigh = currentObj.getCostToHere()+1; // The on represents the distance to the next tile
					double estTotal = costToNeigh + estimate(nTile, to);
					QNode n = new QNode(nTile, currentObj, costToNeigh, estTotal);
					fringe.enqueue(n);
				}
				if(curTile.can_move_right()){
					Tile nTile = curTile.get_neigh(Direction.RIGHT);
					double costToNeigh = currentObj.getCostToHere()+1; // The on represents the distance to the next tile
					double estTotal = costToNeigh + estimate(nTile, to);
					QNode n = new QNode(nTile, currentObj, costToNeigh, estTotal);
					fringe.enqueue(n);
				}
				if(curTile.can_move_left()){
					Tile nTile = curTile.get_neigh(Direction.LEFT);
					double costToNeigh = currentObj.getCostToHere()+1; // The on represents the distance to the next tile
					double estTotal = costToNeigh + estimate(nTile, to);
					QNode n = new QNode(nTile, currentObj, costToNeigh, estTotal);
					fringe.enqueue(n);
				}
				System.out.println("At The Top: "+fringe.peek().getTile().getArrayPos().x+" "+fringe.peek().getTile().getArrayPos().y);
			}			
		}
		getStartNode(currentObj);
		data.setMousePath(points);
		data.setSearching(false);
	}
	
	public void getStartNode(QNode node){
		
		while(node.getFrom() != null){
			Tile cTile = node.getTile();
			System.out.println(cTile.getArrayPos().x+" "+cTile.getArrayPos().y);
			points.add(new Point(cTile.getArrayPos().x,cTile.getArrayPos().y));
			node = node.getFrom();
		}		
		System.out.println("Done with search");		
	}
	
	public boolean isTheSame(QNode from,Tile to){
		int x1 = from.getTile().getArrayPos().x;
		int y1 = from.getTile().getArrayPos().y;
		
		int x2 = to.getArrayPos().x;
		int y2 = to.getArrayPos().y;
		
		if(x1 == x2 && y1 == y2){
			return true;
		}
		return false;
		
	}
	
	public double estimate(Tile from, Tile to){
		int x_w = Math.abs(from.getArrayPos().x - to.getArrayPos().x);
		int y_h = Math.abs(from.getArrayPos().y - to.getArrayPos().y);
		
		return x_w + y_h;
	}
}
