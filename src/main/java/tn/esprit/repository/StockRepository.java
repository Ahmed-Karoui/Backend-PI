package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.Product;
import tn.esprit.entities.Stock;
import tn.esprit.entities.StockPosition;

public interface StockRepository extends CrudRepository<Stock, Integer> {
	
	@Query("select c from Stock c join c.product p where p.id=:product")
	public Stock getStockByProduct(@Param("product") int productID);
	
	@Query("select p.title,c.qte from Stock c join c.product p order by c.qte DESC")
	public List getProductOrderByStock();
	
	@Query("select p from Stock c Right join c.product p where p not in (select st.product from Stock st)")
	public List<Product> getAvailableProductsToStock();
	
	@Query("select c from Stock c where c.stockPosition = :row")
	public List getStockByRow(@Param("row") StockPosition row);

}
