package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.*;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.swing.*;


public class panelSeleccion extends JPanel{
	
	private SpaceInvadersGUI gui;
	
	
	private static Font fuente;
	private JLabel nombreT, nFila, nColumna;
	private JLabel nombreC, imagenNave1, imagenNave2, jugador1, jugador2;
	private JLabel nombreJ;
	private JLabel nombreE;
	private JButton play, volver,instrucciones;
	private JTextField filas, columnas;
	private JLabel pul, cal, can, pla, r, v;
	private JCheckBox siPul, siCal, siCan, siPla, siR, siV;
	
	private JComboBox<String> colorNaves1 , colorNaves2, tipoJuego;
	private String juego;
	
	private Image fondo;
	int ancho, alto;
	HashMap<String,Integer> aliens = new HashMap<String, Integer>();
	HashMap<Integer, String> nave = new HashMap<Integer, String>();
	
	
	/**
	 * constructor para la pantalla de seleccion 
	 * @param el JFrame  
	 * @param el ancho de la pantalla 
	 * @param el alto de la pantalla
	 */
	public panelSeleccion(SpaceInvadersGUI g,int ancho,int alto) {
		
		this.ancho=ancho;
		this.alto=alto;
		prepareFuente();
		prepareElementos(g);
		prepareAcciones();
		this.setVisible(true);
	}

	/**
	 * Prepara los elementos iniciales de la pantalla
	 * @param el JFrame
	 */
	public void prepareElementos(SpaceInvadersGUI g) {
		gui=g;
		this.setLayout(null);
		medidasJuego();
		elementosJuego();
		botones();
		coloresJ1();
		coloresJ2();
		tipoJuego();
	}
	
	/**
	 * Prepara los elementos para que el usuario elija las medidas de juego 
	 */
	public void medidasJuego() { 
		nFila = new JLabel("Filas: ");
		nFila.setForeground(Color.WHITE);
		nFila.setFont(fuente);
		nColumna = new JLabel("Columnas: ");
		nColumna.setForeground(Color.WHITE);
		nColumna.setFont(fuente);
		nombreT = new JLabel("Medida del Juego");
		nombreT.setForeground(Color.WHITE);
		nombreT.setFont(fuente);
		filas = new JTextField("18");
		filas.setFont(fuente);
		columnas = new JTextField("30");
		columnas.setFont(fuente);
		
		nombreT.setBounds(ancho/25,alto/18,300,40);
		nFila.setBounds(ancho/25,alto/7,100,40);
		nColumna.setBounds(ancho/25,alto/4,200,40);
		filas.setBounds(ancho/6,alto/7,100,40);
		columnas.setBounds(ancho/6,alto/4,100,40);
		
		add(nombreT);
		add(nColumna);
		add(nFila);
		add(filas);
		add(columnas);
	}
	
	/**
	 * Prepara los botones
	 */
	public void botones() {
		play =  new JButton(new ImageIcon(getClass().getResource("/res/Botones/Untitled.png")));
		play.setRolloverIcon(new ImageIcon(getClass().getResource("/res/Botones/Untitled1.png")));
		volver =  new JButton(new ImageIcon(getClass().getResource("/res/Botones/Back.png")));
		volver.setRolloverIcon(new ImageIcon(getClass().getResource("/res/Botones/Back2.png")));
		instrucciones =  new JButton(new ImageIcon(getClass().getResource("/res/Botones/Instrucciones.png")));
		instrucciones.setRolloverIcon(new ImageIcon(getClass().getResource("/res/Botones/Instrucciones2.png")));

		play.setBounds(ancho-300,(alto/4)*3,250,100);
		volver.setBounds(ancho-600,(alto/4)*3,250,100);
		instrucciones.setBounds(ancho/18,(alto/4)*3,570,100);
		
		add(play);
		add(volver);
		add(instrucciones);
	}
	
