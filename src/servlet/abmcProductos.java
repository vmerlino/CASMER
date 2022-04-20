package servlet;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Entities.Categoria;
import Entities.Producto;
import Logic.LogicCategoria;
import Logic.LogicProducto;


/**
 * Servlet implementation class abmcCategorias
 */
@MultipartConfig
@WebServlet("/abmcProductos")
public class abmcProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String add="WEB-INF/NewProducto.jsp";
    String list="WEB-INF/listarProductos.jsp";
    String editar="WEB-INF/EditProducto.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicProducto ctrl = new LogicProducto();
		Producto prod = new Producto();
		String acceso="";
		String action=request.getParameter("accion");
		switch(action) {
		 case "add": request.getRequestDispatcher("WEB-INF/NewProducto.jsp").forward(request, response);
		 			break;
		 case "listar": {	LinkedList<Producto> Producto = ctrl.getAll();
		 					request.setAttribute("listaProductos", Producto);
		 					request.getRequestDispatcher(list).forward(request, response);}
		 			break;
		 case "inicio":{
			 			LinkedList<Producto> Producto = ctrl.getAll();
			 			request.setAttribute("listaProductos", Producto);
			 			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		 				}
		 			break;
		 case "agregar": {
		 				  Categoria cat = new Categoria();
						LogicProducto ctrlLogin = new LogicProducto();
						String idCategoria = request.getParameter("idCategoria");
						String descripcion = request.getParameter("descripcion");
						String precio = request.getParameter("precio");
						String stock = request.getParameter("stock");
//						 Part img = request.getPart("img");
//						 int fotosize=(int) img.getSize();
//						 byte[] foto=null;
//								 if(fotosize>0) {
//									 	foto= new byte[fotosize];
//									 	try (DataInputStream dis = new DataInputStream(img.getInputStream())) {
//									 		dis.readFully(foto);
//									 	}
//									 	
//								}
//						System.out.println(img);
						prod.setDescripcion(descripcion);
						prod.setPrecio(Float.parseFloat(precio));
						prod.setStock(Integer.parseInt(stock));
						cat.setIdCategoria(Integer.parseInt(idCategoria));
						prod.setCat(cat);
						
						ctrlLogin.add(prod);
						LinkedList<Producto> Producto = ctrl.getAll();
						request.setAttribute("listaProductos", Producto);
						request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
		 		break;}
		 case "borrar":{
						String id = request.getParameter("idProducto");
						prod.setIdProducto(Integer.parseInt(id));
						ctrl.delete(prod);
						LinkedList<Producto> Producto = ctrl.getAll();
						request.setAttribute("listaProductos", Producto);
						request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
				break;}
		case "editar":{
					request.getRequestDispatcher(editar).forward(request, response);
				break;}
		
		case "filtrar":{
						int idCat = Integer.parseInt(request.getParameter("idCat"));
						LogicCategoria ctrlCat = new LogicCategoria();
						LinkedList<Producto> Producto= new LinkedList<>();
						if(idCat != -1) {
						 Producto = ctrl.getPorCategoria(idCat);
						}else { Producto = ctrl.getAll();}
						 LinkedList<Categoria> Categoria = ctrlCat.getAll();
						 request.setAttribute("listaCategorias", Categoria);
						request.setAttribute("listaProductos", Producto);
			 			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		break;}
		
		 case "actualizar":{
			 Categoria cat = new Categoria();
			 String id = request.getParameter("idProducto");
			 String idCategoria = request.getParameter("idCategoria");
			 String descripcion = request.getParameter("descripcion");
			 String precio = request.getParameter("precio");
			 String stock = request.getParameter("stock");
			 prod.setIdProducto(Integer.parseInt(id));
			 prod.setDescripcion(descripcion);
			 prod.setPrecio(Float.parseFloat(precio));
			 prod.setStock(Integer.parseInt(stock));
			 cat.setIdCategoria(Integer.parseInt(idCategoria));
			 prod.setCat(cat);
			 ctrl.edit(prod);
			 LinkedList<Producto> Producto = ctrl.getAll();
			 request.setAttribute("listaProductos", Producto);
			 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
			 break;
		 }
		 case "buscar": {
			 String descripcion = request.getParameter("buscar");
			 if(descripcion.equals("")) {
				 request.setAttribute("buscarprod", "");
			 }else{
				 prod.setDescripcion(descripcion);
			 LinkedList<Producto> Producto = ctrl.getByDescripcion(prod);
			 System.out.println(Producto);
			 request.setAttribute("listaProductos", Producto);
			 request.setAttribute("buscarprod", descripcion);
			 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);}
			 break;
		 }
		 case "filtrarStock": {
			 String sinStock = request.getParameter("sinStock");
			 String todos= request.getParameter("todos");
			 if(sinStock != null) {
				 LinkedList<Producto> Producto = ctrl.getSinStock();
				 request.setAttribute("listaProductos", Producto);
				 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
			}
			 if(todos!=null){
				 
			 request.getRequestDispatcher("abmcProductos?accion=listar").forward(request, response);}
		 break;}
		 case "buscarIndex": {
			 String descripcion = request.getParameter("buscar");
			// String sinStock = request.getParameter("sinStock");
			 if(descripcion.equals("")) {
				 request.setAttribute("buscarprod", "");
//				if(sinStock != null) {
//					 LinkedList<Producto> Producto = ctrl.getSinStock();
//					 request.setAttribute("listaProductos", Producto);
//					 request.getRequestDispatcher(list).forward(request, response);
//				}else {
//			
//				 request.getRequestDispatcher("abmcProductos?accion=listar").forward(request, response);}
			 }else{
				 prod.setDescripcion(descripcion);
			 LinkedList<Producto> Producto = ctrl.getByDescripcion(prod);
			 System.out.println(Producto);
			 LogicCategoria ctrlCat = new LogicCategoria();
			LinkedList<Categoria> Categoria = ctrlCat.getAll();
			 request.setAttribute("listaCategorias", Categoria);
			 request.setAttribute("listaProductos", Producto);
			 request.setAttribute("buscarprod", descripcion);
			 request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);}
			 break;
		 }
		 case "salir":
			 break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}