package aplicacion;

import java.io.Serializable;

public abstract class Barrera implements Serializable{
	protected int xPos,yPos;
	protected boolean destroyed=false;
	protected int tipo=0;
	
	/**
	 * Constructor de la barrera
	 * @param xPos posicion x
	 * @param yPos posicion y
	 */
	public Barrera(int xPos,int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}
	
	/**
	 * posicion en x
	 * @return xPos
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * posicion en y
	 * @return yPos
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * Si la barrera esta destruida o no 
	 * @return si esta desturida
	 */
	public boolean getDestroyed() {
		return destroyed;
	}
	
	public abstract char getChar();
	
	public abstract void destruya(Bala bala);
	
	public abstract String getImage();
	
	/**
	 * El tipo de la barrera
	 * @return El tipo de la barrera
	 */
	public int getTipo() {
		return tipo;
	}
}