	/**
	 * Prepara los elementos para que el usuario elija el color del jugador 1
	 */
	public void coloresJ1(){
		//Color naves
		nombreC = new JLabel("Color de la(s) nave(s)");
		nombreC.setForeground(Color.WHITE);
		nombreC.setFont(fuente);
		jugador1 = new JLabel("Jugador 1");
		jugador1.setForeground(Color.WHITE);
		jugador1.setFont(fuente);
		colorNaves1 = new JComboBox<String>();
		colorNaves1.addItem("Verde");
		colorNaves1.addItem("Amarillo");
		colorNaves1.addItem("Blanco");
		colorNaves1.addItem("Cafe");
		colorNaves1.addItem("Dorado");
		colorNaves1.addItem("Indigo");
		colorNaves1.addItem("Lavanda");
		colorNaves1.addItem("Morado");
		colorNaves1.addItem("Naranja");
		colorNaves1.addItem("Rojo");
		colorNaves1.addItem("Rosa");
		colorNaves1.addItem("Turquesa");
		colorNaves1.setSelectedIndex(0);
		colorNaves1.setFont(fuente);
		imagenNave1 = new JLabel(new ImageIcon(getClass().getResource("/res/Nave1/Verde.png")));
		nave.put(0,"verde");
		add(nombreC);
		add(jugador1);
		add(colorNaves1);
		add(imagenNave1);
		
	}
	
	/**
	 * Prepara los elementos para que el usuario elija el color del jugador 2
	 */
	public void coloresJ2() {
		
		jugador2 = new JLabel("Jugador 2");
		jugador2.setForeground(Color.WHITE);
		jugador2.setFont(fuente);
		colorNaves2 = new JComboBox<String>();
		colorNaves2.addItem("Verde");
		colorNaves2.addItem("Amarillo");
		colorNaves2.addItem("Blanco");
		colorNaves2.addItem("Cafe");
		colorNaves2.addItem("Dorado");
		colorNaves2.addItem("Indigo");
		colorNaves2.addItem("Lavanda");
		colorNaves2.addItem("Morado");
		colorNaves2.addItem("Naranja");
		colorNaves2.addItem("Rojo");
		colorNaves2.addItem("Rosa");
		colorNaves2.addItem("Turquesa");
		colorNaves2.setSelectedIndex(0);
		colorNaves2.setFont(fuente);
		imagenNave2 = new JLabel(new ImageIcon(getClass().getResource("/res/Nave1/Verde.png")));
		nave.put(1,"verde");
		//Color naves
		nombreC.setBounds((ancho/3),alto/18,300,40);
		jugador1.setBounds((ancho/3),alto/8,150,40);
		jugador2.setBounds((ancho/2),alto/8,150,40);
		colorNaves1.setBounds((ancho/3), alto/5,  150, 40);
		imagenNave1.setBounds((ancho/3), alto/3, 150, 40);
		colorNaves2.setBounds(ancho/2, alto/5,  150, 40);
		imagenNave2.setBounds(ancho/2, alto/3, 150, 40);
		//Color naves
		add(jugador2);
		add(colorNaves2);
		add(imagenNave2);
	}
	
	/**
	 * Prepara los elementos para que el usuario elija el tipo de juego 
	 */
	public void tipoJuego() {
		//Tipo de juego
		nombreJ = new JLabel("Tipo de Juego");
		nombreJ.setForeground(Color.WHITE);
		nombreJ.setFont(fuente);
		tipoJuego = new JComboBox<String>();
		tipoJuego.addItem("Un Solo Jugador");
		tipoJuego.addItem("Jugador vs Consola");
		tipoJuego.addItem("Jugador vs Jugador");
		tipoJuego.setSelectedIndex(0);
		juego = "Un Solo Jugador";
		tipoJuego.setFont(fuente);
		//Tipo de juego
		nombreJ.setBounds((ancho/3)*2, alto/18, 300, 40);
		tipoJuego.setBounds((ancho/3)*2, alto/8, 300, 40);
		//Tipo de juego
		add(nombreJ);
		add(tipoJuego);
		
	}
	
