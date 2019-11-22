package aplicacion;

public class Platillo extends Nave{
	private static final String imageV="/res/Aliens/saucer.png";
	private static final String imageF="/res/Aliens/saucer.png";
	private static final int score=200;
	
	/**
	 * Constructor de la clase platillo
	 */
	public Platillo() {
		super(1,0);
		destroy=true;
		disparos=1;
	}
	
	/**
	 * mueveHorizontalmente la nave
	 */
	public void muevaseHorizontal() {
		xPos+=2;
	}
	
	@Override
	public void setDestroy() {
		disparos--;
		if(disparos<=0) {
			destroy=(!destroy);
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
