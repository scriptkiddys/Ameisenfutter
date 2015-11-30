package ameisenPack;

import java.util.ArrayList;

public class Field {
	private int pStacks;
	private int fStacks;
	private boolean hive;
	private int antCount;
	private int decayRate;
	private ArrayList<Integer> pStackList = new ArrayList<>();

	public Field(int StartDecayRate){
		decayRate = StartDecayRate;
	}
	
	public void decay(){
		for (int i = 0; i < pStackList.size(); i++) {
			pStackList.set(i, (pStackList.get(i)-1));

		}
	}

	public int 	getStacks	(int stackNumb) {
		switch (stackNumb){
			case 1: 	return pStacks;
			case 2: 	return fStacks;
			case 3:		return antCount;
			default:	return -1;
		}
	}
	public void setStacks	(int stackNumb, int stacks) {
		switch (stackNumb){
			case 1: this.pStacks = stacks; break;
			case 2: this.fStacks = stacks; break;
			case 3: this.antCount = stacks; break;
			default: break;
		}
	}
	public void incrStacks	(int stackNumb) {
		switch (stackNumb){
			case 1: pStackList.add(decayRate); break;
			case 2: this.fStacks++; break;
			case 3: this.antCount++; break;
			default: break;
		}
	}
	public void decrStacks	(int stackNumb) {
		switch (stackNumb){
			case 1:
				if(pStacks > 0){
					this.pStacks--;	
				}
				break;
			case 2:
				if(fStacks > 0){
					this.fStacks--;	
				}
				break;
			case 3:
				if(antCount > 0){
					this.antCount--;
				}
				break;
			default: break;
		}
	}
	public boolean isHive() {
		return hive;
	}
	public void setHive(boolean hive) {
		this.hive = hive;
	}	
	
}