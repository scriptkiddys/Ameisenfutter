package ameisenPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
	private int name;	//shall later be used to refer to ant; needed for graphics purposes
	private boolean fTile;
	private boolean hTile;
	private boolean food;
//	private ArrayList<Integer> xPos = new ArrayList<Integer>(1);
//	private ArrayList<Integer> yPos = new ArrayList<Integer>(1);
	private int x;
	private int y;

	public Ant(boolean startFood, int startX, int startY){
		food = startFood;
		x = startX;
		y = startY;
	}

	public void move(){
//		switch(dir()){
//			case 1: xPos.add(xPos.get(xPos.size()-1)+0); yPos.add(yPos.get(yPos.size() - 1)+1); break;
//			case 2: xPos.add(xPos.get(xPos.size()-1)+1); yPos.add(yPos.get(yPos.size() - 1)+0); break;
//			case 3: xPos.add(xPos.get(xPos.size()-1)+0); yPos.add(yPos.get(yPos.size() - 1)-1); break;
//			case 4: xPos.add(xPos.get(xPos.size()-1)-1); yPos.add(yPos.get(yPos.size() - 1)+0); break;
//			default: break;
//		}
		Master.fieldArray[x][y].decrStacks(3);
		switch (dir()){
			case 0:	y++; break;
			case 1: x++; break;
			case 2: y--; break;
			case 3: x--; break;
		}
		Master.fieldArray[x][y].incrStacks(3);
	}

	private int dir() { // move the ant
		tCheck();	// refresh the Tile under the ant
		int resDir = 5;	//resulting direction

		if (!food){ // if ant has no food
			if (fTile && !hTile){	//if ant is on food tile and not on hive
				Master.fieldArray[x][y].decrStacks(2);
				food = true;
			} else {	//if not on food tile, go to pheromone-Tile if adjacent
				int mypDir = sDir(1);
				if (mypDir != 5){
					resDir = mypDir;
				} else {	//if no clue, go in random direction
					resDir = rDir();
				}
			}
		} else {	// if ant has food, go to hive
			if(hTile){	//if on hive
				Master.fieldArray[x][y].incrStacks(2);
				food = false;
			} else {
				resDir = hDir();
			}
		}

		return resDir;
	}
	private void tCheck(){
		hTile = Master.fieldArray[x][y].isHive();
		if(Master.fieldArray[x][y].getStacks(2) > 0){
			fTile = true;
		}
	}

	private int hDir(){

		int[] diff = new int[4];
		int maxDiff = Master.fieldArray.length + Master.fieldArray[0].length + 1;

		if ((y + 1) < Master.fieldArray[0].length) {
			diff[0] = dirDiff(x, (y + 1), Master.hiveX, Master.hiveY);
		} else {
			diff[0] = maxDiff;
		}
		if ((y - 1) >= 0){
			diff[2] = dirDiff(x, (y - 1), Master.hiveX, Master.hiveY);
		} else {
			diff[2] = maxDiff;
		}
		if (((x + 1) < Master.fieldArray.length)) {
			diff[1] = dirDiff((x + 1), y, Master.hiveX, Master.hiveY);
		} else {
			diff[1] = maxDiff;
		}
		if((x - 1) >= 0){
			diff[3] = dirDiff((x - 1), y, Master.hiveX, Master.hiveY);
		} else {
			diff[3] = maxDiff;
		}

		int minDir = 5; //return direction with minimal difference
		int val = maxDiff;

		for (int i = 0; i < diff.length; i++) {
			if (diff[i] < val) {
				val = diff[i];
				minDir = i;
			}
		}

		return minDir;
	}

	private int dirDiff(int xS, int yS, int xF, int yF){

		int diffX = Math.abs(xS - xF);
		int diffY = Math.abs(yS - yF);

		return (diffX + diffY);
	}

	private int rDir(){
		Random rand = new Random();

		ArrayList<Integer> myList = new ArrayList<Integer>();
		if ((y + 1) < Master.fieldArray[0].length) {
			myList.add(0);
		}
		if ((y - 1) >= 0){
			myList.add(1);
		}
		if (((x + 1) < Master.fieldArray.length)) {
			myList.add(2);
		}
		if ((x - 1) >= 0){
			myList.add(3);
		}

		int rIndex = rand.nextInt(myList.size());
		return myList.get(rIndex);

	}
	private int sDir(int stackNumb){ //p=1, f=2
		int[] dir = new int[4];
		if ((y + 1) < Master.fieldArray[0].length) {
			dir[0] = Master.fieldArray[x][y + 1].getStacks(stackNumb); // north
		} else {
			dir[0] = 0;
		}
		if ((y - 1) >= 0){
			dir[2] = Master.fieldArray[x][y - 1].getStacks(stackNumb); // south
		} else {
			dir[2] = 0;
		}
		if (((x + 1) < Master.fieldArray.length)) {
			dir[1] = Master.fieldArray[x + 1][y].getStacks(stackNumb); // east
		} else {
			dir[1] = 0;
		}
		if((x - 1) >= 0){
			dir[3] = Master.fieldArray[x - 1][y].getStacks(stackNumb); // west
		} else {
			dir[3] = 0;
		}
		int maxDir = 1; // get most stacks
		int val = 0;
		for (int i = 0; i < dir.length; i++) {
			if (dir[i] > val) {
				val = dir[i];
				maxDir = i;
			}
		}
		if (dir[maxDir] == 0){
			return 5; //error
		} else {
			return maxDir;	
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
