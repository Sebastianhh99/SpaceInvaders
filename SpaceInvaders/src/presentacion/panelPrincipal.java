package presentacion;

import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class panelPrincipal extends JPanel{
	
	private Image fondo;
	private SpaceInvadersGUI gui;
	
	private JButton inicio;
	private int ancho, alto;
	
	/**
	 * Constructor para la pantalla principal 
	 * @param el JFrame  
	 * @param el ancho de la pantalla
	 * @param el alto de la pantalla
	 */
	public panelPrincipal(SpaceInvadersGUI g,int ancho,int alto) {
		
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
	private void prepareElementos(SpaceInvadersGUI g){
		gui=g;
		setLayout(null);
		this.setOpaque(false);
		prepareBotones();
		
	}
	
	/**
	 * Prepara los botones de la pantalla 
	 */
	public void prepareBotones() {
		inicio = new JButton(new ImageIcon(gui.getClass().getResource("/res/Botones/Untitled.png")));
		inicio.setRolloverIcon(new ImageIcon(gui.getClass().getResource("/res/Botones/Untitled1.png")));
		inicio.setBorderPainted(false);
		
		inicio.setBounds(ancho-400,alto-200,370,120);
		add(inicio);
	}
	
	/**
	 * Prepara las acciones de los botones correspondientes
	 */
	public void prepareAcciones(){
		inicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.menuSelecion();	
			}
		});
	}
	
	/**
	 * Pinta los elementos de la pantalla
	 */
	@Override
	public void paint(Graphics g) {
		fondo = new ImageIcon(gui.getClass().getResource("/res/Fondos/H39Zs6Z.png")).getImage();
		super.paintComponent(g);
		g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
		
	}
	
}