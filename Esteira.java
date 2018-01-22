//package ProductionCell;
import javax.realtime.*;

public class Esteira extends RealtimeThread {
	Metal metal;
	boolean positionMetal = false;
	boolean girando;
	Esteira(){
		girando = true;
		System.out.println("Esteira Rodando!");
	}
	
	//void TransportarMetal(){
	public void run(){
		synchronized(this){
		try {
				positionMetal = false;
				Thread.sleep(5000);
			    positionMetal = true;
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
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
