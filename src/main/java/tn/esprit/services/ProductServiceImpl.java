package tn.esprit.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Product;
import tn.esprit.repository.ProductRepository;
@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public int addProduct(Product product) {
		product.setPublicationDate(new Date());
		productRepository.save(product);
		return product.getId();
	}

	@Override
	public Product deleteProduct(int idProduct) {
		Product product = productRepository.findById(idProduct).orElse(null);
		productRepository.delete(product);
		return product;
	}

	@Override
	public int updateProduct(Product product) {
		Product oldProduct = productRepository.findById(product.getId()).orElse(null);
		oldProduct.setTitle(product.getTitle());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setPublicationDate(product.getPublicationDate());
		productRepository.save(oldProduct);
		
		return product.getId();
	}

	@Override
	public Product findProduct(int idProduct) {
		
		return productRepository.findById(idProduct).orElse(null);
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll()  ;
	}

	@Override
	public List getNombreProductByCategory() {
		
		return productRepository.numberProductsByCategory();
	}

	@Override
	public List<Product> getProductByTitle(String title) {
		return productRepository.getProductByTitle(title);
	}

	@Override
	public List<Product> getProductsByCategory(int id) {
		
		return productRepository.getProductsByCategory(id);
		
	}

}
