package aplicacion;

public class Roja extends Barrera{
	private static final String image="/res/Barreras/BarreraR.png";
	
	/**
	 * Contructor de la clase roja
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Roja(int xPos,int yPos) {
		super(xPos,yPos);
	}

	/**
	 * Destrulle la barrera
	 */
	@Override
	public void destruya(Bala bala) {
		if(!(bala instanceof BalaAlien)) {
			destroyed=true;
		}
	}

	@Override
	public String getImage() {
		return image;
	}

	@Override
	public char getChar() {
		return 'r';
	}
}
