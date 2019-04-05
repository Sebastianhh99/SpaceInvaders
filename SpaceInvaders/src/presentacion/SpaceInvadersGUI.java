
package presentacion;

import aplicacion.*;
import aplicacion.SpaceInvaders;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import java.awt.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.*;


public class SpaceInvadersGUI extends JFrame {
	
	private static  Container cont;
	private static  CardLayout card = new CardLayout();
	
	private panelPrincipal inicial; //pantalla inicio
	private panelSeleccion seleccion; //menu inicio selección
	private panelInstrucciones instucciones; //Instucciones del juego
	private panelJugadores juego; // pantalla juego
	private panelJugadores2 juego1;
	private panelConfiguracion configuracion; //pantalla de configuracion
	private SpaceInvaders game;//modelo juego 
	private JFileChooser fc;
	private File archivo;
	private FileWriter archivotxt;
	private boolean[] teclas;
	private Timer mover;
	private TimerTask taskMover;
	
	protected int ancho, alto;
	public int filas, columnas;
	
	/**
	 * Constructor de la clase GUI	
	 */
	public SpaceInvadersGUI() {
		tiempo();
		prepareElementos();
		prepareAcciones();
		teclas= new boolean[]{false,false,false,false,false,false,false,false};
	}
	
	/**
	 * Para el movimiento
	 */
	public void stop() {
		mover.cancel();
		taskMover.cancel();
	}
	
	/**
	 * Sigue el movimiento
	 */
	public void siga() {
		tiempo();
	}
	
	/**
	 * Crea el tiempo del juego 
	 */
	public void tiempo() {
		mover = new Timer();
		taskMover=new TimerTask() {
			@Override
			public void run() {
				if(juego!=null) {
					if(teclas[0]==true) {
						juego.muevaseLeft(0);
					}
					if(teclas[1]==true) {
						juego.muevaseRight(0);
					}
					if(teclas[2]==true) {
						if(game.getBala1()==null) {
							juego.dispare(0);
						}
					}
					if(teclas[3]==true) {
						if(game.getBala1()==null) {
							juego.dispareEspecial(0);
						}
					}
					if(teclas[4]==true) {
						juego.muevaseLeft(1);
					}
					if(teclas[5]==true) {
						juego.muevaseRight(1);
					}
					if(teclas[6]==true) {
						if(game.getBala2()==null) {
							juego.dispare(1);
						}
					}
					if(teclas[7]==true) {
						if(game.getBala2()==null) {
							juego1.dispareEspecial(1);
						}
					}
				}
				if(juego1!=null) {
					if(teclas[0]==true) {
						juego1.muevaseLeft(0);
					}
					if(teclas[1]==true) {
						juego1.muevaseRight(0);
					}
					if(teclas[2]==true) {
						if(game.getBala1()==null) {
							juego1.dispare(0);
						}
					}
					if(teclas[3]==true) {
						if(game.getBala1()==null) {
							juego1.dispareEspecial(0);
						}
					}
					if(teclas[4]==true) {
						juego1.muevaseLeft(1);
					}
					if(teclas[5]==true) {
						juego1.muevaseRight(1);
					}
					if(teclas[6]==true) {
						if(game.getBala2()==null) {
							juego1.dispare(1);
						}
					}
					if(teclas[7]==true) {
						if(game.getBala2()==null) {
							juego1.dispareEspecial(1);
						}
					}
				}
			}
		};
		mover.schedule(taskMover, 30,30);
	}
	
	/**
	 * Prepara elementos del GUI inicial
	 */
	private void prepareElementos() {
		setResizable(false); //Tamaño de la pantalla no cambia
		// Cambio dimensiones de la pantalla
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		ancho = ((int)d.getWidth()/3)*2;
		alto = ((int)d.getHeight()/3)*2;
		setSize(ancho,alto);
		setLocationRelativeTo(null);// Localiza la pantalla en el centro 
		
		//Para los cambios de panel 
		cont = this.getContentPane();
		cont.setLayout(card);
		
		configuracion = new panelConfiguracion(this,ancho,alto);
		inicial = new panelPrincipal(this,ancho,alto);
		seleccion = new panelSeleccion(this,ancho,alto);
		instucciones = new panelInstrucciones(this,ancho,alto);
		
		card.addLayoutComponent(configuracion,"configuraciones");
		card.addLayoutComponent(inicial, "inicio");
		card.addLayoutComponent(seleccion, "seleccion");
		card.addLayoutComponent(instucciones, "instucciones");
		
		cont.add(configuracion);
		cont.add(inicial);
		cont.add(seleccion);
		cont.add(instucciones);
		
		card.show(cont, "inicio");
	}
	
	/**
	 * Guarda el juego en un archivo 
	 */
	public void opcionSalve() {
		fc=new JFileChooser();
		int retrival = fc.showSaveDialog(null);
		if(retrival==JFileChooser.APPROVE_OPTION) {
			try {
				archivo=new File(fc.getSelectedFile() + ".dat");
				game.salve(archivo);
			}catch(SpaceException e) {
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
	}
	
	/**
	 * Abre un archivo de juego 
	 */
	public void opcionAbra() {
		fc = new JFileChooser();
		int retrival = fc.showOpenDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile();
				game.abra(file);
			}
			 catch (SpaceException ev){
				 JOptionPane.showMessageDialog(this,ev.getMessage());
			}	
			catch (Exception ex) {
				ex.printStackTrace();
			
			}
		}
	}
	
