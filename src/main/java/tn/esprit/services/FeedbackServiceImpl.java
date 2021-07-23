package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Feedback;
import tn.esprit.repository.FeedBackRepository;

@Service
public class FeedbackServiceImpl implements IFeedBackService {
	
	@Autowired
	private FeedBackRepository feedBackRepository;

	@Override
	public int addFeedback(Feedback feedback) {
		this.feedBackRepository.save(feedback);
		return feedback.getId();
	}

	@Override
	public Feedback deleteFeedback(int idfeedBack) {
		Feedback feedback = this.feedBackRepository.findById(idfeedBack).orElse(null);
		this.feedBackRepository.delete(feedback);
		return feedback;
	}

	@Override
	public int updateFeedback(Feedback feedback) {
		this.feedBackRepository.save(feedback);
		return feedback.getId();
	}

	@Override
	public Feedback findFeedback(int idFeedback) {
		return this.feedBackRepository.findById(idFeedback).orElse(null) ;
	}

	@Override
	public List<Feedback> findAllFeedBack() {
		return (ArrayList) this.feedBackRepository.findAll();
	}

	@Override
	public Feedback findListFeedBackByIdProduct(int idProduct) {
		return this.feedBackRepository.findListFeedBackByIdProduct(idProduct);
	}



}
