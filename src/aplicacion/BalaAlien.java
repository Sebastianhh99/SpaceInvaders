package aplicacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class BalaAlien extends Bala{
	private int salida;
	private static final String image="/res/Balas/alien_shot_0.png";
	
	/**
	 * Constructor de la BalaAlien
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 * @param salida maximo tamaño de la pantalla
	 */
	public BalaAlien(int xPos,int yPos,int salida) {
		super(xPos,yPos);
		this.salida=salida;
	}
	
	/**
	 * Verifica si la bala se sale de la pantalla
	 */
	@Override
	public boolean verifiqueSalida() {
		if(yPos>=salida) {
			return true;
		}
		return false;
	}
	
	/**
	 * Mueve la bala
	 * @param cantidad La cantidad que se mueve la bala
	 */
	@Override
	public void muevala(int cantidad) {
		yPos+=cantidad;
	}
	
	/**
	 * retorna el string de la imagen
	 * @return image string de la imagen
	 */
	@Override
	public String getImage() {
		return image;
	}
	
	/**
	 * Verifica si impacto algun tanque
	 * @param tanques linked list de los tanques
	 * @return el tanque que impacto
	 */
	@Override
	public int verifiqueDisparo(LinkedList<Nave> naves, LinkedList<Barrera> barreras,LinkedList<Tanque> tanques) {
		Point bala=new Point(xPos,yPos);
		Rectangle tanque,barrera=null;
		for(int i=0;i<barreras.size();i++) {
			if(i<tanques.size()) {
				tanque=new Rectangle(tanques.get(i).getXPos()-20,tanques.get(i).getYPos()-20,50,50);
				if(barreras.get(i).getChar()=='v') {
					barrera=new Rectangle(barreras.get(i).getXPos()-20,barreras.get(i).getYPos()-20,20,20);
				}
				else if(barreras.get(i).getChar()=='r') {
					barrera=new Rectangle(barreras.get(i).getXPos()-25,barreras.get(i).getYPos()-25,50,50);
				}
				if(barrera.contains(bala) && !barreras.get(i).getDestroyed()) {
					barreras.get(i).destruya(this);
					return 1;
				}
				else if(tanque.contains(bala)) {
					tanques.get(i).cambieDestruido();
					tanques.get(i).lessVida();
					return 2;
				}
			}
			else {
				barrera=new Rectangle(barreras.get(i).getXPos()-20,barreras.get(i).getYPos()-20,20,20);
				if(barrera.contains(bala) && !barreras.get(i).getDestroyed()) {
					barreras.get(i).destruya(this);
					return 1;
				}
			}
		}
		return -1;
	}
}
