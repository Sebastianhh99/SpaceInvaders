package presentacion;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class panelConfiguracion extends JPanel {
	
	private SpaceInvadersGUI gui;
	
	private Image fondo;
	private JButton salve, abrir, importe, exporte, nuevo, volver;
	
	private int ancho, alto;
	
	
	/**
	 * Constructor de la pantalla de configuracion 
	 * @param g SpaceInvadersGUI JFrame
	 * @param ancho pantalla 
	 * @param alto pantalla 
	 */
	public panelConfiguracion(SpaceInvadersGUI g,int ancho,int alto) {
		this.ancho=ancho;
		this.alto=alto;
		
		prepareElementos(g);
		prepareAcciones();
		setVisible(true);
	}
	
	/**
	 * Prepara los elementos de la pantalla 
	 * @param g frame principal 
	 */
	public void prepareElementos(SpaceInvadersGUI g) {
		gui = g;
		setLayout(null);
		this.setOpaque(false);
		prepareBotones();
	}
	
	/**
	 * Prepara los botones de la pantalla
	 */
	public void prepareBotones() {
		salve = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Salve.png")));
		salve.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Salve2.png")));
		salve.setBorderPainted(false);
		
		abrir = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Abrir.png")));
		abrir.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Abrir2.png")));
		abrir.setBorderPainted(false);
		
		importe = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Importe.png")));
		importe.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Importe2.png")));
		importe.setBorderPainted(false);
		
		exporte = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Exporte.png")));
		exporte.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Exporte2.png")));
		exporte.setBorderPainted(false);
		
		nuevo = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Nuevo.png")));
		nuevo.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Nuevo2.png")));
		nuevo.setBorderPainted(false);
		
		volver = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Back.png")));
		volver.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Back2.png")));
		volver.setBorderPainted(false);
		
		salve.setBounds((ancho/2)+55,50,370,120);
		abrir.setBounds(215,50,370,120);
		importe.setBounds((ancho/2)+55,220,370,120);
		exporte.setBounds(215,220,370,120);
		nuevo.setBounds(215,390,370,120);
		volver.setBounds((ancho/2)+55,390,370,120);
		add(salve);
		add(abrir);
		add(importe);
		add(exporte);
		add(nuevo);
		add(volver);
	}
	
	/**
	 * Prepara las acciones de cada uno de los botones 
	 */
	public void prepareAcciones(){
		nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.menuSelecion();	
			}
			
		});
		
		abrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.opcionAbra();
				gui.juegoReal();
			}
		});
		
		salve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.opcionSalve();
			}
		});
		
		importe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.opcionImporte();	
			}
		});
		exporte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.opcionExporte();	
			}
		});
		
		volver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.juegoReal();
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