//package ProductionCell;
//import javax.realtime.*;

public class Guindaste extends Thread{
	Metal metal;
	boolean positionMetal;
	boolean down;
	boolean up;
	boolean moving;
	Guindaste(){
		System.out.println("Guindaste Ligado!");
	}
	//void TransportarMetal(){
	public void run(){
		
	}
	
	public void down(){
		try{
			Thread.sleep(1000);
			down = true;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void up(){
		try{
			if (down == true){
				down = false;
				Thread.sleep(1000);
				up = true;
			}
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void move(){
		try{
			Thread.sleep(2000);
			up = false;
			moving = true;
			positionMetal = true;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void originalPosition(){
		try{	
			Thread.sleep(3000);
			down = false;
			up = false;
			moving = false;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	void setMetal(Metal metal){
		this.metal = metal;
	}
	
	Metal getMetal(){
		Metal metal = this.metal;
		this.metal = null;
		return metal;
	}

}
