package aplicacion;

import java.util.LinkedList;

public class Cuidadoso extends Tanque{
	
	/**
	 * Constructor de la clase Cuidadoso
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Cuidadoso(int xPos,int yPos) {
		super(xPos,yPos);
	}
	
	/**
	 * Verifica si su disparo puede impactar una barrera
	 * @param barreras Las barreras Existentes
	 * @return Si puede impacatar una barrera o no
	 */
	public boolean verifiqueDisparo(LinkedList<Barrera> barreras) {
		for(Barrera i:barreras) {
			if(i.getXPos()<xPos && xPos<i.getXPos()+32) {
				return false;
			}
		}
		return true;
	}
}
