package aplicacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Normal extends Bala{
	
	private static final String image="/res/Balas/ship_shot.png";
	
	/**
	 * Constructor de la clase Normal
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Normal(int xPos,int yPos) {
		super(xPos,yPos);
	}
	
	/**
	 * @return image la imagen de la bala
	 */
	@Override
	public String getImage() {
		return image;
	}

	@Override
	public int verifiqueDisparo(LinkedList<Nave> naves, LinkedList<Barrera> barreras, LinkedList<Tanque> tanques) {
		Point bala=new Point(xPos,yPos);
		Rectangle nave,barrera=null;
		for(int i=0;i<naves.size();i++) {
			if(i<barreras.size()) {
				nave= new Rectangle(naves.get(i).getXPos()-32,naves.get(i).getYPos()-32,50,50);
				if(barreras.get(i).getChar()=='v') {
					barrera=new Rectangle(barreras.get(i).getXPos()-20,barreras.get(i).getYPos()-20,20,20);
				}
				else if(barreras.get(i).getChar()=='r') {
					barrera=new Rectangle(barreras.get(i).getXPos()-25,barreras.get(i).getYPos()-25,50,50);
				}
				if(nave.contains(bala) && !naves.get(i).getDestroy()) {
					naves.get(i).setDestroy();
					return naves.get(i).getScore();
				}
				if(barrera.contains(bala) && !barreras.get(i).getDestroyed()) {
					barreras.get(i).destruya(this);
					return 0;
				}
			}
			else {
				nave= new Rectangle(naves.get(i).getXPos()-32,naves.get(i).getYPos()-32,50,50);
				if(nave.contains(bala) && !naves.get(i).getDestroy()) {
					naves.get(i).setDestroy();
					return naves.get(i).getScore();
				}
			}
		}
		return -1;
	}
}
