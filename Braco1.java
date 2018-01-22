//package ProductionCell;
import javax.realtime.*;

public class Braco1 extends Robo{
	Metal metal;
	boolean positionMetal = false;
	
	Braco1(){
		System.out.println("Braço 1 Funcionando!");
	}
	
	//void TransportarMetal(){
	public void run(){
	}
	
	public void pegaMetal(){
		try {
			Thread.sleep(2000);
			this.Position45();
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void gira180(){
		try {
			this.Position180();
			Thread.sleep(1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void metalnaPrensa(){
		synchronized(this){
			try {
				Thread.sleep(2000);
				positionMetal = true;
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void posicionaBraco2(){
		try {
			this.Position90();
			Thread.sleep(1000);
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
