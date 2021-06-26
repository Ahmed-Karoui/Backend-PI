package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Reclamation;
import tn.esprit.repository.ReclamationRepository;


@Service
public class ReclamationServiceImp implements IReclamationService {

	@Autowired
	ReclamationRepository reclamationRepository;
	
	@Override
	public int addReclamation(Reclamation Reclamation) {
		// TODO Auto-generated method stub
		reclamationRepository.save(Reclamation);
		return Reclamation.getId();
	}

	@Override
	public Reclamation deleteReclamation(int idReclamation) {
		// TODO Auto-generated method stub
		Reclamation reclamation = reclamationRepository.findById(idReclamation).orElse(null);
		reclamationRepository.delete(reclamation);
		return reclamation;
	}

	@Override
	public int updateProduct(Reclamation reclamation) {
		// TODO Auto-generated method stub
		Reclamation oldReclamation = reclamationRepository.findById(reclamation.getId()).orElse(null);
		oldReclamation.setStatus(reclamation.getStatus());
		oldReclamation.setMessage(reclamation.getMessage());
		oldReclamation.setSubject(reclamation.getSubject());
		oldReclamation.setUser(reclamation.getUser());
		reclamationRepository.save(oldReclamation);
		return 0;
	}

	@Override
	public Reclamation findReclamation(int idReclamation) {
		// TODO Auto-generated method stub
		return reclamationRepository.findById(idReclamation).orElse(null);
	}

	@Override
	public List<Reclamation> findAllReclamation() {
		// TODO Auto-generated method stub
		return (List<Reclamation>)reclamationRepository.findAll();
	}

	@Override
	public int getNumberReclamation() {
		// TODO Auto-generated method stub
		return reclamationRepository.getNumberReclamtion();
	}

	@Override
	public List<Object> getStatusReclamtionByDate() {
		// TODO Auto-generated method stub
		return (List<Object>)reclamationRepository.getStatusReclamtionByDate();
	}

	public List<Object> findByStatusStartsWith(String status) {
		// TODO Auto-generated method stub
		return (List<Object>)reclamationRepository.findByStatusStartsWith(status);
	}
	
	public List<Object> searchByMessageLike(String message) {
		// TODO Auto-generated method stub
		return (List<Object>)reclamationRepository.searchByMessageLike(message);
	}
	



}
