package aplicacion;

public class Calamar extends Nave{
	private static final String imageV="/res/Aliens/alien1_1.png";
	private static final String imageF="/res/Aliens/alien1_0.png";
	private static final int score=50;
	
	/**
	 * Constructor del calamar
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Calamar(int xPos,int yPos) {
		super(xPos,yPos);
		disparos=(int)(Math.random()*3)+1;
	}
	
	/**
	 * @return imageV la imagen de el
	 */
	@Override
	public String getImageV() {
		return imageV;
	}
	
	/**
	 * @return la imagen de el
	 */
	@Override
	public String getImageF() {
		return imageF;
	}

	/**
	 * @return retorna el score del tanque
	 */
	@Override
	public int getScore() {
		return score;
	}
}
