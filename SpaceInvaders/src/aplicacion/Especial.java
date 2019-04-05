package aplicacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Especial extends Bala{
	private static final String image="/res/Balas/alien_shot_1.png";

	/**
	 * Constructor de la clase Especial
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Especial(int xPos, int yPos) {
		super(xPos, yPos);
	}

	/**
	 * Verifica si la bala imparcta
	 */
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
					int score=0;
					naves.get(i).setDestroy();
					score+=naves.get(i).getScore();
					if(i==0) {
						naves.get(i+1).setDestroy();
						score+=naves.get(i+1).getScore();
					}
					else if(i==naves.size()-1) {
						naves.get(i-1).setDestroy();
						score+=naves.get(i-1).getScore();
					}
					else {
						naves.get(i-1).setDestroy();
						naves.get(i+1).setDestroy();
						score+=naves.get(i+1).getScore();
						score+=naves.get(i-1).getScore();
					}
					return score;
				}
				if(barrera.contains(bala) && !barreras.get(i).getDestroyed()) {
					barreras.get(i).destruya(this);
					return 0;
				}
			}
			else {
				nave= new Rectangle(naves.get(i).getXPos()-32,naves.get(i).getYPos()-32,50,50);
				if(nave.contains(bala) && !naves.get(i).getDestroy()) {
					int score=0;
					naves.get(i).setDestroy();
					score+=naves.get(i).getScore();
					if(i==0) {
						naves.get(i+1).setDestroy();
						score+=naves.get(i+1).getScore();
					}
					else if(i==naves.size()-2) {
						naves.get(i-1).setDestroy();
						score+=naves.get(i-1).getScore();
					}
					else {
						naves.get(i-1).setDestroy();
						naves.get(i+1).setDestroy();
						score+=naves.get(i+1).getScore();
						score+=naves.get(i-1).getScore();
					}
					return score;
				}
			}
		}
		return -1;
	}
	
	@Override
	public String getImage() {
		return image;
	}
	
	
}
