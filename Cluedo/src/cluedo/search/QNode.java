package cluedo.search;

import cluedo.main.Tile;

public class QNode {
	private Tile tile;
	private QNode from;
	private Double costToHere;
	private Double totalCostToGoal;

	public QNode(Tile tile, QNode from, Double costToHere, Double totalCostToGoal){
		this.tile = tile;
		this.from = from;
		this.costToHere = costToHere;
		this.totalCostToGoal = totalCostToGoal;
	}

	public Tile getTile(){
		return this.tile;
	}

	public void setTile(Tile tile){
		this.tile = tile;
	}

	public QNode getFrom(){
		return this.from;
	}

	public void setFrom(QNode from){
		this.from = from;
	}

	public Double getCostToHere() {
		return costToHere;
	}

	public void setCostToHere(Double costToHere) {
		this.costToHere = costToHere;
	}

	public Double getTotalCostToGoal() {
		return totalCostToGoal;
	}

	public void setTotalCostToGoal(Double totalCostToGoal) {
		this.totalCostToGoal = totalCostToGoal;
	}

}
