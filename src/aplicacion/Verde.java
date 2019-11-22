package aplicacion;

public class Verde extends Barrera{
	private static final String superior="/res/Barreras/shield_1_0.png";
	private static final String superior_der="/res/Barreras/shield_2_0.png";
	private static final String superior_izq="/res/Barreras/shield_0_0.png";
	private static final String inferior_izq="/res/Barreras/shield_0_3.png";
	private static final String inferior_der="/res/Barreras/shield_2_3.png";
	//
	private static final String superior_des="/res/Barreras/shield_1_0_destroyed.png";
	private static final String superior_der_des="/res/Barreras/shield_2_0_destroyed.png";
	private static final String superior_izq_des="/res/Barreras/shield_0_0_destroyed.png";
	private static final String inferior_izq_des="/res/Barreras/shield_0_3_destroyed.png";
	private static final String inferior_der_des="/res/Barreras/shield_2_3_destroyed.png";
	private String image;
	
	/**
	 * Contructor de barrera 
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 * @param tipo tipo de la barrera
	 */
	public Verde(int xPos,int yPos,int tipo) {
		super(xPos,yPos);
		this.tipo=tipo;
		asigneImagen(tipo);
	}
	
	/**
	 * Asigna la imagen a partir de la imagen
	 * @param tipo el tipo de la barrera
	 */
	private void asigneImagen(int tipo) {
		switch(tipo) {
		case(0):
			image=inferior_izq;
			break;
		case(1):
			image=superior_izq;
			break;
		case(2):
			image=superior;
			break;
		case(3):
			image=superior_der;
			break;
		case(4):
			image=inferior_der;
			break;
		}
	}
	
	@Override
	public String toString() {
		return "V";
	}

	@Override
	public void destruya(Bala bala) {
		switch(image) {
		case(superior):
			image=superior_des;
			break;
		case(superior_der):
			image=superior_der_des;
			break;
		case(superior_izq):
			image=superior_izq_des;
			break;
		case(inferior_izq):
			image=inferior_izq_des;
			break;
		case(inferior_der):
			image=inferior_der_des;
			break;
		default:
			destroyed=true;
			break;
		}
	}
	
	@Override
	public String getImage() {
		return image;
	}

	@Override
	public char getChar() {
		return 'v';
	}
}
