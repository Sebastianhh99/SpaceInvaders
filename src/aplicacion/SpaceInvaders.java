package aplicacion;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//Poner la Imagen correcta en la barrera roja y decirle a Lu que ponga el que la cambie a true
//

public class SpaceInvaders implements Serializable{
	private int filas,columnas,altoCasilla,anchoCasilla,
							altoScreen,anchoScreen,navesColumnas,navesFilas,cantidadBarreras;
	private boolean calamar=true,cangrejo=true,pulpo=true,permita=true,roja=false,verde=true,platillo=true,
			notPlatilloReady=true;
	private LinkedList<Nave> naves;
	private LinkedList<Barrera> barreras;
	private LinkedList<Tanque> tanques;
	private Bala[] balas;
	
	/**
	 * Constructor de la clase SpaceInvaders
	 * @param filas
	 * @param columnas
	 * @param ancho
	 * @param alto
	 * @param modo
	 * @throws SpaceException
	 */
	public SpaceInvaders(int filas,int columnas,int ancho,int alto,String modo) throws SpaceException {
		if(modo==null) {
			modo="Un Solo Jugador";
		}
		altoScreen=alto;
		anchoScreen=ancho;
		naves=new LinkedList<Nave>();
		barreras=new LinkedList<Barrera>();
		tanques=new LinkedList<Tanque>();
		this.filas=filas;
		this.columnas=columnas;
		asigneAltos();
		cantidadNaves();
		cantidadBarreras=3;
		switch(modo) {
			case("Un Solo Jugador"):
				creeJugador();
				break;
			case("Jugador vs Jugador"):
				creeJugador();
				creeJugador();
				break;
			case("Jugador vs Consola"):
				creeJugador();
				creeMaquina();
				break;
		}
	}
	/**
	 * Suma al score de un tanque
	 * @param tanque el tanque que impacto
	 * @param plus la cantidad a sumar
	 */
	public void addScore(int tanque,int plus) {
		tanques.get(tanque).plusScore(plus);
	}
	
	/**
	 * Vuelve los color de los tanques
	 * @param coloresTanques los colores de cada tanque
	 */
	public void setColores(HashMap<Integer,String> coloresTanques) {
		for(int i=0;i<tanques.size();i++) {
			tanques.get(i).setColor(coloresTanques.get(i));
		}
	}
	
	/**
	 * Mueve las balas de los aliens hacia abajo
	 */
	public void muevaBalasAliens() {
		for(int i=2;i<balas.length;i++) {
			if(balas[i]!=null) {
				balas[i].muevala(1);
			}
		}
	}
	
