package tn.esprit.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.entities.DeliveryMan;
import tn.esprit.entities.Order;

public interface DeliveryManRepository extends CrudRepository<DeliveryMan, Integer> {
	

	
}
