package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import Logica.Data;
import Logica.GameController;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = -2693863158529022511L;
	private GameController control;
	private int pressed;
	private boolean started;
	private Timer timer;
	
	public GamePanel(GameController control) {
		this.control = control;
		pressed = 0;
		started = false;
		setBackground(Data.FONDO_TABLERO);
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "Up");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "Down");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '), "Start");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "Stop");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "Stop");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Pause");

		this.getActionMap().put("Up", new NewDirections(Data.MOVIMIENTO.UP));
		this.getActionMap().put("Down", new NewDirections(Data.MOVIMIENTO.DOWN));
		this.getActionMap().put("Stop", new NewDirections(Data.MOVIMIENTO.NONE));
		this.getActionMap().put("Start", new StartGame());
	}
	
	public Timer getTimer() {
		if(timer == null) {
			timer = new Timer(10, new GameAction());
		}
		
		return timer;
	}
	
	public void refreshGame() {
		control.moveAll();
		this.repaint();
		
		if(control.isGoal()) {
			getTimer().stop();
			control.startGoal();
			this.repaint();
			started = false;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Pintar paletas
		g.setColor(Data.COLOR_PALETA);
		g.fillRect(control.getJugador().getX(), control.getJugador().getY(), Data.PALETA_ANCHO, Data.PALETA_ALTO);
		g.fillRect(control.getPC().getX(), control.getPC().getY(), Data.PALETA_ANCHO, Data.PALETA_ALTO);
		
		//Pintar bola
		g.setColor(Data.COLOR_BOLA);
		g.fillOval(control.getBall().getX(), control.getBall().getY(), Data.DIAMETRO_BOLA, Data.DIAMETRO_BOLA);
		
		//Pintar puntos
		g.setColor(Data.COLOR_BOLA);
		g.setFont(Data.FUENTE_SCORE);
		g.drawString(control.getPlayerGoals() + " | " + control.getIAGoals(), Data.TABLERO_ANCHO/2 - 40, 30);
	}
	
	private class GameAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			refreshGame();
		}		
	}
	
	private class NewDirections extends AbstractAction{

		private static final long serialVersionUID = -7297609249453290054L;
		private Data.MOVIMIENTO dir;
		
		public NewDirections(Data.MOVIMIENTO dir) {
			if(dir != Data.MOVIMIENTO.NONE)
				pressed++;
			
			else
				pressed--;
			
			if(dir == Data.MOVIMIENTO.NONE && pressed == 0)
				this.dir = dir;
			
			else
				this.dir = dir;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			control.getJugador().setDirection(dir);
		}
		
	}
	
	public class StartGame extends AbstractAction{

		private static final long serialVersionUID = -1751838348404053053L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!started) {
				started = true;
				getTimer().start();
			}
		}
		
	}

}
