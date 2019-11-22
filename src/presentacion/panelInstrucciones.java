package presentacion;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class panelInstrucciones extends JPanel {
	
	private SpaceInvadersGUI gui;
	
	private Image fondo;
	private JButton volver;
	
	private int ancho, alto;
	
	/**
	 * Construcctor de la pantalla de Instrucciones 
	 * @param g SpaceInvadersGUI JFrame
	 * @param ancho pantalla 
	 * @param alto pantalla
	 */
	public panelInstrucciones(SpaceInvadersGUI g,int ancho,int alto) {
		this.ancho=ancho;
		this.alto=alto;
		
		prepareElementos(g);
		prepareAcciones();
		setVisible(true);
	}

	/**
	 * Prepara los elementos iniciales de la pantalla
	 * @param el JFrame
	 */
	public void prepareElementos(SpaceInvadersGUI g) {
		gui = g;
		setLayout(null);
		this.setOpaque(false);
		prepareBotones();
	}
	
	/**
	 * Prepara loe elementos dentro de la pantalla 
	 */
	public void prepareBotones() {
		volver = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Back.png")));
		volver.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Back2.png")));
		volver.setBorderPainted(false);
		
		volver.setBounds(ancho-400,alto-200,370,120);
		add(volver);
	}
	
	/**
	 * Prepara las acciones de los botones correspondientes
	 */
	public void prepareAcciones(){
		volver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.menuSelecion();	
			}
		});
	}
	
	/**
	 * Pinta los elementos de la pantalla
	 */
	public void paintComponent(Graphics g) {
		fondo = new ImageIcon(gui.getClass().getResource("/res/Fondos/retro-arcade-wallpaper-1.jpg")).getImage();
		super.paintComponent(g);
		g.drawImage(fondo,0,0,gui.getWidth(), gui.getHeight(),null);		
	}
}
