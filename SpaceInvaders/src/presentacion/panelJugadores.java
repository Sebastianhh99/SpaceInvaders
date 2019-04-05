package presentacion;

import aplicacion.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class panelJugadores extends JPanel{
	protected SpaceInvaders game;
	
	protected SpaceInvadersGUI gui;
	protected static Font fuente;
	

	protected Image fondo;
	protected int ancho, alto,score=0;
	protected HashMap<Integer, String> nave;
	protected HashMap<String, Integer> aliens;
	protected boolean figura=true;
	protected LinkedList<Tanque> tanques;
	protected Bala bala1,bala2,balas[];
	protected JLabel nave1, nave2;
	protected Random ran;
	protected LinkedList<Nave> naves;
	protected Timer time,timeMueve,timePlatillo,timeDestruirTanque,tiRepaint;
	protected HashMap<String,String> vidas = new HashMap<String,String>() ;
	
	/**
	 * Constructor de la pantalla de juego cuando es un solo jugador
	 * @param g SpaceInvadersGUI JFrame
	 * @param ancho pantalla 
	 * @param alto pantalla
	 * @param game el juego desde la capa de aplicacion 
	 * @param nave HashMap con los dos tanques y los colores que el usuario escogió
	 * @param aliens HashMap con los elementos que escogio el usuario
	 * @throws SpaceException 
	 */
	public panelJugadores(SpaceInvadersGUI g,int ancho,int alto,SpaceInvaders game,HashMap<Integer, String> nave,HashMap<String, Integer> aliens) throws SpaceException{
		vidas.put("amarillo", "/res/Nave/Amarillo_ship_lives.png");
		vidas.put("blanco", "/res/Nave/Blanco_ship_lives.png");
		vidas.put("cafe", "/res/Nave/Cafe_ship_lives.png");
		vidas.put("dorado", "/res/Nave/Dorado_ship_lives.png");
		vidas.put("indigo", "/res/Nave/Indigo_ship_lives.png");
		vidas.put("lavanda", "/res/Nave/Lavanda_ship_lives.png");
		vidas.put("morado", "/res/Nave/Morado_ship_lives.png");
		vidas.put("naranja", "/res/Nave/Naranja_ship_lives.png");
		vidas.put("rojo", "/res/Nave/Rojo_ship_lives.png");
		vidas.put("rosa", "/res/Nave/Rosa_ship_lives.png");
		vidas.put("turquesa", "/res/Nave/Turquesa_ship_lives.png");
		vidas.put("verde", "/res/Nave/Verde_ship_lives.png");
		ran=new Random();
		this.alto = alto;
		this.ancho = ancho;
		this.game = game;
		this.nave = nave;
		this.aliens = aliens;
		prepareElementos(g);
		prepareFuente();
		seleccioneElementos();
		game.setColores(nave);
		naves=game.getNaves();
		tanques=game.getTanques();
		tRepaint();
		tiempo();
		tiempoMueva();
	}
	
	/**
	 * Repinta la pantalla
	 */
	public void tRepaint() {
		tiRepaint=new Timer();
		TimerTask taskRepaint=new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		};
		tiRepaint.schedule(taskRepaint, 1,1);
	}
	
	/**
	 * Busca los elementos seleccionados por el usuario, y los crea
	 * @throws SpaceException
	 */
	public void seleccioneElementos() throws SpaceException{
		if(aliens.get("Calamar").equals(0)) {
			game.cambieCalamar();
		}
		if(aliens.get("Pulpo").equals(0)) {
			game.cambiePulpo();
		}
		if(aliens.get("Cangrejo").equals(0)) {
			game.cambieCangrejo();
		}
		if(aliens.get("Platillo").equals(0)) {
			game.cambiePlatillo();
		}
		if(aliens.get("Roja").equals(1)) {
			game.cambieRoja();
		}
		if(aliens.get("Verde").equals(0)) {
			game.cambieVerde();
		}
		game.creeNaves();
		game.creeBarreras();
	}
	
	/**
	 * Cambia el booleano de figura, para cambiar la imagen al moverse
	 */
	public void cambieAlMoverse() {
		figura=(!figura);
	}
	
	/**
	 * Crea la bala normal del tanque 
	 */
	public void dispare(int tanque) {
		game.creeBala(tanque);
	}
	
	/**
	 * Crea la bala especial del tanque 
	 * @param tanque
	 */
	public void dispareEspecial(int tanque) {
		game.creeEspecial(tanque);
	}
	
	/**
	 * Crea un tiempo especifico para el platillo 
	 */
	public void tiempoPlatillo() {
		timePlatillo=new Timer();
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				game.muevaPlatillo();
			}
		};
		timePlatillo.schedule(task,10,10);
	}
	
	/**
	 * Crea el tiempo para las balas 
	 */
	public void tiempoMueva() {
		timeMueve=new Timer();
		TimerTask task= new TimerTask() {
			@Override
			public void run() {
				if(!game.getNaves().getLast().getDestroy()) {
					if(game.platilloSalido()) {
						timePlatillo.cancel();
					}
				}
				if(game.checkPlatillo()) {
					tiempoPlatillo();
				}
				cambieAlMoverse();
				muevalos();
				game.dispareAliens();
			}
		};
		
		TimerTask taskBalaAlien=new TimerTask() {
			@Override
			public void run() {
				game.muevaBalasAliens();
				if(game.verifiqueDisparoAlien()==2) {
					destruirTanque();
				}
			}
		};
		timeMueve.schedule(task,1000,1000);
		timeMueve.schedule(taskBalaAlien, 1, 1);
	}
	
	/**
	 * Destruye tanque
	 */
	public void destruirTanque() {
		game.setBalasNull();
		Tanque destruido=null;
		for(Tanque i:tanques) {
			if(i.getExplotion()) {
				destruido=i;
				break;
			}
		}
		if(destruido!=null) {
			if(destruido.getVidas()<=0) {
				JOptionPane.showMessageDialog(null,"Perdio Jugador "+String.valueOf(tanques.indexOf(destruido)+1));
				gui.menuSelecion();
			}
			else {
				gui.stop();
				stop();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				destruido.cambieDestruido();
				gui.siga();
				siga();
			}
		}
	}
	
	/**
	 * Tiempo de movimiento de los aliens, de la bala
	 */
	public void tiempo() {
		time = new Timer();
		TimerTask muevaIA;
		TimerTask taskBalas=new TimerTask() {
			@Override
			public void run() {
				bala1=game.getBala1();
				bala2=game.getBala2();
				if(bala1!=null) {
					int ans=bala1.verifiqueDisparo(naves,game.getBarreras(),game.getTanques());
					if(ans!=-1) {
						if(ans==200) {
							game.addEspeciales(0);
						}
						game.addScore(0,ans);
						game.setBala(0);
					}
					else if(bala1.verifiqueSalida()) {
						game.setBala(0);
					}
					else {
						bala1.muevala(1);
					}
				}
				if(bala2!=null) {
					int ans=bala2.verifiqueDisparo(naves,game.getBarreras(),game.getTanques());
					if(ans!=-1) {
						if(ans==200) {
							game.addEspeciales(1);
						}
						game.addScore(1,ans);
						game.setBala(1);
					}
					else if(bala2.verifiqueSalida()) {
						game.setBala(1);
					}
					else {
						bala2.muevala(1);
					}
				}
			}
		};
		time.schedule(taskBalas, 1, 1);
		if(tanques.size()>1) {
			if(!(tanques.get(1) instanceof Jugador)) {
				muevaIA=new TimerTask() {
					@Override
					public void run() {
						boolean check=ran.nextBoolean();
						int temp=ran.nextInt(10);
						if(check) {
							for(int i=0;i<temp;i++) {
								if(tanques.get(1).checkLeft()) {
									tanques.get(1).moveLeft();
								}
							}
						}
						else {
							for(int i=0;i<temp;i++) {
								if(tanques.get(1).checkRight(ancho)) {
									tanques.get(1).moveRight();
								}
							}
						}
						if(bala2==null) {
							dispare(1);
						}
					}
				};
				time.schedule(muevaIA,100,100);
			}
		}
	}
	
	/**
	 * Para el tiempo
	 */
	public void stop() {
		time.cancel();
		timeMueve.cancel();
		if(timePlatillo!=null) {
			timePlatillo.cancel();
		}
	}
	
	/**
	 * Para el disparo 
	 */
	public void stopDisparo() {
		timeMueve.cancel();
	}
	
	/**
	 * Sigue el disparo
	 */
	public void sigaDisparo() {
		tiempoMueva();
	}
	
	/**
	 * Retoma el tiempo 
	 */
	public void siga() {
		tiempo();
		tiempoMueva();
		if(timeDestruirTanque!=null) {
			timeDestruirTanque.cancel();
		}
		if(timePlatillo!=null) {
			tiempoPlatillo();
		}
	}
	
	/**
	 * Mueve las naves
	 */
	public void muevalos() {
		game.muevaNaves();
	}
	
	/**
	 * Mueve el tanque a la izquierda 
	 * @param tanque
	 */
	public void muevaseLeft(int tanque) {
		if(tanque<tanques.size()) {
			if(tanques.get(tanque) instanceof Jugador) {
				if(tanques.get(tanque).checkLeft()) {
					tanques.get(tanque).moveLeft();
				}
			}
		}
	}
	
	/**
	 * Mueve el tanque a la derecha 
	 * @param tanque
	 */
	public void muevaseRight(int tanque) {
		if(tanques.size()>tanque) {
			if(tanques.get(tanque) instanceof Jugador) {
				if(tanques.get(tanque).checkRight(ancho)) {
					tanques.get(tanque).moveRight();
				}
			}
		}
	}
	
	/**
	 * Prepara los elementos iniciales de la pantalla
	 * @param el JFrame
	 */
	public void prepareElementos(SpaceInvadersGUI g){
		gui= g;
		setLayout(null);
	}
	
	/**
	 * Pinta las balas
	 */
	public void pinteBalas() {
		balas=game.getBalas();
		for(int i=0;i<balas.length;i++) {
			if(balas[i]!=null) {
				JLabel bala=new JLabel(new ImageIcon(gui.getClass().getResource(balas[i].getImage())));
				if(balas[i]!=null) {
					bala.setBounds(balas[i].getXPos(), balas[i].getYPos(), 42, 25);
				}
				add(bala);
			}
		}
	}
	
	/**
	 * Pinta los aliens
	 */
	public void pinteAliens() {
		for(Nave i:naves) {
			if(figura) {
				if(!i.getDestroy()) {
					int x=i.getXPos();
					int y=i.getYPos();
					JLabel a=new JLabel(new ImageIcon(gui.getClass().getResource(i.getImageV())));
					a.setBounds(x, y, 40, 42);
					add(a);
				}
			}
			else if(!figura) {
				if(!i.getDestroy()) {
					int x=i.getXPos();
					int y=i.getYPos();
					JLabel a=new JLabel(new ImageIcon(gui.getClass().getResource(i.getImageF())));
					a.setBounds(x, y, 40, 42);
					add(a);
				}
			}
		}
	}
	
	/**
	 * Pinta los tanques de los jugadores 
	 */
	public void pinteNaves() {
		for(int i=0;i<tanques.size();i++) {
			if(tanques.get(i).getVidas()<=0) {
				tiRepaint.cancel();
				JOptionPane.showMessageDialog(null,"Perdio Jugador "+String.valueOf(i+1));
				gui.menuSelecion();
			}
			nave1 =new JLabel(new ImageIcon(gui.getClass().getResource(tanques.get(i).getImage())));
			nave1.setBounds(tanques.get(i).getXPos(),tanques.get(i).getYPos(),42,25);
			add(nave1);
		}
	}
	
	/**
	 * Prepara el tipo de fuente del juego
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
	 * Agrega la cantidad de vidas que tiene cada jugador 
	 */
	public void getLives(){
		int y=alto-100;
		JLabel live = new JLabel("Lives ");
		live.setForeground(Color.WHITE);
		live.setFont(fuente);
		live.setBounds(15,y, 100, 42);
		add(live);
		int x=80;
		String h = vidas.get(nave.get(0));
		for(int i=0; i<tanques.getFirst().getVidas(); i++ ) {
			JLabel a = new JLabel(new ImageIcon(gui.getClass().getResource(h)));
			a.setBounds(x,y, 40, 42);
			add(a);
			x+=40;
			
		}
	}

	/**
	 * Agrega el puntaje de cada jugador 
	 */
	public void pinteScore() {
		score = tanques.get(0).getScore();
		JLabel a = new JLabel("Score "+ Integer.toString(score));
		a.setForeground(Color.WHITE);
		a.setFont(fuente);
		a.setBounds(ancho/6,alto-100, 420, 42);
		add(a);		
	}
	
	/**
	 * Pinta las barreras
	 */
	public void pinteBarreras() {
		LinkedList<Barrera> barreras=game.getBarreras();
		for(Barrera i:barreras) {
			if(!i.getDestroyed()) {
				if(i.getChar()=='v') {
					JLabel a= new JLabel(new ImageIcon(gui.getClass().getResource(i.getImage())));
					a.setBounds(i.getXPos(), i.getYPos(), 14, 8);
					add(a);
				}
				else if(i.getChar()=='r') {
					JLabel a= new JLabel(new ImageIcon(gui.getClass().getResource(i.getImage())));
					a.setBounds(i.getXPos(), i.getYPos(), 50, 24);
					add(a);
				}
			}
		}
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
		tanques=game.getTanques();
		naves=game.getNaves();	
		pinteAliens();
		pinteNaves();
		pinteBalas();
		pinteBarreras();
		
	}
	
}
