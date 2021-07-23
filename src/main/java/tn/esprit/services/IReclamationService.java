package tn.esprit.services;

import java.util.List;


import tn.esprit.entities.Reclamation;

public interface IReclamationService {
	
	public int addReclamation(Reclamation Reclamation);

	public Reclamation deleteReclamation(int idReclamation);

	public int updateProduct(Reclamation reclamation);

	public Reclamation findReclamation(int idReclamation);
	
	public List<Reclamation> findAllReclamation();
	
	public int getNumberReclamation() ;
	
	public List<Object> getStatusReclamtionByDate();
	
	

}
