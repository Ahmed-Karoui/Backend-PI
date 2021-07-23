package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Product;

public interface IProductService {

	
	public int addProduct(Product product);

	public Product deleteProduct(int idProduct);

	public int updateProduct(Product product);

	public Product findProduct(int idProduct);
	
	public List<Product> findAllProduct();
	
	public List getNombreProductByCategory(); 
	
	public List<Product> getProductByTitle(String title);
	
	public List<Product> getProductsByCategory(int id );
	
	

}
