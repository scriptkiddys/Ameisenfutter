package ameisenPack;

public class Field {
	private int pStacks;
	private int fStacks;
	private boolean hive;
	
	public int 	getStacks	(int stackNumb) {
		switch stackNumb{
			case 1: return pStacks; break;
			case 2: return fStacks; break;
			default: return -1; break;
		}
	}
	public void setStacks	(int stackNumb, int stacks) {
		switch stackNumb{
			case 1: this.pStacks = pStacks; break;
			case 2: this.fStacks = fStacks; break;
			default: return -1; break;
		}
	}
	public void incrStacks	(int stackNumb) {
		switch stackNumb{
			case 1: this.pStacks++; break;
			case 2: this.fStacks++; break;
			default: return -1; break;
		}
	}
	public void decrStacks	(int stackNumb) {
		switch stackNumb{
			case 1:
				if(!pStacks <= 0){
					this.pStacks--;	
				}
				break;
			case 2:
				if(!fStacks <= 0){
					this.fStacks--;	
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