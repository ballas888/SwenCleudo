package cluedo.search;

public class PQueue {
	private QNode[] priorityQ;
	private int maxSize = 100;
	private int tNumber;

	public PQueue() {
		priorityQ = new QNode[maxSize];
		tNumber = 0;
	}

	public void enqueue(QNode item){
		int a = 0;
		if(this.isEmpty()){
			priorityQ[tNumber++] = item;
		}else{
			ensureCapacity();
			for(a = tNumber -1; a >=0; a--){
				if((item.getCostToHere()+item.getTotalCostToGoal()) > (priorityQ[a].getCostToHere()+priorityQ[a].getTotalCostToGoal())){
					priorityQ[a+1] = priorityQ[a];
				}else{
					break;
				}
			}
			priorityQ[a+1] = item;
			tNumber++;
		}
	}

	public void ensureCapacity() {
		if (isFull()) {
			maxSize = maxSize * 2;
			QNode[] temp = new QNode[maxSize];
			for (int i = 0; i < tNumber; i++) {
				temp[i] = priorityQ[i];
			}
			priorityQ = temp;
		}
	}

	public QNode dequeue() {
		return priorityQ[--tNumber];
	}

	public boolean isEmpty() {
		if (tNumber == 0) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		return (tNumber == maxSize);
	}

}
