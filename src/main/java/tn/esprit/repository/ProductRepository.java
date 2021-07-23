package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>  {

	@Query("select c.code,count(p) from Product p join p.category c group by c.code")
	public List numberProductsByCategory();
	
	@Query("select p from Product p join p.category c where c.id = :id")
	public List<Product> getProductsByCategory(@Param("id") int id);
	
	@Query("select p from Product p where p.title LIKE %:title% ")
	public List<Product> getProductByTitle(@Param("title") String title);
}
