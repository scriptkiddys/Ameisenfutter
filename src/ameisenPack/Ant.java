package ameisenPack;

import java.util.Random;

public class Ant {
	private int name;	//shall later be used to refer to ant; needed for graphics purposes
	private boolean fTile;
	private boolean hTile;
	private boolean food;
	private int x;
	private int y;
	
	public void move(){
		switch(dir()){
			case 1: y++; break;
			case 2: x++; break;
			case 3: y--; break;
			case 4: x--; break;
			default: break;
		}
	}
	private int dir() { // move the ant
		tCheck();	// refresh the Tile under the ant
		int resDir = 5;	//resulting direction

		if (!food){ // if ant has no food
			if (fTile){	//if ant is on food tile
				Master.fieldArray[x][y].decrStacks(2);
				food = true;														//does ant move after having taken food?
			} else {	//if not on food tile, go to food-Tile if adjacent
				int myfDir = sDir(2);
				if (myfDir != 5){
					resDir = myfDir;
				} else {	//if no adj. food tile, go to pheromone-Tile if adjacent
					int mypDir = sDir(1);
					if (mypDir != 5){
						resDir = mypDir;
					} else {	//if no clue, go in random direction
						resDir = rDir();
					}
				}
			}
		} else {	// if ant has food, go to hive
			if(hTile){	//if on hive
				Master.fieldArray[x][y].incrStacks(2);
				food = false;
																					//does the ant move after dropping food, or vice versa?
			} else {

				//how does the ant move if it has food and wants to the hive? TODO
				//add p-Stack-Addition
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
	
	private int rDir(){
		Random rand = new Random();
		int rDir = rand.nextInt(3) + 1;
		return rDir;
	}
	private int sDir(int stackNumb){ //p=1, f=2
		int[] dir = new int[4];
		if ((y + 1) != Master.fieldArray[0].length) {
			dir[0] = Master.fieldArray[x][y + 1].getStacks(stackNumb); // north
		} else {
			dir[0] = 0;
		}
		if ((y - 1) != 0){
			dir[2] = Master.fieldArray[x][y - 1].getStacks(stackNumb); // south
		} else {
			dir[0] = 0;
		}
		if (((x + 1) != Master.fieldArray.length)) {
			dir[1] = Master.fieldArray[x + 1][y].getStacks(stackNumb); // east
		} else {
			dir[0] = 0;
		}
		if((x - 1) != 0){
			dir[3] = Master.fieldArray[x - 1][y].getStacks(stackNumb); // west
		} else {
			dir[0] = 0;
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
