package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Categoria;
import Entities.LineaDePedido;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import Logic.LogicLdp;
import Logic.LogicPedido;
import Logic.Login;

/**
 * Servlet implementation class abmcPedido
 */
@WebServlet("/abmcPedido")
public class abmcPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public abmcPedido() {
		super();
	}

	LogicPedido ctrlped = new LogicPedido();
	Usuario usu = new Usuario();
	LinkedList<Pedido> listaPed = new LinkedList<>();
	Double totalPagar;
	LogicPedido ctrl = new LogicPedido();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		switch (accion) {

		case "generarCompra": {
			Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
			Pedido p = (Pedido) request.getSession().getAttribute("Pedido");
			LinkedList<LineaDePedido> productosPedidos = (LinkedList<LineaDePedido>) request.getAttribute("carrito");
			totalPagar = Double.parseDouble(request.getParameter("totalPagar"));
			p.setMonto(totalPagar);
			p.setProductos(productosPedidos);
			p.setEstado("a entregar");
			System.out.println("pedidoooo  " + p);
			ctrl.update(p);
			request.getSession().setAttribute("Pedido", p);
			request.getRequestDispatcher("WEB-INF/pedidos.jsp").forward(request, response);
			break;
		}
		case "ini":{
			LinkedList<Pedido> Pedido = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedido);
			request.getRequestDispatcher("WEB-INF/pedidos.jsp").forward(request, response);
			break;}

		case "editar": {
			request.getRequestDispatcher("WEB-INF/EditPedido.jsp").forward(request, response);
			break;
		}
		
		case "verDetalle":{
			Pedido p = new Pedido();
			LogicLdp ctrlLdp = new LogicLdp();
			int idPedido =Integer.parseInt( request.getParameter("idcompra"));
			p.setIdPedido(idPedido);
			System.out.println("id  " + idPedido);
			System.out.println("ueueueu" + p);
			p = ctrl.getOne(p);
			System.out.println("jojojojo" + p);
			LinkedList<LineaDePedido> lineasDelPedido = ctrlLdp.getLineasDelPedido(p);
			request.setAttribute("lineasDelPedidoComprado", lineasDelPedido);
			request.setAttribute("pedidoComprado", p);
			request.getRequestDispatcher("WEB-INF/verDetallePedido.jsp").forward(request, response);
			break;
		}

		case "listar": {
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
		}
			break;

		case "borrar": {
			String id = request.getParameter("idPedido");
			Pedido ped = new Pedido();
			ped.setIdPedido(Integer.parseInt(id));
			ctrlped.delete(ped);
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}

		case "actualizar": {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaE = null;
			Date fechaP = null;
			Date fechaC = null;
			Usuario usu = new Usuario();
			Login logicU = new Login();
			Pedido ped = new Pedido();
			Categoria cat = new Categoria();
			String id = request.getParameter("idPedido");
			String fechaPedido = request.getParameter("fechaPedido");
			String fechaEntrega = request.getParameter("fechaEntrega");
			String fechaCancelacion = request.getParameter("fechaCancelacion");
			String monto = request.getParameter("monto");
			String dni = request.getParameter("dni");
			String estado = request.getParameter("estado");
			ped.setIdPedido(Integer.parseInt(id));
			try {
				fechaE = formato.parse(fechaEntrega);
				fechaC = formato.parse(fechaCancelacion);
				fechaP = formato.parse(fechaPedido);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ped.setFechaPedido(fechaP);
			ped.setFechaEntrega(fechaE);
			ped.setFechaCancelacion(fechaC);
			ped.setMonto(Double.parseDouble(monto));
			ped.setEstado(estado);
			usu.setDni(dni);
			logicU.getByDni(usu);
			ped.setUsu(usu);
			ctrlped.update(ped);
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
