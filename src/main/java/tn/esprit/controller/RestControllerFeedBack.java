package tn.esprit.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Feedback;
import tn.esprit.entities.Order;
import tn.esprit.entities.Product;
import tn.esprit.entities.User;
import tn.esprit.services.FeedbackServiceImpl;
import tn.esprit.services.IProductService;
import tn.esprit.services.OrderServiceImpl;

@RestController
public class RestControllerFeedBack {
	
	private static final Logger logger = Logger.getLogger(RestControllerFeedBack.class);

	@Autowired
	private FeedbackServiceImpl feedbackService;
	
	@Autowired
	private IProductService productServices;
	
	@PostMapping("/addFeedback")
	@ResponseBody
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		Product product = this.productServices.findProduct(feedback.getIdProduct());
		feedback.setProduct(product);
		List<Feedback> feedbacks = this.feedbackService.findAllFeedBack();
		for(Feedback feedback2 : feedbacks) {
			if(feedback2.getProduct().getId() == feedback.getIdProduct()) {
				float rating = (feedback2.getRate() + feedback.getRate()) / 2;
				feedback = feedback2 ;
				feedback.setRate(rating);
			}
		}
		this.feedbackService.addFeedback(feedback);
		return feedback ;
	}
	
	@PutMapping("/updateFeedback")
	@ResponseBody
	public Feedback updateFeedback(@RequestBody Feedback feedback) {
		this.feedbackService.updateFeedback(feedback);
		return feedback;
	}
	
	@DeleteMapping(value = "/deleteFeedback/{idFeedback}")
	public String deletFeedback(@PathVariable("idFeedback") int idFeedback) {
		this.feedbackService.deleteFeedback(idFeedback);
		return "Order N° " + idFeedback + " deleted" ;
	}
	
	@GetMapping("/ListFeedback")
	public List<Feedback> findAllOrder() {
		return this.feedbackService.findAllFeedBack();
	}
	
	@GetMapping("/findListFeedBackByIdProduct/{productId}")
	public Feedback findListFeedBackByIdProduct(@PathVariable("productId") int productId) {
		Feedback feedback = this.feedbackService.findListFeedBackByIdProduct(productId);
		feedback.setIdProduct(feedback.getProduct().getId());
		return feedback ;
	}


}
