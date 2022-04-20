package servlet;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Categoria;
import Entities.Producto;
import Entities.Rol;
import Entities.Usuario;
import Logic.Login;
import Logic.LogicCategoria;
import Logic.LogicProducto;

/**
 * Servlet implementation class abmc
 */
@WebServlet("/abmc")
public class abmc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public abmc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String add="WEB-INF/usuariosAbmc.jsp";
    String list="WEB-INF/listar.jsp";
    String editar="WEB-INF/editUsuario.jsp";
    String cat="WEB-INF/listarCategorias";
    //String edit="WEB-INF/"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login ctrl = new Login();
		String acceso="";
		String action=request.getParameter("accion");
		switch(action) {
		 case "add": request.getRequestDispatcher("WEB-INF/usuariosAbmc.jsp").forward(request, response);
		 			
		 			break;
		 case "listar": {	LinkedList<Usuario> usuarios = ctrl.getAll();
		 					request.setAttribute("listaUsuarios", usuarios);
		 					request.getRequestDispatcher(list).forward(request, response);}
		 			break;
		 case "agregar": {
			 			Usuario usu = new Usuario();
		 				Rol r = new Rol();
						Login ctrlLogin = new Login();
						String dni = request.getParameter("dni");
						String email = request.getParameter("email");
						String contrasenia = request.getParameter("password");
						String nombre = request.getParameter("nombre");
						String apellido = request.getParameter("apellido");
						String telefono = request.getParameter("telefono");
						String direccion = request.getParameter("direccion");
						String idrol = request.getParameter("rol");
						usu.setDni(dni);
						usu.setEmail(email);
						usu.setContrasenia(contrasenia);
						usu.setNombre(nombre);
						usu.setApellido(apellido);
						usu.setTelefono(telefono);
						usu.setDireccion(direccion);
						r.setId(Integer.parseInt(idrol));
						usu.setRol(r);
						ctrlLogin.add(usu);
						LinkedList<Usuario> usuarios = ctrl.getAll();
						request.setAttribute("listaUsuarios", usuarios);
						request.getRequestDispatcher("WEB-INF/listar.jsp").forward(request, response);
		 		break;}
		 case "borrar":{Usuario usu = new Usuario();
						Login ctrlLogin = new Login();
						String dni = request.getParameter("dni");
						usu.setDni(dni);
						ctrlLogin.delete(usu);
						LinkedList<Usuario> usuarios = ctrl.getAll();
						request.setAttribute("listaUsuarios", usuarios);
						request.getRequestDispatcher("WEB-INF/listar.jsp").forward(request, response);
				break;}
		case "editar":{
						request.getRequestDispatcher(editar).forward(request, response);
				break;}
		 case "actualizar":{
			 Usuario usu = new Usuario();
			 Login ctrlLogin = new Login();
			 String dni=request.getParameter("dni");
			 String email=request.getParameter("email");
			 String nombre=request.getParameter("nombre");
			 String apellido=request.getParameter("apellido");
			 String telefono=request.getParameter("telefono");
			 String contrasenia=request.getParameter("contrasenia");
			 String direccion=request.getParameter("direccion");
			 usu.setDni(dni);
			 usu.setEmail(email);
			 usu.setContrasenia(contrasenia);
			 usu.setNombre(nombre);
			 usu.setApellido(apellido);
			 usu.setTelefono(telefono);
			 usu.setDireccion(direccion);
			 ctrlLogin.edit(usu);
			 LinkedList<Usuario> usuarios = ctrl.getAll();
			 request.setAttribute("listaUsuarios", usuarios);
			 request.getRequestDispatcher("WEB-INF/listar.jsp").forward(request, response);
		 }
		 case "inicio":{
			 LogicProducto ctrlProd = new LogicProducto();
			 LogicCategoria ctrlCat = new LogicCategoria();
			 LinkedList<Producto> Producto = ctrlProd.getAll();
			 request.setAttribute("listaProductos", Producto);
			 LinkedList<Categoria> Categoria = ctrlCat.getAll();
			 request.setAttribute("listaCategorias", Categoria);
			 request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		 }
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

}
