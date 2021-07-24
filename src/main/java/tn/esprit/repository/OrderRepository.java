package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.dto.CountGroupByOrderStatus;
import tn.esprit.dto.CountOrderByUser;
import tn.esprit.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>  {
	

	@Query("FROM Order o where o.user.id = :id")
	public List<Order> findOrderByUserId(@Param("id") Integer id);
	
	@Query("SELECT new tn.esprit.dto.CountOrderByUser(o.user.firstName,count(*)) "
			+ "FROM User u inner join Order o on u.id = o.user.id group by o.user.firstName")
	public List<CountOrderByUser> findOrderNumberForUser();
	
	@Query("SELECT new tn.esprit.dto.CountGroupByOrderStatus(o.status,count(*)) "
			+ "FROM Order o group by o.status")
	public List<CountGroupByOrderStatus> findStatusGroupByNumber();

//	@Query("SELECT o FROM Orders o WHERE o.deliveryMan.id = :id")
//	List<Order> orderById(@Param("id") Integer id);


}
