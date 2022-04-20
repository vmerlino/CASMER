package UI;


import java.util.LinkedList;
import java.util.Scanner;
import Entities.*;
import Logic.*;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();
	LogicCategoria ctrlCat = new LogicCategoria();

	public void start() {
		s = new Scanner(System.in);
		Usuario u=login();
		System.out.println("Bienvenido "+u.getNombre()+" "+u.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
				search();
			break;
		case "new":
			//newCategoria();
			newUsu();
				
			break;
		case "edit":
				edit();
			break;
		case "delete":
				delete();
			break;
		default:
			break;
		}
	}

	private void newUsu() {
		Usuario usu = new Usuario();
		Login ctrlLogin = new Login();
		System.out.println("dni");
		usu.setDni(s.nextLine());
		System.out.println("email");
		usu.setEmail(s.nextLine());
		System.out.println("pass");
		usu.setContrasenia(s.nextLine());
		System.out.println("nom");
		usu.setNombre(s.nextLine());
		System.out.println("ape");
		usu.setApellido(s.nextLine());
		System.out.println("tel");
		usu.setTelefono(s.nextLine());
		System.out.println("direc");
		usu.setDireccion(s.nextLine());
		ctrlLogin.add(usu);
		System.out.println("El dni del nuevo usuario es: "+ usu.getDni());
	}

	private void search() {
		Categoria cat = new Categoria();
		LinkedList<Categoria> c = new LinkedList<Categoria>();
		System.out.println("Ingrese por descripcion a buscar");
		cat.setDescripcion(s.nextLine());
		c = ctrlCat.getByDescripcion(cat);
		System.out.println("Las Categorias encontradas por esa descripcion son:\n " + c);
	}


	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por IdCategoria"); //solo debe devolver 1
		System.out.println("search\t\tlistar por descripcion"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva USUARIO");
		System.out.println("edit\t\tbusca por idCategoria y actualiza todos los datos");
		System.out.println("delete\t\tborra por idCategoria");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Usuario login() {
		
		Usuario u=new Usuario();
		
		System.out.print("Email: ");
		u.setEmail(s.nextLine());

		System.out.print("Contraseña: ");
		u.setContrasenia(s.nextLine());
		
		u=ctrlLogin.validate(u);
		
		return u;
		
	}
	
	/*private Usuario find() {
		System.out.println();
		Usuario u=new Usuario();
		System.out.println("dni: ");
		u.setDni(s.nextLine());
		return ctrlLogin.getByDni(u);
	}*/
	
	private Categoria find() {
		System.out.println();
		Categoria c = new Categoria();
		System.out.println("idCategoria: ");
		c.setIdCategoria(Integer.parseInt(s.nextLine()));
		return ctrlCat.getByIdCategoria(c);
	}
	
	
	private void newCategoria() 
	{
		Categoria cat = new Categoria();
		System.out.println("Ingrese descripcion");
		cat.setDescripcion(s.nextLine());
		ctrlCat.add(cat);
		System.out.println("El id de la nueva categoria es: "+ cat.getIdCategoria());
	}
	
	private void delete() {
		Categoria cat = new Categoria();
		System.out.println("Ingrese idCategorias a borrar: ");
		cat.setIdCategoria(Integer.parseInt(s.nextLine()));
		ctrlCat.delete(cat);
		System.out.println("La baja se ha dado con exito!");
	}
	
	private void edit() {
		Categoria cat = new Categoria();
		cat = find();
		System.out.println("Ingrese nueva Descripcion de la categoria: ");
		cat.setDescripcion(s.nextLine());
		ctrlCat.edit(cat);
		System.out.println("Se ha editado la categoria con exito!");
	}

}