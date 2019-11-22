package aplicacion;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Tanque implements Serializable{
	protected int xPos,yPos;
	protected boolean explotion=false;
	protected int vida;
	protected String color,image;
	protected String colorDestruido;
	protected String colorVisual;
	protected static HashMap<String,String> coloresDestuido=new HashMap<String,String>();
	protected static HashMap<String,String> colores=new HashMap<String,String>();
	private int score=0;
	protected int especial=3;
	
	/**
	 * Constructor de la clase Tanque
	 * @param xPos posicion en x
	 * @param yPos posicion en y
	 */
	public Tanque(int xPos,int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
		vida=3;
		setHasColores();
	}
	
	/**
	 * añade balas especiales al tanque
	 */
	public void addEspeciales() {
		especial+=3;
	}
	
	/**
	 * obtiene el score del tanque
	 * @return int score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Suma al escore
	 * @param plus valor a sumar al score
	 */
	public void plusScore(int plus) {
		score+=plus;
	}
	
	/**
	 * Añade los posibles colores a un HashMap
	 */
	private static void setHasColores() {
		coloresDestuido.put("amarillo", "/res/Nave/Amarillo_destroyed_0.png");
		coloresDestuido.put("blanco", "/res/Nave/Blanco_destroyed_0.png");
		coloresDestuido.put("cafe", "/res/Nave/Cafe_destroyed_0.png");
		coloresDestuido.put("dorado", "/res/Nave/Dorado_destroyed_0.png");
		coloresDestuido.put("indigo", "/res/Nave/Indigo_destroyed_0.png");
		coloresDestuido.put("lavanda", "/res/Nave/Lavanda_destroyed_0.png");
		coloresDestuido.put("morado", "/res/Nave/Morado_destroyed_0.png");
		coloresDestuido.put("naranja", "/res/Nave/Naranja_destroyed_0.png");
		coloresDestuido.put("rojo", "/res/Nave/Rojo_destroyed_0.png");
		coloresDestuido.put("rosa", "/res/Nave/Rosa_destroyed_0.png");
		coloresDestuido.put("turquesa", "/res/Nave/Turquesa_destroyed_0.png");
		coloresDestuido.put("verde", "/res/Nave/Verde_destroyed_0.png");
		colores.put("amarillo","/res/Nave/Amarillo.png");
		colores.put("blanco", "/res/Nave/Blanco.png");
		colores.put("cafe", "/res/Nave/Cafe.png");
		colores.put("dorado", "/res/Nave/Dorado.png");
		colores.put("indigo", "/res/Nave/Indigo.png");
		colores.put("lavanda", "/res/Nave/Lavanda.png");
		colores.put("morado", "/res/Nave/Morado.png");
		colores.put("naranja", "/res/Nave/Naranja.png");
		colores.put("rojo", "/res/Nave/Rojo.png");
		colores.put("rosa", "/res/Nave/Rosa.png");
		colores.put("turquesa", "/res/Nave/Turquesa.png");
		colores.put("verde", "/res/Nave/Verde.png");
	}
	
	/**
	 * El numero de especiales
	 * @return la cantidad de balas especiales
	 */
	public int getEspeciales() {
		return especial;
	}
	
	/**
	 * reta una bala especial al tanque
	 */
	public void lessEspecial() {
		especial--;
	}
	
	/**
	 * Verifica si el tanque esta en el maximo 
	 * @param maxSize el maximo tamaño de la pantalla
	 * @return si se sale o no
	 */
	public boolean checkRight(int maxSize) {
		if(xPos+32>=maxSize) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica si esta en el minimo de la pantallla
	 * @return si esta en el minimo de la pantalla
	 */
	public boolean checkLeft() {
		if(xPos==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Da la posicion en X
	 * @param xPos La nueva posicion en X
	 */
	public void setXPos(int xPos) {
		this.xPos=xPos;
	}
	
	/**
	 * @return xPos la posicion en X
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * @return yPos la posicion en Y
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * Mueve el tanque a la derecha
	 */
	public void moveRight() {
		xPos+=10;
	}
	
	/**
	 * mueve el tanque a la izquerda
	 */
	public void moveLeft() {
		xPos-=10;
	}
	
	/**
	 * retorna si exploto o no
	 * @return explotion si exploto
	 */
	public boolean getExplotion() {
		return explotion;
	}
	
	/**
	 * @return vidas las vidas del tanque
	 */
	public int getVidas() {
		return vida;
	}
	
	/**
	 * resta una vida al tanque
	 */
	public void lessVida() {
		vida--;
	}
	
	/**
	 * Cambia la imagen entre destuido y vivo
	 */
	public void cambieDestruido() {
		explotion=(!explotion);
		if(image.equals(color)) {
			image=colorDestruido;
		}
		else if(image.equals(colorDestruido)) {
			image=color;
		}
	}
	
	/**
	 * Obtiene el color del tanque
	 * @return String el color del tanque
	 */
	public String getColor() {
		return colorVisual;
	}
	
	/**
	 * define su color
	 * @param color el String del color a tomar
	 */
	public void setColor(String color) {
		colorVisual=color;
		this.colorDestruido=coloresDestuido.get(color);
		this.color=colores.get(color);
		image=this.color;
	}
	
	/**
	 * obtiene la imagen del tanque
	 * @return String de la direccion de la imagen
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * Limpia las vidas del tanque
	 */
	public void clearLives() {
		vida=0;
	}
}
