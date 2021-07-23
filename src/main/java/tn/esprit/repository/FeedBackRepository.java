package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.Feedback;


public interface FeedBackRepository extends CrudRepository<Feedback, Integer> {
	
	@Query("FROM Feedback o where o.product.id = :id")
	public Feedback findListFeedBackByIdProduct(@Param("id") Integer id);

}