	/**
	 * Verifica si un disparo de alien impacta un tanque o una barrera
	 * @return si impacto o no
	 */
	public int verifiqueDisparoAlien() {
		for(int i=2;i<balas.length;i++) {
			if(balas[i]!=null) {
				int ans=((BalaAlien) balas[i]).verifiqueDisparo(naves,barreras,tanques);
				if(ans!=-1) {
					balas[i]=null;
					return ans;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Añade balas especiales al tanque 
	 * @param tanque
	 */
	public void addEspeciales(int tanque) {
		tanques.get(tanque).addEspeciales();
	}
	
	/**
	 * Vuelve todas las balas null
	 */
	public void setBalasNull() {
		Arrays.fill(balas, null);
	}
	
	/**
	 * Dispara de los aliens de forma aleatoria
	 */
	public void dispareAliens() {
		for(int i=2;i<naves.size()-1;i++) {
			if(balas[i]!=null) {
				if(balas[i].verifiqueSalida()) {
					balas[i]=null;
				}
			}
			if(balas[i]==null && !naves.get(i).getDestroy()) {
				int temp=(int)(Math.random()*100)+1;
				if(temp==1 || temp==10 || temp==20 || temp==25 || temp== 40) {
					balas[i]=new BalaAlien(naves.get(i).getXPos(),naves.get(i).getYPos(),altoScreen);
				}
			}
		}
	}
	
	/**
	 * Crea las maquinas en el modo de jugador vs consola
	 */
	public void creeMaquina() {
		int temp=(int) (Math.random()*3)+1;
		switch(temp) {
			case(1):
				tanques.add(new Cuidadoso(anchoScreen/2,altoScreen-altoCasilla*4));
				break;
			case(2):
				tanques.add(new Ofensivo(anchoScreen/2,altoScreen-altoCasilla*4));
				break;
			case(3):
				tanques.add(new Nervioso(anchoScreen/2,altoScreen-altoCasilla*4));
				break;
		}
	}
	
	/**
	 * Crea el o los jugadores en el centro de la pantalla
	 */
	public void creeJugador() {
		tanques.add(new Jugador(anchoScreen/2,altoScreen-altoCasilla*4));
	}
	
	/**
	 * Asigna los altos y anchos de las casillas de los aliens
	 * @throws SpaceException
	 */
	public void asigneAltos() throws SpaceException{
		altoCasilla=altoScreen/filas;
		anchoCasilla=anchoScreen/columnas;
		if(altoCasilla<40 || anchoCasilla<42) {
			throw new SpaceException(SpaceException.SIZE);
		}
	}
	
	/**
	 * Importa un archivo de juego
	 * @param archivo el archivo a importar
	 * @throws SpaceException
	 */
	public void importe(FileReader archivo) throws SpaceException {
		Constructor constructo=null;
		barreras.clear();
		naves.clear();
		Arrays.fill(balas, null);
		tanques.clear();
		int xPos,yPos,cont=1;
    	String[] datos;
	    String line=null;
	    PrintWriter pw=null;
    	try {	
    		pw=new PrintWriter(new FileOutputStream("SpaceErrG.txt"));
    		BufferedReader texto=new BufferedReader(archivo);
	    	line=texto.readLine();
	    	while(line!=null) {
	    		line=line.trim();
	    		if(!line.isEmpty()) {
		   			datos=line.split(";");
		   			xPos=Integer.parseInt(datos[1]);
		   			yPos=Integer.parseInt(datos[2]);
		   			String tipo=datos[0];
		   			Class clazz=Class.forName("aplicacion."+tipo);
		   			cont++;
		   			if(tipo.equals("Platillo")) {
		   				constructo=clazz.getConstructor(new Class[]{});
		   			}
		   			else if(tipo.equals("Verde")) {
		   				constructo=clazz.getConstructor(new Class[]{int.class,int.class,int.class});
		   			}
		   			else {
		   				constructo=clazz.getConstructor(new Class[]{int.class,int.class});
		   			}
					if(tipo.equals("Verde") || tipo.equals("Rojo")) {
						barreras.add((Barrera)constructo.newInstance(xPos,yPos,Integer.parseInt(datos[3])));
					}
					else if(tipo.equals("Jugador") || tipo.equals("Cuidadoso") || tipo.equals("Nervioso") || tipo.equals("Ofensivo")) {
						tanques.add((Tanque)constructo.newInstance(xPos,yPos));
						tanques.getLast().setColor(datos[3]);
					}
					else {
						if(tipo.equals("Platillo")) {
							naves.add((Nave)constructo.newInstance());
						}
						else {
							naves.add((Nave)constructo.newInstance(xPos,yPos));
							if(Boolean.valueOf(datos[3])) {
								naves.getLast().setDestroy();
							}
						}
					}
		    	}
	    		line=texto.readLine();
	    	}
	    	texto.close();
	    	pw.close();
	    }catch(FileNotFoundException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.FILE_NOT_FOUND);
	    }catch(NumberFormatException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.COORDENADA_NOT_INT);
	    }catch(IOException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.IOERROR);
	    }catch(ClassNotFoundException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.CLASS_NOT_FOUND);
	    }catch(NoSuchMethodException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.METHOD_NOT_FOUND);
	    }catch(InvocationTargetException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.METHOD_ERROR);
	    }catch(IllegalAccessException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.METHOD_ERROR);
	    }catch(InstantiationException e) {
	    	pw.println(e.getMessage());
	    	pw.close();
	    	throw new SpaceException(SpaceException.METHOD_ERROR);
	    }
	}
	
	/**
	 * exporta el juego 
	 * @param archivo el archivo donde se va a guardar
	 * @throws SpaceException
	 */
	public void exporte(FileWriter archivo) throws SpaceException{
		try {	
    		PrintWriter pr = new PrintWriter(archivo);
    		for(Nave i:naves) {
    			pr.println(i.getClass().getSimpleName()+";"+i.getXPos()+";"+i.getYPos()+";"+i.getDestroy());
    		}
    		for(Tanque i:tanques) {
    			pr.println(i.getClass().getSimpleName()+";"+i.getXPos()+";"+i.getYPos()+";"+i.getColor());
    		}
    		for(Barrera i:barreras) {
    			pr.println(i.getClass().getSimpleName()+";"+i.getXPos()+";"+i.getYPos()+";"+Integer.toString(i.getTipo()));
    		}
    		archivo.close();
    	}catch (FileNotFoundException ex){
			ex.printStackTrace();
			throw new SpaceException(SpaceException.FILE_NOT_FOUND);
			
		} catch (IOException e){
			e.printStackTrace();
			throw new SpaceException(SpaceException.IOERROR);
		}
	}
	
	/**
	 * Guarda el archivo con el progreso del juego
	 * @param archivo el archivo a escribir
	 * @throws SpaceException
	 */
	public void salve(File archivo) throws SpaceException{
    	try {
    		ObjectOutputStream oos = new ObjectOutputStream(new 
    			FileOutputStream(archivo));
    		oos.writeObject(this);
    		oos.close();
    	}catch(FileNotFoundException e) {
    		throw new SpaceException(SpaceException.FILE_NOT_FOUND);
    	}catch(IOException e) {
    		throw new SpaceException(SpaceException.IOERROR);
    	}
    }
	
	/**
	 * Abre un archivo guardado
	 * @param file El archivo a abrir
	 * @throws SpaceException
	 */
	public void abra(File file) throws SpaceException{
		SpaceInvaders ans=null;
    	try{
			ObjectInputStream ios = new ObjectInputStream(new FileInputStream(file));
			ans = (SpaceInvaders) ios.readObject();
			ios.close();
		
		} catch (FileNotFoundException ex){
			//ex.printStackTrace();
			throw new SpaceException(SpaceException.FILE_NOT_FOUND);
			
		} catch (IOException e){
			//e.printStackTrace();
			throw new SpaceException(SpaceException.IOERROR);

		} catch (ClassNotFoundException ev){
			//ev.printStackTrace();
			throw new SpaceException(SpaceException.CLASS_NOT_FOUND);
		}
    	naves=ans.getNaves();
    	barreras=ans.getBarreras();
    	tanques=ans.getTanques();
    	balas=ans.getBalas();
	}
	
	/**
	 * Retorna las barreras
	 * @return barreras de del juego
	 */
	public LinkedList<Barrera> getBarreras(){
		return barreras;
	}
	
	/**
	 * Crea las barreras del juego
	 */
	public void creeBarreras() {
		int ran=2;
		int frecuenci=anchoScreen/3;
		for(int i=0;i<3;i++) {
			if(roja && verde) {
				if(ran==2) {
					ran=1;
				}
				else if(ran==1) {
					ran=2;
				}
			}
			else if(verde) {
				ran=1;
			}
			else if(roja) {
				ran=2;
			}
			if(ran==1) {
				for(int j=0;j<5;j++) {
					switch(j) {
					case(0):
						barreras.add(new Verde((i*frecuenci+anchoCasilla*4)-14,altoScreen-altoCasilla*5,j));
						break;
					case(1):
						barreras.add(new Verde((i*frecuenci+anchoCasilla*4)-14,(altoScreen-altoCasilla*5)-8,j));
						break;
					case(2):
						barreras.add(new Verde((i*frecuenci+anchoCasilla*4),(altoScreen-altoCasilla*5)-8,j));
						break;
					case(3):
						barreras.add(new Verde((i*frecuenci+anchoCasilla*4)+14,(altoScreen-altoCasilla*5)-8,j));
						break;
					case(4):
						barreras.add(new Verde((i*frecuenci+anchoCasilla*4)+14,altoScreen-altoCasilla*5,j));
						break;
					}
				}
			}
			else if(ran==2) {
				barreras.add(new Roja(i*frecuenci+anchoCasilla*4,altoScreen-altoCasilla*5));
			}
		}
	}
	
	/**
	 * Da la cantidad de naves en horizontal y vertical del juego
	 */
	public void cantidadNaves() {
		navesColumnas=columnas/3;
		navesFilas=filas/3;
		navesFilas/=3;
		if(navesFilas<1) navesFilas=1;
	}
	
	/**
	 * Crea las naves del juego
	 * @throws SpaceException
	 */
	public void creeNaves() throws SpaceException{
		if(!calamar && !pulpo && !cangrejo && !roja && !verde && !platillo) throw new SpaceException(SpaceException.NONE_SELECT);
		int contTemp=-1;
		for(int cont=0;cont<3;cont++) {
			for(int j=0;j<navesFilas;j++) {
				contTemp++;
				for(int i=0;i<navesColumnas;i++) {
					if(cont==0 && calamar) {
						naves.add(new Calamar(anchoCasilla*(i+navesColumnas),altoCasilla*(contTemp)));
					}
					else if(cont==1 && cangrejo) {
						naves.add(new Cangrejo(anchoCasilla*(i+navesColumnas),altoCasilla*(contTemp)));
					}
					else if(cont==2 && pulpo) {
						naves.add(new Pulpo(anchoCasilla*(i+navesColumnas),altoCasilla*(contTemp)));
					}
				}
			}
		}
		naves.add(new Platillo());
		balas= new Bala[naves.size()+2];
	}
	
	/**
	 * Chequea si se puede crear un platillo y lo crea
	 * @return si lo creo o no
	 */
	public boolean checkPlatillo() {
		if(platillo && notPlatilloReady) {
			for(int i=0;i<naves.size()-1;i++) {
				if(naves.get(i).getYPos()==0) {
					return false;
				}
			}
			int prob=(int)(Math.random()*10)+1;
			if(prob==1) {
				naves.getLast().setYPos(0);
				naves.getLast().setXPos(anchoCasilla+1);
				naves.getLast().setDestroy();
				notPlatilloReady=false;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si el platillo salio de la pantalla
	 * @return si se salio o no
	 */
	public boolean platilloSalido() {
		if(naves.getLast().getXPos()>anchoScreen) {
			naves.getLast().setDestroy();
			return true;
		}
		return false;
	}

	/**
	 * Le dice al platillo que se mueva horizontalmente
	 */
	public void muevaPlatillo() {
		Nave plat=naves.getLast();
		((Platillo) plat).muevaseHorizontal();
	}
	
	/**
	 * Da las naves existentes
	 * @return
	 */
	public LinkedList<Nave> getNaves() {
		return naves;
	}
	
	
	/**
	 * Verifica si las naves se pueden mover horizontalmente
	 */
	public void verificaMoverse() {
		for(int i=0;i<naves.size()-1;i++) {
			if(!naves.get(i).getDestroy()) {
				if(naves.get(i).getYPos()>=tanques.get(0).getYPos()) {
					tanques.get(0).clearLives();
				}
				if(naves.get(i).getXPos()<anchoCasilla || naves.get(i).getXPos()>=anchoCasilla*(columnas-1)) {
					permita=false;
					return;
				}
			}
		}
	}
	
	/**
	 * Mueve todas las naves menos el platillo
	 */
	public void muevaNaves() {
		verificaMoverse();
		if(permita) {
			for(int i=0;i<naves.size()-1;i++) {
				naves.get(i).muevaseHorizontal(anchoCasilla,altoCasilla,naves,navesColumnas);
			}
		}
		else if(!permita) {
			permita=true;
			for(int i=0;i<naves.size()-1;i++) {
				naves.get(i).cambieSentido();
				naves.get(i).muevaseVertical(altoCasilla);
				naves.get(i).muevaseHorizontal(anchoCasilla,altoCasilla,naves,navesColumnas);
			}
		}
	}
	
	/**
	 * Crea la bala del tanque al momento de disparar
	 * @param tanque el tanque que la dispara
	 */
	public void creeBala(int tanque) {
		if(tanque<tanques.size()) {
			if(tanques.get(tanque) instanceof Jugador) {
				balas[tanque]=new Normal(tanques.get(tanque).getXPos(),tanques.get(tanque).getYPos());
			}
			else {
				if(tanques.get(tanque) instanceof Cuidadoso) {
					if(((Cuidadoso) tanques.get(tanque)).verifiqueDisparo(barreras)) {
						balas[tanque]=new Normal(tanques.get(tanque).getXPos(),tanques.get(tanque).getYPos());
					}
				}
				else if(tanques.get(tanque) instanceof Nervioso) {
					balas[tanque]=new Normal(tanques.get(tanque).getXPos(),tanques.get(tanque).getYPos());
				}
				else if(tanques.get(tanque) instanceof Ofensivo) {
					if(((Ofensivo) tanques.get(tanque)).verifiqueDisparo(naves)) {
						balas[tanque]=new Normal(tanques.get(tanque).getXPos(),tanques.get(tanque).getYPos());
					}
				}
			}
		}
	}
	
	public void creeEspecial(int tanque) {
		if(tanque<tanques.size()) {
			if(tanques.get(tanque).getEspeciales()>0) {
				tanques.get(tanque).lessEspecial();
				balas[tanque]=new Especial(tanques.get(tanque).getXPos(),tanques.get(tanque).getYPos());
			}
		}
	}
	
	/**
	 * Retorna la bala del jugador 1
	 * @return bala del jugador 1
	 */
	public Bala getBala1() {
		return balas[0];
	}
	
	/**
	 * Retorna la bala del jugador 2
	 * @return bala del jugador 2
	 */
	public Bala getBala2() {
		return balas[1];
	}
	
	/**
	 * Retorna los tanques existentes
	 * @return
	 */
	public LinkedList<Tanque> getTanques(){
		return tanques;
	}
	
	/**
	 * Marca si el calamar existe o no
	 */
	public void cambieCalamar() {
		calamar=(!calamar);
	}
	
	/**
	 * Marca si el Pulpo existe o no
	 */
	public void cambiePulpo() {
		pulpo=(!pulpo);
	}
	
	/**
	 * Marca si el cangrejo existe o no
	 */
	public void cambieCangrejo() {
		cangrejo=(!cangrejo);
	}
	
	/**
	 * Marca si barrera roja existe o no
	 */
	public void cambieRoja() {
		roja=(!roja);
	}
	
	/**
	 * Marca si la barrera verde existe o no
	 */
	public void cambieVerde() {
		verde=(!verde);
	}
	
	/**
	 * Marca si el Platillo existe o no
	 */
	public void cambiePlatillo() {
		platillo=(!platillo);
	}
	
	/**
	 * Vuelve una bala nula
	 */
	public void setBala(int bala) {
		balas[bala]=null;
	}
	
	/**
	 * Retorna las balas existentes
	 */
	public Bala[] getBalas() {
		return balas;
	}
}
