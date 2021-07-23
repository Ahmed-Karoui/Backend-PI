package tn.esprit.services;

import java.util.List;

import tn.esprit.dto.CountOrderByUser;
import tn.esprit.entities.Order;

public interface IOrderService {
	
	public int addOrder(Order order);

	public Order deleteOrder(int idOrder);

	public int updateOrder(Order order);

	public Order findOrder(int idOrder);

	public List<Order> findAllOrder();

	public List<Order> findOrderByUserCriteria(Integer idUser);
	
	public List<CountOrderByUser> findNumberOrderForUser();

}
