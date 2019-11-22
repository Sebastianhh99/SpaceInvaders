package presentacion;

import aplicacion.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class panelJugadores2 extends panelJugadores{
	private JLabel sNave1, sNave2, vNave1, vNave2;
	
	/**
	 * Constructor de la pantalla de juego cuando son dos jugadores
	 * @param g SpaceInvadersGUI JFrame
	 * @param ancho pantalla 
	 * @param alto pantalla
	 * @param game el juego desde la capa de aplicacion 
	 * @param nave HashMap con los dos tanques y los colores que el usuario escogió
	 * @param aliens HashMap con los elementos que escogio el usuario
	 * @throws SpaceException 
	 */
	public panelJugadores2(SpaceInvadersGUI g,int ancho,int alto,SpaceInvaders game,HashMap<Integer, String> nave,HashMap<String, Integer> aliens) throws SpaceException{
		super(g,ancho,alto,game,nave,aliens);
		prepareFuente();
	}
	
	/**
	 * Agrega la cantidad de vidas que tiene cada jugador 
	 */
	@Override
	public void getLives(){
		int y=alto-100;
		JLabel live1 = new JLabel("Lives ");
		live1.setForeground(Color.WHITE);
		live1.setFont(fuente);
		live1.setBounds(15,y, 80, 42);
		add(live1);
		int x1=80;
		String img = vidas.get(nave.get(0));
		for(int i=0; i<tanques.getFirst().getVidas(); i++ ) {
			vNave1 = new JLabel(new ImageIcon(gui.getClass().getResource(img)));
			vNave1.setBounds(x1,y, 40, 42);
			add(vNave1);
			x1+=40;
		}
		
		JLabel live2 = new JLabel("Lives ");
		live2.setForeground(Color.WHITE);
		live2.setFont(fuente);
		live2.setBounds(ancho/2,y, 80, 42);
		add(live2);
		int x2=(ancho/2)+80;
		String img2 = vidas.get(nave.get(1));
		for(int i=0; i<tanques.getLast().getVidas(); i++ ) {
			vNave2 = new JLabel(new ImageIcon(gui.getClass().getResource(img2)));
			vNave2.setBounds(x2,y, 40, 42);
			add(vNave2);
			x2+=40;
		}
		
	}

	/**
	 * Agrega el puntaje de cada jugador 
	 */
	@Override
	public void pinteScore() {
		int num1 = tanques.get(0).getScore();
		sNave1 = new JLabel("Score "+ Integer.toString(num1));
		sNave1.setForeground(Color.WHITE);
		sNave1.setFont(fuente);
		sNave1.setBounds(ancho/6,alto-100, 420, 42);
		add(sNave1);	
		
		int num2 = tanques.get(1).getScore();
		sNave2 = new JLabel("Score "+ Integer.toString(num2));
		sNave2.setForeground(Color.WHITE);
		sNave2.setFont(fuente);
		sNave2.setBounds(ancho/6*4,alto-100, 420, 42);
		add(sNave2);
	}
	
	
	
	/**
	 * Pinta los elementos de la pantalla
	 */
	public void paintComponent(Graphics g) {
		removeAll();
		fondo = new ImageIcon(gui.getClass().getResource("/res/Fondos/bc2d5e7b9a3c8e8b7c384905235d0f6e.jpg")).getImage();
		super.paintComponent(g);
		g.drawImage(fondo,0,0,gui.getWidth(), gui.getHeight(),null);
		pinteScore();
		getLives();	
		pinteAliens();
		pinteNaves();
		pinteBalas();
	}
	
}
