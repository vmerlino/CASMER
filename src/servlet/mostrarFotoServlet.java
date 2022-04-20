package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Producto;
import Logic.LogicProducto;

@WebServlet(urlPatterns="/MostrarFotoServlet")
public class mostrarFotoServlet extends HttpServlet {

	@EJB
	private LogicProducto logicproducto;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String $id= req.getParameter("id");
		int id = Integer.parseInt($id);
		Producto producto= new Producto();
		producto.setIdProducto(id);
		logicproducto.getByIdProducto(producto);
		byte[] foto = producto.getImg();
}

}
