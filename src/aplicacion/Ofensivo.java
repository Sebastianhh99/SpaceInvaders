package aplicacion;

import java.util.LinkedList;

public class Ofensivo extends Tanque{
	
	/**
	 * Constructor de la clase Ofensivo
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Ofensivo(int xPos,int yPos) {
		super(xPos,yPos);
	}
	
	/**
	 * Verifica si tiene un alien encima
	 * @param naves las naves existentes
	 * @return si debe disparar o no 
	 */
	public boolean verifiqueDisparo(LinkedList<Nave> naves) {
		for(Nave i:naves) {
			if(i.getXPos()<xPos && xPos<i.getXPos()+40) {
				return true;
			}
		}
		return false;
	}
}
