//package ProductionCell;
import java.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

public class controler {
/*
	static public Metal metal;
	static public Deposito deposito;
	private static final ScheduledExecutorService DepositoScheduler = Executors.newScheduledThreadPool(1);
	static public Guindaste guindaste;
	static public Esteira esteira;
	static public MesaGiratoria mesa;
	static public Braco1 braco1;
	static public Braco2 braco2;
	static public Prensa prensa;
	
	public static void main(String[] args){
		inicia_sistema();
		int i = 0; 
		//beepForAnHour();
		//while(true){
			if(i == 0){
				System.out.println("Controller Pronto!");
				deposito.setMetal(metal);
				i++;
			}
			DepositoController();
			GuindasteController();
			EsteiraController();
			MesaController();
			Braco1Controller();
			PrensaController();
			Braco2Controller();
			
			while (true){
				if (braco2.positionMetal == true){
					deposito.setMetal(braco2.getMetal());
					System.out.println("Hey!");
					i++;
				}
				if ( i == 3){
					System.out.println("O Ciclo já executou 3 vezes e agora pode desligar");
					break;
				}
			}
	}
	
	//Controle da Esteira de Depósito
		   public static void DepositoController() {
		     final Runnable depositoC = new Runnable() {
		       public void run() { deposito.TransportarMetal(); }
		     };
		     final ScheduledFuture<?> depositoHandle =
		     DepositoScheduler.scheduleAtFixedRate(depositoC, 1, 1, SECONDS);
		     DepositoScheduler.schedule(new Runnable() {
		       public void run() { depositoHandle.cancel(true); }
		     }, 12, HOURS);
		   }
	
//Controller do Guindaste	
private static final ScheduledExecutorService GuindasteScheduler = Executors.newScheduledThreadPool(1);
	
	public static void GuindasteController() {
		final Runnable guindasteC = new Runnable() {
			public void run() { 
				if (deposito.positionMetal == true){
					deposito.girando = false;
					deposito.positionMetal = false; 
					guindaste.setMetal((deposito.getMetal())); //vai pra dentro da classe guindaste
					guindaste.TransportarMetal();				// fica
					esteira.setMetal(guindaste.getMetal());    //vai pra dentro da classe guindaste
				}
			}
		};
		final ScheduledFuture<?> guindasteHandle = GuindasteScheduler.scheduleAtFixedRate(guindasteC, 1, 1, SECONDS);
		GuindasteScheduler.schedule(new Runnable() { public void run() 
		{ guindasteHandle.cancel(true); } }, 12, HOURS);
}

//Controller do Esteira
private static final ScheduledExecutorService EsteiraScheduler = Executors.newScheduledThreadPool(1);
public static void EsteiraController() {
			final Runnable esteiraC = new Runnable() {
				public void run() { 
						esteira.TransportarMetal();
				}
			};
			final ScheduledFuture<?> esteiraHandle = EsteiraScheduler.scheduleAtFixedRate(esteiraC, 1, 1, SECONDS);
			EsteiraScheduler.schedule(new Runnable() { public void run() 
			{ esteiraHandle.cancel(true); } }, 12, HOURS);
	}

//Controller do MesaGiratoria
private static final ScheduledExecutorService MesaScheduler = Executors.newScheduledThreadPool(1);
public static void MesaController() {
			final Runnable mesaC = new Runnable() {
				public void run() { 
					if (esteira.positionMetal == true){ 
						esteira.girando = false;
						esteira.positionMetal = false; 
						mesa.setMetal((esteira.getMetal())); //vai pra dentro da classe Mesa
						mesa.TransportarMetal();
					}
				}
			};
			final ScheduledFuture<?> esteiraHandle = MesaScheduler.scheduleAtFixedRate(mesaC, 1, 1, SECONDS);
			MesaScheduler.schedule(new Runnable() { public void run() 
			{ esteiraHandle.cancel(true); } }, 12, HOURS);
	}

//Controller do Braco1
private static final ScheduledExecutorService Braco1Scheduler = Executors.newScheduledThreadPool(1);
public static void Braco1Controller() {
			final Runnable Braco1C = new Runnable() {
				public void run() { 
					//System.out.println(mesa.positionMetal);
					if (mesa.positionMetal == true){
						//mesa.notify();
						synchronized (mesa) {
							mesa.notify();
						}
						mesa.positionMetal = false;
						braco1.setMetal((mesa.getMetal()));
						braco1.TransportarMetal();
						prensa.setMetal(braco1.getMetal());
					}
				}
			};
			final ScheduledFuture<?> Braco1Handle = Braco1Scheduler.scheduleAtFixedRate(Braco1C, 1, 1, SECONDS);
			Braco1Scheduler.schedule(new Runnable() { public void run() 
			{ Braco1Handle.cancel(true); } }, 12, HOURS);
	}

//Controller do Braco2
private static final ScheduledExecutorService Braco2Scheduler = Executors.newScheduledThreadPool(1);
public static void Braco2Controller() {
			final Runnable Braco2C = new Runnable() {
				public void run() {
						if (metal.Forjado == true){
							braco2.setMetal(prensa.getMetal());//Por pra dentro da class braco2 
							braco2.TransportarMetal();
						}
				}
			};
			final ScheduledFuture<?> Braco2Handle = Braco2Scheduler.scheduleAtFixedRate(Braco2C, 1, 1, SECONDS);
			Braco2Scheduler.schedule(new Runnable() { public void run() 
			{ Braco2Handle.cancel(true); } }, 12, HOURS);
	}

//Controller do Prensa
private static final ScheduledExecutorService PrensaScheduler = Executors.newScheduledThreadPool(1);
public static void PrensaController() {
			final Runnable PrensaC = new Runnable() {
				public void run() { 
						prensa.ForjarMetal();
				}
			};
			final ScheduledFuture<?> PrensaHandle = PrensaScheduler.scheduleAtFixedRate(PrensaC, 1, 1, SECONDS);
			PrensaScheduler.schedule(new Runnable() { public void run() 
			{ PrensaHandle.cancel(true); } }, 12, HOURS);
	}

		   
public static void inicia_sistema(){
		metal = new Metal();
		deposito = new Deposito();
		guindaste = new Guindaste();
		esteira = new Esteira();
		mesa = new MesaGiratoria();
		braco1 = new Braco1();
		braco2 = new Braco2();
		prensa = new Prensa();
	}
	*/
}
