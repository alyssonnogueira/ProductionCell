//package ProductionCell;
import javax.realtime.*;

public class MesaGiratoria extends RealtimeThread{

	Metal metal;
	boolean positionMetal;
	boolean down;
	boolean gira90D;
	MesaGiratoria(){
		System.out.println("Mesa Giratória Ligada!");
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

	public void gira90D(){
		try{
			Thread.sleep(2000);
			gira90D = true;
			positionMetal = true;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void waiting(){
		synchronized(this){
			try{
				this.wait();
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void originalPosition(){
		try{	
			Thread.sleep(3000);
			down = false;
			gira90D = false;
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