	public void opcionExporte() {
		fc = new JFileChooser();
		int retrival= fc.showSaveDialog(null);
		if(retrival==JFileChooser.APPROVE_OPTION) {
			try {
				archivotxt=new FileWriter(fc.getSelectedFile() + ".txt");
				game.exporte(archivotxt);
			}catch(SpaceException e) {
				JOptionPane.showMessageDialog(this,e.getMessage());
			}catch(IOException e) {
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
	}
	
	public void opcionImporte() {
		if(juego!=null) {
			juego.stop();
		}
		else if(juego1!=null) {
			juego1.stop();
		}
		fc= new JFileChooser();
    	int retrival =fc.showOpenDialog(null);
    	if(retrival==JFileChooser.APPROVE_OPTION) {
    		try{
    			game.importe(new FileReader(fc.getSelectedFile()));
    		}catch(SpaceException e) {
    			JOptionPane.showMessageDialog(this,e.getMessage());
    		}catch(IOException e) {
    			JOptionPane.showMessageDialog(this,e.getMessage());
    		}
    	}
	}
	
	/**
	 * Muestra el panel de inicio 
	 */
	public void menuPrincipal() {
		card.show(cont,"inicio");
	}
	
	/**
	 * Muestra el panel de seleccion 
	 */
	public void menuSelecion() {
		if(juego!=null) {
			juego.stop();
		}
		else if(juego1!=null) {
			juego1.stop();
		}
		game=null;
		juego = null;
		juego1= null;
		card.show(cont,"seleccion");
	}
	
	/**
	 * Muestra el panel de instrucciones 
	 */
	public void panelInstrucciones(){
		card.show(cont,"instucciones");
	}
	
	/**
	 * Muestra el panel del juego y crea los listener de las teclas
	 */
	public void juegoReal(String fila, String columna,HashMap<Integer, String> nave,HashMap<String, Integer> aliens, String tipoJuego) {
		tiempo();
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A) {
					teclas[0]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					teclas[1]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_W) {
					teclas[2]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_S) {
					teclas[3]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					teclas[4]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					teclas[5]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					teclas[6]=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					teclas[7]=false;
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A) {
					teclas[0]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					teclas[1]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_W) {
					teclas[2]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					teclas[4]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_S) {
					teclas[3]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					teclas[5]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					teclas[6]=true;
				}
				else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					teclas[7]=true;
				}
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					if(juego!=null) {
						menuConfiguracion();
					}
					if(juego1!=null) {
						menuConfiguracion();
					}
				}
			}
		});
		
		filas = Integer.parseInt(fila);
		columnas = Integer.parseInt(columna);
		try {
			game = new SpaceInvaders (filas,columnas,ancho,alto,tipoJuego);
			if (tipoJuego.equals("Un Solo Jugador")) {
				juego = new panelJugadores(this,ancho,alto,game,nave,aliens);
			}
			else {
				juego1 = new panelJugadores2(this,ancho,alto,game,nave,aliens);
			}
		}catch(SpaceException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		if(juego!=null) {
			card.addLayoutComponent(juego, "juego");
			cont.add(juego);
			card.show(cont,"juego");
		}
		else if (juego1!=null) {
			card.addLayoutComponent(juego1, "juego");
			cont.add(juego1);
			card.show(cont,"juego");
		}
		
	}
	
	/**
	 * Muestra el panel de Configuracion
	 */
	public void menuConfiguracion(){
		if(juego!=null) {
			juego.stop();
		}
		else if(juego1!=null) {
			juego1.stop();
		}
		card.show(cont,"configuraciones");
	}
	
	/**
	 * Muestra el panel de juego, con un juego iniciado 
	 */
	public void juegoReal() {
		if(juego!=null) {
			juego.siga();
		}
		else if(juego1!=null) {
			juego1.siga();
		}
		card.show(cont,"juego");
	}
	
	/**
	 * Importa un achivo
	 */
	public void importaJuego() {
		
	}
	
	/**
	 * Exporta un archivo 
	 */
	public void exportaJuego() {
		
	}
	
	
	/**
	 * Prepara acciones de la ventana
	 */
	public void prepareAcciones() {
		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						 salga();
					}
				}
			);
		
	}
	
	
	/**
	 * Cierra el juego mostrando un cuadro de advertencia
	 */
	public void salga() {
		int ans =JOptionPane.showConfirmDialog(null,"¿Desea salir del juego?","Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (ans==JOptionPane.YES_OPTION){
			dispose();
			System.exit(0);}
	}
	
	/**
	 * Lo hace visible el frame 
	 */
	public void makeVisible(){
		setVisible(true);
	}
	
	/**
	 * Crea la interface
	 */
	public static void main(String args[]) {
		SpaceInvadersGUI space= new SpaceInvadersGUI(); 
		space.makeVisible();
		space.setTitle("Space Inveders");
	}
	
}
