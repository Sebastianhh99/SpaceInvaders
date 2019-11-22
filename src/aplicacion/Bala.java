package aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public abstract class Bala implements Serializable{
	protected int xPos,yPos;
	
	/**
	 * Contructor de la Bala
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Bala(int xPos,int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}
	
	/**
	 * Mueve la bala haci arriba
	 * @param cantidad de pixeles que se mueve
	 */
	public void muevala(int cantidad) {
		yPos-=cantidad;
	}
	
	/**
	 * Verifica si la bala salio de la pantalla
	 * @return si se salio
	 */
	public boolean verifiqueSalida() {
		if(yPos<0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna la posicion x
	 * @return xPos
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * retorna la posicion y
	 * @return yPos
	 */
	public int getYPos() {
		return yPos;
	}
	
	
	/**
	 * Verifica si hubi un impacto
	 * @param naves linked list de las naves
	 * @return si destuyo o no
	 */
	public abstract int verifiqueDisparo(LinkedList<Nave> naves,LinkedList<Barrera> barreras,LinkedList<Tanque> tanques);
	
	public abstract String getImage();
}
