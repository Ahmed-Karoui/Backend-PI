package tn.esprit.repository;

import java.util.List;

import tn.esprit.entities.DeliveryMan;
import tn.esprit.entities.Order;

public interface DeliveryRepositoryCriteria {
	
	 DeliveryMan getDeliveryManByFirstName(String firstName);
	 
	 List<Order> getListOrdersByDeliveryManId(int id);


}
