package Logic;

import java.util.LinkedList;
import Data.DataPedido;
import Entities.Pedido;

public class LogicPedido {
private DataPedido dp;
	
	public LinkedList<Pedido> getAll(){
		dp = new DataPedido();
		return dp.getAll();
	}
	
	public void add(Pedido ped) {
		dp=new DataPedido();
		dp.add(ped);
	}
	
	public void delete(Pedido ped) {
		dp=new DataPedido();
		dp.delete(ped);
	}

	public void eliminarPedido(Pedido pedido) {
		System.out.println("entro al llego a ca" +pedido.getEstado());

		if(pedido.getEstado().equals("En proceso") ) {
			System.out.println("entro al eliminar");
			dp=new DataPedido();
			dp.delete(pedido);
		}
	}
	
	public Pedido getOne(Pedido pedido) {
		dp=new DataPedido();
		return dp.getOne(pedido);
	}
	

	public void update(Pedido p) {
		dp=new DataPedido();
		dp.update(p);
	}
}
