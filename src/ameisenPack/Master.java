package ameisenPack;	
	
public class Master {
	public static Field [][] fieldArray = new Field[5][5];
	public static Ant[] antArray = new Ant[5];
	
	public static fStackNumb 	= 50; 	// Stacks per fTile
	public static fTileNumb 	= 5; 	// Number of fTiles
	public static turnNumb 		= 100; 	// Number of turns
	
	public static void main (String[] args){
		for(int i = 0; i < 10; i++){
			turn();
		}
	}
	public void turn(){
		for(Ant elem : antArray){
			elem.move();
		}
	}
	public void init(){
		int[] newPos = new int[2];
		newPos = rPos();
		fieldArray[newPos[0]][newPos[1]].setHive(true); //make Hive
		for (int i; i < fTileNumb; i++){
			newPos = rPos();
			fieldArray[newPos[0]][newPos[1]].setStacks(2, fStackNumb);
		}
	}
	
	public static int[] rPos(){
		Random rand = new Random();
		int[] randPos = new int[2];
		int[0] = rand(fieldArray.length); 		//x
		int[1] = rand(fieldArray[0].length);	//y
		return randPos;
 	}
 	
}