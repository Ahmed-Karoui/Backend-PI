package tn.esprit.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Product;
import tn.esprit.services.IProductService;

@RestController
public class RestProductController {
	
	private static final Logger logger = Logger.getLogger(RestControlUser.class);
	
	@Autowired
	IProductService productServices;
	

	@PostMapping("/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		logger.info("---- ajouter Product Méthode ---");
		productServices.addProduct(product);
		return product;
	}

	@PostMapping("/updateProduct")
	@ResponseBody
	public Product updateUser(@RequestBody Product product) {
		logger.info("---- update Product Méthode ---");
		productServices.updateProduct(product);
		return product;
	}

	@DeleteMapping(value = "/deleteProduct/{idProduct}")
	public Product deleteProduct(@PathVariable("idProduct") int idProduct) {
		logger.info("---- delete product Méthode ---");
		return productServices.deleteProduct(idProduct);
	}

	// http://localhost:8082/SpringMVC/servlet/findUser/1

	@GetMapping(value = "/findProduct/{idProduct}")
	public Product findProduct(@PathVariable("idProduct") int idProduct) {
		logger.info("---- find Product Méthode ---");
		
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		return productServices.findProduct(idProduct);
	}

	@GetMapping(value = "/findAllProduct")
	@ResponseBody
	public List<Product> findAllProduct() {
		return productServices.findAllProduct();
	}
	
	@GetMapping(value = "/nombreProductByCategory")
	@ResponseBody
	public List nombreProductByCategory() {
		return productServices.getNombreProductByCategory();
	}
	
	@GetMapping(value = "/nombreProductByCategoryList")
	public List statNombreProductByCategory() {
		return productServices.statNombreProductByCategory();
	}
	
	@GetMapping(value = "/getProductByTitle/{title}")
	@ResponseBody
	public List<Product> getProductByTitle(@PathVariable("title") String title) {
		return productServices.getProductByTitle(title);
	}
	
	@GetMapping(value = "/getProductsByCategory/{id}")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable("id") int id) {
		return productServices.getProductsByCategory(id);
	}
	
	
	

}
