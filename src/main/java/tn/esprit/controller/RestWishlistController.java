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

import tn.esprit.entities.Whishlist;
import tn.esprit.services.WishListServiceImp;

@RestController
public class RestWishlistController {
	
	private static final Logger logger = Logger.getLogger(RestWishlistController.class);

	@Autowired
	WishListServiceImp wishListServices;
	

	@PostMapping("/addWhishlist")
	@ResponseBody
	public Whishlist addWishList(@RequestBody Whishlist wishlist) {
		logger.info("---- ajouter Reclamation Méthode ---");
		wishListServices.addWishList(wishlist);
		return wishlist;
	}

	@PostMapping("/updateWhishlist")
	@ResponseBody
	public Whishlist updateWishList(@RequestBody Whishlist wishlist) {
		logger.info("---- update Reclamation Méthode ---");
		wishListServices.updateWishList(wishlist);
		return wishlist;
	}

	@DeleteMapping(value = "/deleteWhishlist/{idWhishlist}")
	public Whishlist deleteWishList(@PathVariable("idWhishlist") int idWhishlist) {
		logger.info("---- delete Reclamation Méthode ---");
		return wishListServices.deleteWishList(idWhishlist);
	}

	// http://localhost:8082/SpringMVC/servlet/findWishList/1

	@GetMapping(value = "/findWhishlist/{idWhishlist}")
	public Whishlist findWishList(@PathVariable("idWhishlist") int idWhishlist) {
		logger.info("---- find Reclamation Méthode ---");
		return wishListServices.findWishList(idWhishlist);
	}

	@GetMapping(value = "/findAllWishList")
	@ResponseBody
	public List<Whishlist> findAllWishList() {
		return wishListServices.findAllWishList();
	}
	
	@GetMapping(value = "/getNumberWishList")
	@ResponseBody
	public int getNumberWishList() {
		return wishListServices.getNumberWishList();
	}
}
