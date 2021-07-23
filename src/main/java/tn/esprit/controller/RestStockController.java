package tn.esprit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.OrderDetails;
import tn.esprit.entities.Product;
import tn.esprit.entities.Stock;
import tn.esprit.entities.StockPosition;
import tn.esprit.entities.User;
import tn.esprit.repository.UserRepository;
import tn.esprit.services.EmailServiceImpl;
import tn.esprit.services.IStockService;
import tn.esprit.services.IUserService;

@RestController
public class RestStockController {
	private static final Logger logger = Logger.getLogger(RestControlUser.class);
	
	@Autowired
	IStockService StockServices;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	EmailServiceImpl emailService;
	
	@PostMapping("/addStock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock Stock) {
		logger.info("---- ajouter Stock Méthode ---");
		StockServices.addStock(Stock);
		return Stock;
	}

	@PostMapping("/updateStock")
	@ResponseBody
	public Stock updateUser(@RequestBody Stock Stock) {
		logger.info("---- update Stock Méthode ---");
		StockServices.updateStock(Stock);
		return Stock;
	}
	
	
	@PostMapping("/decrementStock")
	@ResponseBody
	public boolean DecrementStock(@RequestBody List<OrderDetails> orderDetails) {
		if(StockServices.decrementFromStock(orderDetails))
			return true;
		
		return false;
	}

	@DeleteMapping(value = "/deleteStock/{idStock}")
	public Stock deleteStock(@PathVariable("idStock") int idStock) {
		logger.info("---- delete Stock Méthode ---");
		return StockServices.deleteStock(idStock);
	}

	// http://localhost:8082/SpringMVC/servlet/findUser/1

	@GetMapping(value = "/findStock/{idStock}")
	public Stock findStock(@PathVariable("idStock") int idStock) {
		logger.info("---- find Stock Méthode ---");
		
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		return StockServices.findStock(idStock);
	}
	@GetMapping(value = "/findStockByProduct/{idProduct}")
	public Stock findStockByProduct(@PathVariable("idProduct") int idProduct) {
		
		return StockServices.getStockByProduct(idProduct);
	}
	
	

	@GetMapping(value = "/findAllStock")
	public List<Stock> findAllStock() {
		return StockServices.findAllStock();
	}
	
	@GetMapping(value = "/getAvailableProductsToStock")
	public List<Product> getAvailableProductsToStock() {
		return StockServices.getAvailableProductsToStock();
	}
	
	@GetMapping(value = "/productsOrderByQte")
	public List productsOrderByQte() {
		return StockServices.productsOrderByQte();
	}
	
	@GetMapping(value = "/getProductByRow/{row}")
	public List getProductByRow(@PathVariable("row") String row) {
		 
		return StockServices.getStockByRow(StockPosition.valueOf(row));
	}
	
	
	//@Scheduled(fixedDelay = 10000000)
	@Scheduled(cron = "0 0 23 * * *")
	public void sendOutOfStockAlert() {
		System.out.println("I am here");
		String text = "Les produit qui ont atteint le seuille de stock = 10 sont : \n";
		List<Stock> availableList = StockServices.checkAlertStock();
		
		
		User admin = UserRepository.getUserByUsername("admin");
		System.out.println("la taille de liste est : ++++++++++++++++++++++++++++++"+ availableList.size());
		if(availableList.size()>0) {
		for(Stock st : availableList) {
			Product produit = st.getProduct();
		
			//String qte = qte + String.valueOf(st.getQte()) ;
			text = text + "\n Titre produit : "+ produit.getTitle()+" Quantité restante :"+st.getQte()+" à la position :"+st.getStockPosition()+"\n";
					;
		}
		emailService.sendSimpleMessage(admin.getEmail(), "Alerte Stock", text);
		
		}
	}

}
