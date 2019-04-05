package aplicacion;

import java.util.LinkedList;

public class Pulpo extends Nave{
	private static final String imageV="/res/Aliens/alien3_0.png";
	private static final String imageF="/res/Aliens/alien3_1.png";
	private static final int score=10;
	
	/**
	 * Constructor de la clase Pulpo
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Pulpo(int xPos,int yPos) {
		super(xPos,yPos);
		disparos=1;
	}
	
	@Override
	public void muevaseHorizontal(int aMover,int aMover2,LinkedList<Nave> naves,int paralela) {
		int indice=naves.indexOf(this)-paralela;
		if(indice<naves.size() && indice>0) {
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
	 * @return la imagen
	 */
	@Override
	public String getImageV() {
		return imageV;
	}
	
	/**
	 * @return la imagen
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
