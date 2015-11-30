package ameisenPack;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Master {
	public static final int F_STACK_NUMB	= 50; 	// Stacks per fTile	//50
	public static final int F_TILE_NUMB		= 5; 	// Number of fTiles	//5
	public static final int TURN_NUMB		= 100; 	// Number of turns	//infinite
	public static final int ANT_NUMB		= 5;						//
	public static final int FIELD_DIM_X		= 10;
	public static final int FIELD_DIM_Y		= 10;
	public static final int P_DECAY_TIME	= 5;

	public static Map myMap;

	public static int turnCount		= 0;	// Number of played turns

	public static Field [][] fieldArray = new Field[FIELD_DIM_X][FIELD_DIM_Y];
	public static Ant[] antArray = new Ant[ANT_NUMB];

	public static int hiveX = 0;
	public static int hiveY = 0;
	
	public static void main (String[] args){
		init();
		while (fieldArray[hiveX][hiveY].getStacks(1)<(F_STACK_NUMB*F_TILE_NUMB)){
			turn();
			try {
				Thread.sleep(2000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void turn(){
		myMap.repaint();
		for(Ant elem : antArray){
			elem.move();
		}
		for (int i = 0; i < fieldArray.length; i++) {
			for (int j = 0; j < fieldArray[0].length; j++) {
				fieldArray[i][j].decay();
			}
		}
	}

	public static void init(){
		MapStartUp();

		for (int i = 0; i < FIELD_DIM_X; i++) {
			for (int j = 0; j < FIELD_DIM_Y; j++) {
				fieldArray[i][j] = new Field();
			}
		}

		hiveX = FIELD_DIM_X / 2;
		hiveY = FIELD_DIM_Y / 2;
		fieldArray[hiveX][hiveY].setHive(true); //make Hive
		fieldArray[hiveX][hiveY].setStacks(3, ANT_NUMB);

		int[] newPos = new int[2];
		for (int i = 0; i < F_TILE_NUMB; i++){
			newPos = rPos();
			fieldArray[newPos[0]][newPos[1]].setStacks(2, F_STACK_NUMB);
		}

		for (int i = 0; i < antArray.length; i++) {
			antArray[i] = new Ant(false, hiveX, hiveY);
		}
	}
	
	public static int[] rPos(){
		Random rand = new Random();
		int[] randPos = new int[2];
		randPos[0] = rand.nextInt(fieldArray.length); 		//x
		randPos[1] = rand.nextInt(fieldArray[0].length);	//y
		return randPos;
 	}

	public static void MapStartUp() {
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					JFrame frame = new JFrame("Game");
					myMap = new Map();
					frame.add(myMap);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}