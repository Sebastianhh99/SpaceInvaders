package aplicacion;

import java.util.LinkedList;

public class Cangrejo extends Nave{
	private static final String imageV="/res/Aliens/alien2_0.png";
	private static final String imageF="/res/Aliens/alien2_1.png";
	private static final int score=30;
	
	/**
	 * Constructor del Cangrejo
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Cangrejo(int xPos,int yPos) {
		super(xPos,yPos);
		disparos=2;
	}
	
	@Override
	public void muevaseHorizontal(int aMover,int aMover2,LinkedList<Nave> naves,int paralela) {
		int indice=naves.indexOf(this)+paralela;
		if(indice<naves.size()) {
			int yPosP= naves.get(indice).getYPos();
			if(naves.get(indice).getDestroy()) {
				if(sentidoLR) {
					xPos+=aMover;
				}
				else if(!sentidoLR) {
					xPos-=aMover;
				}
				muevaseVertical(aMover2);
			}
			else {
				if(sentidoLR) {
					xPos+=aMover;
				}
				else if(!sentidoLR) {
					xPos-=aMover;
				}
			}
		}
		else {
			if(sentidoLR) {
				xPos+=aMover;
			}
			else if(!sentidoLR) {
				xPos-=aMover;
			}
		}
	}
	
	/**
	 * @return imagen del Cangrejo
	 */
	@Override
	public String getImageV() {
		return imageV;
	}
	
	/**
	 * @return imagen del Cangrejo
	 */
	@Override
	public String getImageF() {
		return imageF;
	}
	
	@Override
	public int getScore() {
		return score;
	}
}

