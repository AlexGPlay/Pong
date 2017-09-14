package Logica;

public class Ball {

	private int x;
	private int y;

	private double moveX;
	private double moveY;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		moveX = (Math.random()*2)+1;
		moveY = (Math.random()*2)+1;

		if(Math.random()>0.5)
			moveX *= -1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move() {
		x += moveX;
		y += moveY;

		if(y<=0 || y+Data.DIAMETRO_BOLA>=Data.TABLERO_ALTO)
			moveY *= -1;
	}

	public void choque(Paddle p, int paleta) {
		if(paleta == 0) {
			if(this.x + moveX <= p.getX() && this.x + moveX <= p.getX()+Data.PALETA_ANCHO && this.y >= p.getY() && this.y<= p.getY()+Data.PALETA_ALTO) {
				moveX -= Data.ACELERACION_BOLA;
				moveX *= -1;
			}
		}
		
		else if(paleta == 1) {
			if(this.x + moveX >= p.getX() && this.x + moveX <= p.getX()+Data.PALETA_ANCHO && this.y >= p.getY() && this.y<= p.getY()+Data.PALETA_ALTO) {
				moveX += Data.ACELERACION_BOLA;
				moveX *= -1;
			}
		}
	}
	
	public void restartBall(int goal) {
		moveX = (Math.random()*2)+1;
		moveY = (Math.random()*2)+1;
		
		setX(Data.TABLERO_ANCHO/2);
		setY(Data.TABLERO_ALTO/2);
		
		if(goal == 1)
			moveX *= -1;
	}
	
	public double geXMove() {
		return moveX;
	}

}
