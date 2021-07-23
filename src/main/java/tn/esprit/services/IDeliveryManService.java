package tn.esprit.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.entities.AffectationDto;
import tn.esprit.entities.DeliveryMan;
import tn.esprit.entities.DeliveryManStat;
import tn.esprit.entities.Order;
import tn.esprit.entities.PropositionDto;

public interface IDeliveryManService {
       
	//crud
	
	public int addDeliveryMan(DeliveryMan DeliveryMan);

	public int deleteDeliveryMan(int idDeliveryMan);

	public int updateDeliveryMan(DeliveryMan DeliveryMan);

	public DeliveryMan findDeliveryMan(int idDeliveryMan);
	
	public List<DeliveryMan> listDeliveryMan();
	
	//affectatiopn tache avancée
	
	public String affectOrdersToDeliveryMan(AffectationDto aff);
	public Order setOrderDelivered_Criteria(int idDeliveryMan,int idOrder); // il reste a ajouter la  notification par email  !
	
	public Order supprimerAffectation(int idOrder);
	
	public String affectOrdersToDeliveryManFront(int idD,int idC);
	
	
	
	//recherche criteria
	
	public DeliveryMan getDeliveryManByFirstName(String prenom);
	public List<Order> getListOrdersByDeliveryManId(int id);
	
	
	
	//statistique without JPQL QUERY  ==> using View
	public List<DeliveryManStat> getAllDeliveryManStatus();
	public DeliveryManStat getDeliveryManStatById(@PathVariable int id);
	
	
	
	//algo optimisation
	public List<DeliveryMan> optimisationAlgo(@PathVariable int codePostalOrder);

	
	//cron 
	
	


}
