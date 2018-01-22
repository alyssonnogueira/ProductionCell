//package ProductionCell;
import javax.realtime.*;

public class Prensa extends RealtimeThread {
	Metal metal;
	boolean positionMetal = false;
	boolean open;
	
	Prensa(){
		try{
			System.out.println("Abrindo a prensa!");
			Thread.sleep(2000);
		} catch(InterruptedException ex) {
			System.out.println("Erro ao iniciar a prensa!");
			Thread.currentThread().interrupt();
		}
		System.out.println("Prensa ligada!");
	}
	
	//void ForjarMetal(){
	public void run(){
	}
	
	public void AbrePrensa(){
		synchronized(this){
			try {
				Thread.sleep(2000);
				open = true;
				this.positionMetal = true;
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
	public void FechaPrensa(){
		try {
			Thread.sleep(1000);
			open = false;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	public void ForjarMetal(){
		synchronized(this){
			try {
				Thread.sleep(5000);
				metal.Forjado = true;
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