	/**
	 * Prepara los elementos para que el usuario elija los elementos que aparezcan en el juego
	 */
	public void elementosJuego() {
		//Elementos en el juego 
		nombreE = new JLabel("Elementos en el juego");
		nombreE.setForeground(Color.WHITE);
		nombreE.setFont(fuente);
		
		siPul = new JCheckBox("Pulpo",true);
		aliens.put("Pulpo", 1);
		pul = new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/Pulpo.png")));
		siPul.setOpaque(false);
		siPul.setForeground(Color.WHITE);
		siPul.setFont(fuente);
		cal = new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/Calamar.png")));
		siCal = new JCheckBox("Calamar",true);
		aliens.put("Calamar", 1);
		siCal.setOpaque(false);
		siCal.setForeground(Color.WHITE);
		siCal.setFont(fuente);
		can = new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/Cangrejo.png")));
		siCan = new JCheckBox("Cangrejo",true);
		aliens.put("Cangrejo", 1);
		siCan.setOpaque(false);
		siCan.setForeground(Color.WHITE);
		siCan.setFont(fuente);
		pla =  new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/Platillo.png")));
		siPla = new JCheckBox("Platillo",true);
		aliens.put("Platillo", 1);
		siPla.setFont(fuente);
		siPla.setOpaque(false);
		siPla.setForeground(Color.WHITE);
		r = new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/BarreraR.png")));
		siR = new JCheckBox("Barrera Roja");
		aliens.put("Roja", 0);
		siR.setFont(fuente);
		siR.setOpaque(false);
		siR.setForeground(Color.WHITE);
		v = new JLabel(new ImageIcon(getClass().getResource("/res/aliens1/BarreraV.png")));
		siV = new JCheckBox("Barrera Verde",true); 
		aliens.put("Verde", 1);
		siV.setFont(fuente);
		siV.setOpaque(false);
		siV.setForeground(Color.WHITE);
		//Elementos en el juego 
		nombreE.setBounds(ancho/25, 300, 300, 40);
		pul.setBounds(ancho/25, 350, 118, 88);
		siPul.setBounds(ancho/25,450,150,40);
		cal.setBounds(ancho/25*4, 350, 91, 87);
		siCal.setBounds(ancho/25*4,450,150,40);
		can.setBounds(ancho/25*7, 350, 103, 85);
		siCan.setBounds(ancho/25*7,450,200,40);
		pla.setBounds(ancho/25*11,350, 109, 72);
		siPla.setBounds(ancho/25*11,450,150,40);
		v.setBounds(ancho/25*15, 350, 113, 94);
		siV.setBounds(ancho/25*14,450,250,40);
		r.setBounds(ancho/25*20, 350, 117, 89);
		siR.setBounds(ancho/25*19,450,200,40);	
		//Elementos en el juego 
		add(nombreE);
		add(pul);
		add(siPul);
		add(cal);
		add(siCal);
		add(can);
		add(siCan);
		add(pla);
		add(siPla);
		add(v);
		add(siV);
		add(r);
		add(siR);
		
	}
	
	
	/**
	 * Prepara acciones 
	 */
	public void prepareAcciones(){
		colorNaves1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (colorNaves1.getSelectedIndex()== 0) {
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Verde.png")));
						nave.put(0,"verde");
					}
					else if (colorNaves1.getSelectedIndex()== 1){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Amarillo.png")));
						nave.put(0,"amarillo");
					}
					else if (colorNaves1.getSelectedIndex()== 2){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Blanco.png")));
						nave.put(0,"blanco");
					}
					else if (colorNaves1.getSelectedIndex()== 3){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Cafe.png")));
						nave.put(0,"cafe");
					}
					else if (colorNaves1.getSelectedIndex()== 4){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Dorado.png")));
						nave.put(0,"dorado");
					}
					else if (colorNaves1.getSelectedIndex()== 5){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Indigo.png")));
						nave.put(0,"indigo");
					}
					else if (colorNaves1.getSelectedIndex()== 6){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Lavanda.png")));
						nave.put(0,"lavanda");
					}
					else if (colorNaves1.getSelectedIndex()== 7){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Morado.png")));
						nave.put(0,"morado");
					}
					else if (colorNaves1.getSelectedIndex()== 8){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Naranja.png")));
						nave.put(0,"naranja");
					}
					else if (colorNaves1.getSelectedIndex()== 9){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Rojo.png")));
						nave.put(0,"rojo");
					}
					else if (colorNaves1.getSelectedIndex()== 10){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Rosa.png")));
						nave.put(0,"rosa");
					}
					else if (colorNaves1.getSelectedIndex()== 11){
						imagenNave1.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Turquesa.png")));
						nave.put(0,"turquesa");
					}
				}
			
			}
		});
		
		colorNaves2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (colorNaves2.getSelectedIndex()==0) {
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Verde.png")));
						nave.put(1,"verde");
					}
					else if (colorNaves2.getSelectedIndex()== 1){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Amarillo.png")));
						nave.put(1,"amarillo");
					}
					else if (colorNaves2.getSelectedIndex()== 2){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Blanco.png")));
						nave.put(1,"blanco");
					}
					else if (colorNaves2.getSelectedIndex()== 3){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Cafe.png")));
						nave.put(1,"cafe");
					}
					else if (colorNaves2.getSelectedIndex()== 4){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Dorado.png")));
						nave.put(1,"dorado");
					}
					else if (colorNaves2.getSelectedIndex()== 5){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Indigo.png")));
						nave.put(1,"indigo");
					}
					else if (colorNaves2.getSelectedIndex()== 6){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Lavanda.png")));
						nave.put(1,"lavanda");
					}
					else if (colorNaves2.getSelectedIndex()== 7){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Morado.png")));
						nave.put(1,"morado");
					}
					else if (colorNaves2.getSelectedIndex()== 8){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Naranja.png")));
						nave.put(1,"naranja");
					}
					else if (colorNaves2.getSelectedIndex()== 9){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Rojo.png")));
						nave.put(1,"rojo");
					}
					else if (colorNaves2.getSelectedIndex()== 10){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Rosa.png")));
						nave.put(1,"rosa");
					}
					else if (colorNaves2.getSelectedIndex()== 11){
						imagenNave2.setIcon(new ImageIcon(getClass().getResource("/res/Nave1/Turquesa.png")));
						nave.put(1,"turquesa");
					}
				}
			}
		});
		
		
		tipoJuego.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (tipoJuego.getSelectedIndex()==0) {
						juego = "Un Solo Jugador";
					}
					else if (tipoJuego.getSelectedIndex()== 1){
						juego = "Jugador vs Consola";
						
					}
					else {
						juego = "Jugador vs Jugador";
					}
				}
			}
		});
		
		siPul.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Pulpo", 1);
				}
				else {
					aliens.put("Pulpo", 0);
				}
			}
		});
		
		siCal.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Calamar", 1);
				}
				else {
					aliens.put("Calamar", 0);
				}
			}
		});
		
		siCan.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Cangrejo", 1);
				}
				else {
					aliens.put("Cangrejo", 0);
				}
			}
		});
		
		siPla.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Platillo", 1);
				}
				else {
					aliens.put("Platillo", 0);
				}
			}
		});
		
		siV.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Verde", 1);
				}
				else {
					aliens.put("Verde", 0);
				}
			}
		});
		
		siR.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					aliens.put("Roja", 1);
				}
				else {
					aliens.put("Roja", 0);
				}
			}
		});
		
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				if (filas.getText().trim().isEmpty() || columnas.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"No se conocen filas o columnas","Alerta", JOptionPane.ERROR_MESSAGE);
				}
				else if (filas.getText().trim().equals("0") || columnas.getText().trim().equals("0") ) {
					JOptionPane.showMessageDialog(null,"Filas o columnas es cero","Alerta", JOptionPane.ERROR_MESSAGE);
				}
				else {
					gui.juegoReal(filas.getText(),columnas.getText(),nave,aliens,juego);
					
				}
			}
		});
		
		volver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				gui.menuPrincipal();
							
			}			
		});
		
		
		instrucciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				gui.panelInstrucciones();
				
							
			}			
		});
	}
		
	
	
	
	
	/**
	 * Cambia el tipo de fuente 
	 */
	public void prepareFuente() {
		InputStream myStream = null;
		try {
			myStream = new BufferedInputStream(new FileInputStream("src/res/Font/chinese.sthupo.ttf"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fuente = fuente.deriveFont(Font.PLAIN, 30);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(fuente);
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
