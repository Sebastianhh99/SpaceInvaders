package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import aplicacion.Bala;
import aplicacion.Nave;
import aplicacion.SpaceException;
import aplicacion.SpaceInvaders;
import presentacion.SpaceInvadersGUI;

public class pruebas {
	public pruebas() {}
	
	@Test
	public void DeberiaCrearElJuego() throws SpaceException {
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
	}
	
	@Test
	public void DeberiaDispararLosAliens() throws SpaceException {
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
		game.creeNaves();
		game.dispareAliens();
		Bala[] balas=game.getBalas();
		boolean flag=true;
		for(int i=0;i<balas.length;i++) {
			if(balas[i]!=null) {
				flag=false;
				break;
			}
		}
		assertFalse(flag);
	}
	
	@Test
	public void DeberiaVolverLasBalasNulas() throws SpaceException {
		boolean flag=true;
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
		game.creeNaves();
		game.dispareAliens();
		game.setBalasNull();
		Bala[] balas=game.getBalas();
		for(int i=0;i<balas.length;i++) {
			if(balas[i]!=null) {
				flag=false;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void DeberiaDispararUnicamenteLaBalaDelJugador() throws SpaceException{
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
		game.creeNaves();
		game.creeJugador();
		game.creeBala(0);
		Bala[] balas=game.getBalas();
		assertTrue(balas[0]!=null);
	}
	
	@Test
	public void DeberiaMoverLasNaves() throws SpaceException{
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
		game.creeNaves();
		int yPos=game.getNaves().get(0).getYPos();
		game.muevaNaves();
		LinkedList<Nave> naves2=game.getNaves();
		assertFalse(naves2.get(0).getYPos()!=yPos);
	}
	
	@Test
	public void DeberiaMoverElTanque() throws SpaceException{
		SpaceInvaders game=new SpaceInvaders(18,30,1280,720,null);
		int temp=720/2;
		game.creeJugador();
		game.getTanques().get(0).moveRight();
		assertTrue(temp<game.getTanques().get(0).getYPos());
	}
}
