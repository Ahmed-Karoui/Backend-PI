package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Feedback;

public interface IFeedBackService {
	
	public int addFeedback(Feedback feedback);

	public Feedback deleteFeedback(int idOrder);

	public int updateFeedback(Feedback feedback);

	public Feedback findFeedback(int idFeedback);
	
	public List<Feedback> findAllFeedBack();
	
	public Feedback findListFeedBackByIdProduct(int idProduct);

}
