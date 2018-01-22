//package ProductionCell;
import javax.realtime.*;

public class Braco2 extends Robo {
	Braco2(){
		System.out.println("Braco2 funcionando!");
	}
	Metal metal;
	boolean positionMetal = false;
	//void TransportarMetal(){
	public void run(){

	}
	
	void move180(){
		try {

			Thread.sleep(1000);
			this.Position180();

		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	void posicionaBraco1(){
		try {

			Thread.sleep(1000);
			this.Position45();

		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	void setMetal(Metal metal){
		try {

			Thread.sleep(2000);
			this.metal = metal;

		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	Metal getMetal(){
		try{
		Thread.sleep(2000);
		Metal metal = this.metal;
		this.metal = null;
		return metal;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
			return null;
		}

	}

}
