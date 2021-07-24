package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.dto.CountGroupByOrderStatus;
import tn.esprit.dto.CountOrderByUser;
import tn.esprit.entities.Order;
import tn.esprit.entities.OrderDetails;
import tn.esprit.repository.OrderDetailsRepository;
import tn.esprit.repository.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderRepository orderRepository ;
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private OrderDetailsRepository orderDetailsService ;

	@Override
	public int addOrder(Order order) {
		this.orderRepository.save(order);
		return order.getId();
	}

	@Override
	public Order deleteOrder(int idOrder) {
		Order order = this.orderRepository.findById(idOrder).orElse(null);
		List<OrderDetails> listOrderFetals = this.orderDetailsService.findOrderDetailsByOrderId(idOrder);
		this.orderDetailsService.deleteAll(listOrderFetals);
		this.orderRepository.delete(order);
		return order;
	}

	@Override
	public int updateOrder(Order order) {
		this.orderRepository.save(order);
		return order.getId();
	}

	@Override
	public Order findOrder(int idOrder) {
		return this.orderRepository.findById(idOrder).orElse(null) ;
	}

	@Override
	public List<Order> findAllOrder() {
		return (ArrayList<Order>) this.orderRepository.findAll();
	}

	@Override
	public List<Order> findOrderByUserCriteria(Integer idUser) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		 CriteriaQuery<Order> criteriaQuery = cb.createQuery(Order.class); 
//		 Root<Order> root = criteriaQuery.from(Order.class);
//		 criteriaQuery.select(root);
//		 DeliveryMan dv = findDeliveryMan(id);
////		 ParameterExpression<String> p = cb.parameter(String.class);
//		 criteriaQuery.select(root).where(cb.equal(root.get("deliveryMan"), dv));
//		 
//		 TypedQuery<Order> query = em.createQuery(criteriaQuery);
////		 query.setParameter(p, prenom);
//		 try {
//			 if(
		return this.orderRepository.findOrderByUserId(idUser);
	}

	@Override
	public List<CountOrderByUser> findNumberOrderForUser() {
		return this.orderRepository.findOrderNumberForUser();
	}
	
	public List<CountGroupByOrderStatus> findStatusGroupByNumber(){
		return this.orderRepository.findStatusGroupByNumber();
	}

}
