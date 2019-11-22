package aplicacion;

public class SpaceException extends Exception{
	public static final String FILE_NOT_FOUND="El archivo no se encuentra"; 
	public static final String IOERROR="Se produjo un error IO";
	public static final String  CLASS_NOT_FOUND = "La clase no se encuentra";
	public static final String CELULA_NOT_FOUND="El tipo de celula no existe";
	public static final String COORDENADA_NOT_INT="Una coordenada debe ser un entero";
	public static final String COORDENADA_NOT_FOUND="La coordenada supera el tamaño del automata";
	public static final String METHOD_NOT_FOUND="El metodo de la clase no fue encontrado";
	public static final String METHOD_ERROR="Error en los metodos al instanciar";
	public static final String SIZE="Este tamaño no puede ser jugable";
	public static final String NONE_SELECT="No hay aliens seleccionados";
	public static final String PERDIO="PERDIO";
	
	/**
	 * Constructor de la clase SpaceException
	 * @param message el String de la excepcion
	 */
	public SpaceException(String message) {
		super(message);
	}
	
}
