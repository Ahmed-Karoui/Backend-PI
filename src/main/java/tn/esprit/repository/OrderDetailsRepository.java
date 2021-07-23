package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.OrderDetails;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {
	
	@Query("FROM OrderDetails o where o.order.id = :id")
	public List<OrderDetails> findOrderDetailsByOrderId(@Param("id") Integer id);

}
