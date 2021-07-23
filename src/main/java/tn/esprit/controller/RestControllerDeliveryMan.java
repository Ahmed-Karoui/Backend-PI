package tn.esprit.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.AffectationDto;
import tn.esprit.entities.DeliveryMan;
import tn.esprit.entities.DeliveryManStat;
import tn.esprit.entities.Order;
import tn.esprit.entities.PropositionDto;
import tn.esprit.entities.User;
import tn.esprit.services.IDeliveryManService;

@RequestMapping("dm")
@RestController
public class RestControllerDeliveryMan {
	
	private static final Logger logger = Logger.getLogger(RestControlUser.class);


	@Autowired
	IDeliveryManService iDeliveryManService;
	
	
	
	@PostMapping("/adddvm")
	@ResponseBody
	public DeliveryMan addDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
		logger.info("---- ajouter deliveryMan Méthode ---");
		iDeliveryManService.addDeliveryMan(deliveryMan);
		return deliveryMan;
	}
	
	@PostMapping("/updatedvm")
	@ResponseBody
	public DeliveryMan updateDeliveryMan(@RequestBody DeliveryMan dvm) {
		logger.info("---- update DeliveryMan Méthode ---");
	   iDeliveryManService.updateDeliveryMan(dvm);
		return dvm;
	}
	
	@DeleteMapping(value = "/deletedvm/{id}")
	public int deletedvm(@PathVariable int id) {
		logger.info("---- delete User Méthode ---");
		return iDeliveryManService.deleteDeliveryMan(id);
	}
	
	@GetMapping(value = "/")
	public List<DeliveryMan> listDeliveryMan() {
		logger.info("----getList DeliveryMan Méthode ---");
		return iDeliveryManService.listDeliveryMan();
	}
	
	@GetMapping(value = "/getDeliveryManByName___Criteria/{firstName}")
	public DeliveryMan getDeliveryManByName__Criteria(@PathVariable String firstName) {	
		return iDeliveryManService.getDeliveryManByFirstName(firstName);
	}
	
	@GetMapping(value = "/findDeliveryManById/{id}")
	public DeliveryMan findDeliveryManById(@PathVariable int id) {	
		return iDeliveryManService.findDeliveryMan(id);
	}
	
	
	@GetMapping(value = "/getListOrdersByDeliveryManId___Criteria/{id_deliveryMan}")
	public List<Order> getListOrdersByDeliveryManId___Criteria(@PathVariable int id_deliveryMan) {	
		return iDeliveryManService.getListOrdersByDeliveryManId(id_deliveryMan);
	}
	
	@PostMapping(value = "/affectOrdersToDeliveryMan/")
	@ResponseBody
	public String affectOrdersToDeliveryMan( @RequestBody AffectationDto aff) {	
		return iDeliveryManService.affectOrdersToDeliveryMan(aff);
	}
	
	@GetMapping(value = "/setOrderDelivered/{idDeliveryMan}/{idOrder}")
	public Order setOrderDelivered(@PathVariable int idDeliveryMan,@PathVariable int idOrder) {	
		return iDeliveryManService.setOrderDelivered_Criteria(idDeliveryMan, idOrder);
	}
	
	
	@GetMapping(value = "/getAllDeliveryManStat")
	public List<DeliveryManStat> getAllDeliveryManStatus() {	
		return iDeliveryManService.getAllDeliveryManStatus();
	}
	
	@GetMapping(value = "/getDeliveryManStatById/{id}")
	public DeliveryManStat getDeliveryManStatById(@PathVariable int id) {	
		return iDeliveryManService.getDeliveryManStatById(id);
	}
	
	@GetMapping(value = "/optimisationAlgo/{codePostalOrder}")
	public List<DeliveryMan> optimisationAlgo(@PathVariable int codePostalOrder) {	
		return iDeliveryManService.optimisationAlgo(codePostalOrder);
	}
	
	@GetMapping(value = "/affectOrdersToDeliveryMan/{idDeliveryMan}/{idCommande}")
	public String affectOrdersToDeliveryMan( @PathVariable int idDeliveryMan,@PathVariable int idCommande) {	
		return   iDeliveryManService.affectOrdersToDeliveryManFront(idDeliveryMan,idCommande);
	}
	
	
	@GetMapping(value = "/supprimerAffectation/{idOrder}")
	public Order supprimerAffectation(@PathVariable int idOrder) {	
		return iDeliveryManService.supprimerAffectation(idOrder);
	}
	
	
	
	
	
	

	
	
	
	
	

}
