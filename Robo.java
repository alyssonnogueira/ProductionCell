//package ProductionCell;
import javax.realtime.*;

public class Robo extends RealtimeThread {
	Metal metal;
	boolean positionMetal;
	int position; 

	Robo(){
		position = 90;
		System.out.println("Motor do Robo ligado!");
	}
	
	void Position45(){
		while (position != 45){
			if(position > 45){
				position -= 1;
			} else {
				position += 1;
			}
		}
	}
	
	void Position90(){
		while (position != 90){
			if(position > 90){
				position -= 1;
			} else {
				position += 1;
			}
		}
	}
	
	void Position180(){
		while (position != 180){
			if(position > 180){
				position -= 1;
			} else {
				position += 1;
			}
		}
	}
	
	Metal getMetal(){
		Metal metal = this.metal;
		this.metal = null;
		return metal;
	}

	void setMetal(Metal metal){
		this.metal = metal;
	}

}
