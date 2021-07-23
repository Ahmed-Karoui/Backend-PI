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

import tn.esprit.entities.Order;
import tn.esprit.entities.Publication;
import tn.esprit.entities.User;
import tn.esprit.services.IPublicationService;
import tn.esprit.services.IUserService;

@RestController
public class RestControllerPublication {
	
	private static final Logger logger = Logger.getLogger(RestControlUser.class);
	
	@Autowired
	IPublicationService iPublicationService;
	@Autowired
	IUserService IUserService;
	
	
	@PostMapping("/addPublication")
	@ResponseBody
	public Publication addPublication(@RequestBody Publication publication) {
		User u = IUserService.findUser(publication.getIdUser());
		publication.setUser(u);
		logger.info("---- add publication Method ---");
		iPublicationService.addPublication(publication);
		return publication;
	}
	
	
	@DeleteMapping(value = "/deletePublication/{idPublication}")
	public Publication deleteUser(@PathVariable("idPublication") int idPublication) {
		logger.info("---- delete publication Méthode ---");
		return iPublicationService.deletePublication(idPublication);
	}
	
	@PostMapping("/updatePublication")
	@ResponseBody
	public Publication updatePublication(@RequestBody Publication publication) {
		logger.info("---- update publication Method ---");
		iPublicationService.updatePublication(publication);
		return publication;
	}
	
	// http://localhost:8082/SpringMVC/servlet/findUser/1

	@GetMapping(value = "/findPublication/{idPublication}")
	public Publication findPublication(@PathVariable("idPublication") int idPublication) {
		logger.info("---- find publication Method ---");
		return iPublicationService.findPublication(idPublication);
	}
	
	@GetMapping(value = "/findAllpublications")
	@ResponseBody
	public List<Publication> findAllpublications() {
		
		return iPublicationService.listspublications();
	}
	
	@PostMapping("/validatePublication")
	@ResponseBody
	public Publication validatePublication(@RequestBody Publication publication) {
		logger.info("---- validate publication Method ---");
		iPublicationService.validatePublication(publication);
		return publication;
	}
	
	@PostMapping("/likePublication")
	@ResponseBody
	public Publication likePublication(@RequestBody Publication publication) {
		logger.info("---- Like publication Method ---");
		iPublicationService.likePublication(publication);
		return publication;
	}
	
	@PostMapping("/DislikePublication")
	@ResponseBody
	public Publication DislikePublication(@RequestBody Publication publication) {
		logger.info("---- Disklike publication Method ---");
		iPublicationService.dislikePublication(publication);
		return publication;
	}
	
	@GetMapping("/findPublicationByUser/{idUser}")
	public List <Publication> findPublicationByUser(@PathVariable("idUser") int idUser) {
		return iPublicationService.findPublicationByUser(idUser);
	}
	
    // URL : http://localhost:8081/SpringMVC/servlet/getNombreEmployeJPQL
    @GetMapping(value = "getNombrePublication")
    @ResponseBody
	public int getNombrePublication() {
	return iPublicationService.getNombrePublicationJPQL();
	}
    
	@GetMapping("/findValidatedPublications")
	public List <Publication> findValidatedPublications() {
		return iPublicationService.getValidatedPublications();
	}
	
	@GetMapping("/findNotValidatedPublications")
	public List <Publication> findNotValidatedPublications() {
		return iPublicationService.getNotValidatedPublications();
	}
	
    @GetMapping(value = "getnbValidated")
    @ResponseBody
	public List getnbValidated() {
	return iPublicationService.getnbValidated();
	}
    
	@GetMapping(value = "/statpublications")
	public List statpublications() {
		return iPublicationService.statPublications();
	}
	
}