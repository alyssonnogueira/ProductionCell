//package ProductionCell;
import java.*;
import javax.realtime.*;

public class Deposito extends RealtimeThread {
	Metal metal;
	boolean positionMetal;
	boolean girando;

	Deposito(){
		girando = true;
		System.out.println("Esteira de Deposito Iniciada");
	}
	
	//void TransportarMetal(){
	public void run(){
		synchronized(this){
			try {
					positionMetal = false;
					Thread.sleep(5000); //5 segundos
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
