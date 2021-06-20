package tn.esprit.services;

import tn.esprit.entities.Order;

public interface IOrderService {
	
	public int addOrder(Order order);

	public Order deleteOrder(int idOrder);

	public int updateOrder(Order order);

	public Order findOrder(int idOrder);

}