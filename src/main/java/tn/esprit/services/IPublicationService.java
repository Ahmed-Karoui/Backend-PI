package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Order;
import tn.esprit.entities.Publication;


public interface IPublicationService {

	public int addPublication(Publication publication);

	public Publication deletePublication(int idPublication);

	public int updatePublication(Publication publication);

	public Publication findPublication(int idPublication);
	
	public List<Publication> listspublications();
	
	public int validatePublication (Publication publication);
	
	public int likePublication (Publication publication);

	public int dislikePublication (Publication publication);
	
	public List<Publication> findPublicationByUser(int idUser);
	
	public int getNombrePublicationJPQL();
	
	public List<Publication> getValidatedPublications();
	
	public List<Publication> getNotValidatedPublications();
	
	public List getnbValidated();
	
	public List statPublications();
	
}
