package Logic;

import java.util.LinkedList;
import Data.*;
import Entities.*;

public class Login {
	private DataUsuario du;
	//private DataCategoria dc;
	
	public Login() {
		du=new DataUsuario();
	}
	
	public Usuario validate(Usuario u) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		/*register */
		return du.getByUser(u);
	}
	
	
	public LinkedList<Usuario> getAll(){
		return du.getAll();
	}
	
	/*public LinkedList<Categoria> getAll(){
		dc=new DataCategoria();
		return dc.getAll();
	}*/
	
	public Usuario getByDni(Usuario use) {
		return du.getByDni(use);
	}
	
	public void add(Usuario usu) {
		du.add(usu);
	}
	
	public void delete(Usuario usu) {
		du=new DataUsuario();
		du.delete(usu);
	}
	
	public void edit(Usuario usu) {
		du = new DataUsuario();
		du.edit(usu);
	}
	
	/*public Categoria getByIdCategoria(Categoria cat) {
		dc=new DataCategoria();
		return dc.getByIdCategoria(cat);
	}
	
	public void add(Categoria cat) {
		dc=new DataCategoria();
		dc.add(cat);
	}
	
	public void delete(Categoria cat) {
		dc=new DataCategoria();
		dc.delete(cat);
	}

	public void edit(Categoria cat) {
		dc = new DataCategoria();
		dc.edit(cat);
		
	}

	public Categoria search(Categoria cat) {
		dc = new DataCategoria();
		return dc.search(cat);
	}*/
	
}
