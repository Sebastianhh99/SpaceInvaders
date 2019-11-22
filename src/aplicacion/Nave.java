package aplicacion;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Nave implements Serializable{
	protected int xPos,yPos,disparos;
	protected boolean sentidoLR=true,destroy=false;
	private static final String muere="/res/Aliens/alien_destroyed.png";
	
	/**
	 * Constructor de la clase Nave
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Nave(int xPos,int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}
	
	/**
	 * La posicion en X
	 * @return xPos
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * La posicion en Y
	 * @return yPos
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * Cambia el sentido al que se dirije el alien
	 */
	public void cambieSentido() {
		sentidoLR=(!sentidoLR);
	}
	
	/**
	 * Suma a la yPos una cantidad
	 * @param aMover La cantidad que tiene que mover hacia abajo
	 */
	public void muevaseVertical(int aMover) {
		yPos+=aMover;
	}
	
	/**
	 * Suma a la xPos una cantidad
	 * @param aMover La cantidad que tiene que mover hacia el lateral
	 */
	public void muevaseHorizontal(int aMover,int aMover2,LinkedList<Nave> naves,int paralela) {
		if(sentidoLR) {
			xPos+=aMover;
		}
		else if(!sentidoLR) {
			xPos-=aMover;
		}
	}
	
	/**
	 * Marca como destruido la Nave y resta vidas
	 */
	public void setDestroy() {
		disparos--;
		if(disparos<=0) {
			destroy=true;
		}
	}
	
	/**
	 * Da la posicion en X
	 * @param xPos La nueva posicion en x
	 */
	public void setXPos(int xPos) {
		this.xPos=xPos;
	}
	
	/**
	 * Da la posicion en Y
	 * @param xPos La nueva posicion en y
	 */
	public void setYPos(int yPos) {
		this.yPos=yPos;
	}
	
	/**
	 * Retorna si es destruido o no
	 * @return destroy 
	 */
	public boolean getDestroy() {
		return destroy;
	}
	
	public abstract int getScore();
	public abstract String getImageV();
	public abstract String getImageF();
}
