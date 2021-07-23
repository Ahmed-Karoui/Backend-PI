package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.Whishlist;

public interface WishlistRepository extends CrudRepository<Whishlist, Integer>{

	@Query(value = "SELECT COUNT(*) FROM Whishlist")
	int getNumberWishlist();
	


}
