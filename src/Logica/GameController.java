package Logica;

public class GameController {

	private Paddle jugador;
	private Paddle ia;
	
	private Ball bola;
	
	private int goalsPlayer;
	private int goalsIA;
	private boolean goalPlayer;
	private boolean goalIA;
	
	public GameController() {
		jugador = new Paddle(10, Data.TABLERO_ALTO/3);
		ia = new Paddle(Data.TABLERO_ANCHO-20, Data.TABLERO_ALTO/3);
		bola = new Ball(Data.TABLERO_ANCHO/2, Data.TABLERO_ALTO/2);
		
		goalsPlayer = 0;
		goalsIA = 0;
		goalPlayer = false;
		goalIA = false;
	}
	
	public Paddle getJugador() {
		return jugador;
	}
	
	public Paddle getPC() {
		return ia;
	}
	
	public Ball getBall() {
		return bola;
	}
	
	public void moveAll() {
		moveIA();
		
		jugador.move();
		ia.move();
		bola.move();
		
		bola.choque(jugador,0);
		bola.choque(ia,1);
		
		checkGoal();
	}
	
	public void moveIA() {
		
		if(bola.getY() < ia.getY())
			ia.setDirection(Data.MOVIMIENTO.UP);
		
		else if(bola.getY() > ia.getY() + Data.PALETA_ALTO)
			ia.setDirection(Data.MOVIMIENTO.DOWN);
		
		else
			ia.setDirection(Data.MOVIMIENTO.NONE);
	}
	
	public void checkGoal() {
		if(bola.getX()<=0) {
			goalsIA++;
			goalIA = true;
		}
		
		else if(bola.getX()+Data.DIAMETRO_BOLA>=Data.TABLERO_ANCHO) {
			goalsPlayer++;
			goalPlayer = true;
		}
		
	}
	
	public int getPlayerGoals() {
		return goalsPlayer;
	}
	
	public int getIAGoals() {
		return goalsIA;
	}
	
	public boolean isGoal() {
		return goalPlayer || goalIA;
	}
	
	public void startGoal() {
		int gol = 0;
		
		if(goalIA)
			gol = 1;
		
		bola.restartBall(gol);
		ia.restartPaddle(Data.TABLERO_ANCHO-20, Data.TABLERO_ALTO/3);
		jugador.restartPaddle(10, Data.TABLERO_ALTO/3);
		
		goalPlayer = false;
		goalIA = false;
	}
	
	
}
