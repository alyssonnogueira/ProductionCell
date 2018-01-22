			import java.*;
			import java.util.concurrent.*;
			import static java.util.concurrent.TimeUnit.*;

			public class main {

			/*	public static void main(String args[]){
			System.out.println("Deu certo");	
			}
			*/

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
						braco2.positionMetal = false;
						metal.Forjado = false;
						System.out.println("Ciclo chegou ao fim!");
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
					public void run() {
						deposito.girando = true; 
						if((deposito.metal != null) && (deposito.girando == true)){
							System.out.println("Deposito: Esteira de Deposito transportando Metal.. Aguarde!");
							deposito.run();
							synchronized(deposito){
								while (deposito.positionMetal == false);
							}
							deposito.girando = false;
							System.out.println("Deposito: Chegou ao fim!");
						} else {
			//System.out.println("Deposito: Sem Metal!");
						}
					}
				};
				final ScheduledFuture<?> depositoHandle =
				DepositoScheduler.scheduleAtFixedRate(depositoC, 1, 10, SECONDS);
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
							deposito.positionMetal = false; 
						guindaste.setMetal((deposito.getMetal())); //vai pra dentro da classe guindaste
						if(guindaste.metal != null){
							guindaste.positionMetal = false;
							System.out.println("Guindaste: Descendo para pegar o Metal.. Aguarde!");
							guindaste.down();
							System.out.println("Guindaste: Metal no guindaste, subindo.. Aguarde!");
							guindaste.up();
							System.out.println("Guindaste: Andando em direção a Esteira.. Aguarde!");
							guindaste.move();
							System.out.println("Guindaste: Metal largado na Esteira!");
							esteira.setMetal(guindaste.getMetal());    //vai pra dentro da classe guindaste
							System.out.println("Guindaste: Voltando a posição original!");
							guindaste.originalPosition();
						}
					} else {
						//System.out.println("Guindaste: Sem Metal!");
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
					esteira.girando = true;
					if((esteira.metal != null) && (esteira.girando == true)){
						System.out.println("Esteira: Esteira de Alimentação transportando Metal.. Aguarde!");
						esteira.run();
						synchronized(esteira){
							while (esteira.positionMetal == false);
						}
						esteira.girando = false;
						System.out.println("Esteira: Chegou ao fim!");
					} else {
						//System.out.println("Esteira: Sem Metal!");
					}
				}
			};
			final ScheduledFuture<?> esteiraHandle = EsteiraScheduler.scheduleAtFixedRate(esteiraC, 1, 10, SECONDS);
			EsteiraScheduler.schedule(new Runnable() { public void run() 
				{ esteiraHandle.cancel(true); } }, 12, HOURS);
		}

			//Controller do MesaGiratoria
		private static final ScheduledExecutorService MesaScheduler = Executors.newScheduledThreadPool(1);
		public static void MesaController() {
			final Runnable mesaC = new Runnable() {
				public void run() { 
					//synchronized(esteira){
						if (esteira.positionMetal == true){ 
							esteira.positionMetal = false; 
							mesa.setMetal((esteira.getMetal()));
							mesa.positionMetal = false;
							if(mesa.metal != null){
								System.out.println("Mesa: Descendo para pegar o Metal.. Aguarde!");
								mesa.down();
								System.out.println("Mesa: Metal na mesa, girando 90ºC a direita.. Aguarde!");
								mesa.gira90D();
								System.out.println("Mesa: Esperando Robo pegar o metal!");
								mesa.waiting();
								System.out.println("Mesa: Voltando a posição original 90ºC a esquerda!");
								mesa.originalPosition();
								System.out.println("Mesa: Em posição!");
							} else {
								//System.out.println("Mesa Giratória: Sem Metal!");
							}
						}
					//}
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
						synchronized (mesa) {
							mesa.notify();
						}
						mesa.positionMetal = false;
						System.out.println("Robo - Braco1: Pegando o Metal da mesa.. Aguarde!");
						braco1.setMetal((mesa.getMetal()));
						braco1.positionMetal = false;
						if(braco1.metal != null){
							System.out.println("Robo - Braco1: Girando Robo para 180ºC..");
							braco1.gira180();
							System.out.println("Robo - Braco1: Colocando Metal na Prensa.. Aguarde!");
							braco1.metalnaPrensa();
							synchronized(braco1){
								while (braco1.positionMetal == false);
							}
							System.out.println("Robo - Braco1: Colocando Braco2 em posição!");
							braco1.posicionaBraco2();
							prensa.setMetal(braco1.getMetal());
						} else {
							System.out.println("Braco1: Sem Metal!");

						}
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
						braco2.positionMetal = false;
						System.out.println("Robo - Braco2: Retirando metal da prensa.. Aguarde!");
						braco2.setMetal(prensa.getMetal());	
						if(braco2.metal != null){
							System.out.println("Robo - Braco2: Indo para a esteira de Depósito!");
							braco2.move180();
							System.out.println("Robo - Braco2: Soltando Metal na esteira de Depósito.. Aguarde!");
							deposito.setMetal(braco2.getMetal());
							braco2.positionMetal = true;
							System.out.println("Robo - Braco2: Metal na esteira de Depósito");
							System.out.println("Robo - Braco2: Posicionando Braco1 na mesa giratória!");
							braco2.posicionaBraco1();
						} else {
			//System.out.println("Braco2: Sem Metal!");

						}
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
					if((prensa.metal != null) && (metal.Forjado == false)){
						prensa.positionMetal = false;
						metal.Forjado = false;
						System.out.println("Prensa: Fechando a prensa!");
						prensa.FechaPrensa();
						System.out.println("Prensa: Metal sendo forjado.. Aguarde!");
						prensa.ForjarMetal();
						synchronized(prensa){
							while (metal.Forjado == false);
						}
						System.out.println("Prensa: Abrindo a Prensa!");
						prensa.AbrePrensa();
						synchronized(prensa){
							while (prensa.positionMetal == false);
						}
						System.out.println("Prensa: Aguardando Braco2 retirar o Metal!");
					} else {
				//System.out.println("Prensa: Sem Metal!");

					}

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

	}
