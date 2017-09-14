package Logica;

public class Paddle {

	private int x;
	private int y;
	private Data.MOVIMIENTO dir;
	
	public Paddle(int x, int y) {
		this.x = x;
		this.y = y;
		dir = Data.MOVIMIENTO.NONE;
	}
	
	public void restartPaddle(int x, int y) {
		this.x = x;
		this.y = y;
		dir = Data.MOVIMIENTO.NONE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDirection(Data.MOVIMIENTO dir) {
		this.dir = dir;
	}
	
	public void move() {
		
		switch(dir) {
		
		case UP:
			if(y>0)
				this.y -= Data.MOVIMIENTO_PALA;
			
			break;
			
		case DOWN:
			if(y+Data.PALETA_ALTO<Data.TABLERO_ALTO)
				this.y += Data.MOVIMIENTO_PALA;
			
			break;
			
		case NONE:
			break;
		}
		
	}
	
}
