<%@page import="Entities.*" %>
<%@page import ="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>UTNWEB</title>
</head>
<body>
	<%
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String idPedido=request.getParameter("idPedido");
		String fechaEntrega=request.getParameter("fechaEntrega");
		String fechaCancelacion=request.getParameter("fechaCancelacion");
		String monto=request.getParameter("monto");
		String estado=request.getParameter("estado");
		String dni =request.getParameter("dni");
		String fechaPedido = request.getParameter("fechaPedido");
		// String img=request.getParameter("img");
	%>
	
		<section >
	<nav  style="width: 100%; 
  z-index: 1000;" class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="abmc?accion=inicio">INICIO</a></li>
					</li>
					<li class="nav-item"><a  class="nav-link"
						href="abmc?accion=inicio">SEGUIR COMPRANDO <svg
								xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
  <path
									d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
</svg>
					</a></li>
					<%
					if (usu.getRol().getDescripcion().equals("administrador")) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> ADMINISTRAR </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="abmc?accion=listar">Usuarios</a></li>
							<li><a class="dropdown-item"
								href="abmcProductos?accion=listar">Productos</a></li>
							<li><a class="dropdown-item"
								href="abmcCategorias?accion=listar">Categorias</a></li>
							<li><a class="dropdown-item" href="abmcPedido?accion=listar">Pedidos</a></li>
						</ul> <%
 }
 %>
					<li style="margin-left:190mm" class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-expanded="false">CERRAR SESION</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"><img src="img/usu.png"
									height="80" width="80"></a></li>
							<li><a class="dropdown-item"><%=usu.getNombre()%> <%=usu.getApellido() %></a></li>
							<li><a class="dropdown-item"><%=usu.getEmail() %></a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="Signin?salida=salir">Salir</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	</section>
	<br>
	
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">EDITAR PEDIDO</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	 
	  <form class="row g-3" action="abmcProductos" method="get">
  <div class="col-12">
    <label for="inputAddress" class="form-label">ID Pedido</label>
    <input type="text" style=" box-shadow: 1px 1px 1px 1px #BBB0B0" class="form-control" id="idPedido" name="idPedido" readonly value="<%=idPedido%>" placeholder="Ingrese IdProducto" required>
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Estado</label>
    <input type="text" class="form-control" id="estado" name="estado" value="<%=estado%>" placeholder="Ingrese idCategoria" required>
  </div>
  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Fecha Pedido</label>
    <input type="text" class="form-control" id="fechaPedido" name="fechaPedido" value="<%=fechaPedido%>" placeholder="Ingrese descripcion" required>
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Fecha Entregado</label>
    <input type="text" class="form-control" id="fechaEntrega" name="fechaEntrega" value="<%=fechaEntrega%>" placeholder="Ingrese precio" required>
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Fecha Cancelacion</label>
    <input type="text" class="form-control" id="fechaCancelacion" name="fechaCancelacion" value="<%=fechaCancelacion%>" placeholder="Ingrese stock" required>
  </div>
  
   <div class="col-12">
    <label for="inputAddress2" class="form-label">Monto</label>
    <input type="text" class="form-control" id="monto" name="monto" value="<%=monto%>" placeholder="Ingrese stock" required>
  </div>
  
    <div class="col-12">
    <label for="inputAddress2" class="form-label">DNI del usuario</label>
    <input type="text" class="form-control" id="dni" name="dni" value="<%=dni%>" placeholder="Ingrese stock" required>
  </div>
  
  <div class="col-12">
    <input style="margin-right:200mm"type="submit" class="btn btn-primary" name="accion" value="actualizar"> 
  		<a href="javascript:history.back()"><button type="button" class="btn btn-danger">Cancelar</button></a>
	</div>
</form>
</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>