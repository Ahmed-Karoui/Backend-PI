package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Comment;
import tn.esprit.entities.DataPoint;
import tn.esprit.entities.Publication;
import tn.esprit.entities.User;
import tn.esprit.repository.PublicationRepository;
import tn.esprit.repository.UserRepository;


@Service
public class IPublicationServiceImpl implements IPublicationService {
	
	
	@Autowired
	PublicationRepository publicationRepository;




	@Override
	public int addPublication(Publication publication) {
		publicationRepository.save(publication);
		return publication.getId();
	}

	@Override
	public Publication deletePublication(int idPublication) {
		Publication publication = publicationRepository.findById(idPublication).orElse(null);
		publicationRepository.delete(publication);
		return publication;
	}

	@Override
	public Publication findPublication(int idPublication) {
	Publication publication = publicationRepository.findById(idPublication).orElse(null);
	return publication;
	}

	@Override
	public List<Publication> listspublications() {
		 return (List<Publication>) publicationRepository.findAll();
	}

	@Override
	public int validatePublication(Publication publication) {
		Publication newpublication = publicationRepository.findById(publication.getId()).orElse(null);
		if (newpublication!=null) {
			newpublication.setValidated(true);
			publicationRepository.save(newpublication);
			return 1;
		}	
		return 0;
	}

	@Override
	public int likePublication(Publication publication) {
		Publication newpublication = publicationRepository.findById(publication.getId()).orElse(null);
		if (newpublication!=null) {
			int nblikes = newpublication.getNbLike();
			newpublication.setNbLike(nblikes+1);
			publicationRepository.save(newpublication);
			return 1;
		}	
		return 0;
	}

	@Override
	public int dislikePublication(Publication publication) {
		Publication newpublication = publicationRepository.findById(publication.getId()).orElse(null);
		if (newpublication!=null) {
			int nbDislikes = newpublication.getNbDisLike();
			newpublication.setNbDisLike(nbDislikes+1);
			publicationRepository.save(newpublication);
			return 1;
		}	
		return 0;
	}

	@Override
	public List<Publication> findPublicationByUser(int idUser) {
		return this.publicationRepository.findPublicationByUserId(idUser);
	}
	
	public int getNombrePublicationJPQL() {
		return publicationRepository.countpublications();
	}

	@Override
	public List<Publication> getValidatedPublications() {
		return publicationRepository.getValidatedPublications();
	}

	@Override
	public List<Publication> getNotValidatedPublications() {
		return publicationRepository.getNotValidatedPublications();
	}
	
	
	public List getnbValidated() {
		return publicationRepository.getnbValidated();
	}
	@Override
	public List<DataPoint> statPublications() {
		List<DataPoint> list = new ArrayList<DataPoint>() ;
		for (Object object : publicationRepository.statPublications()) {
			DataPoint dataPoint = new DataPoint();     
			dataPoint.setLabel((((Object[]) object)[0]).toString());
			dataPoint.setY(Float.valueOf((((Object[]) object)[1]).toString()));
			list.add(dataPoint);
		}

		return list;
	}
	
	@Override
	public int updatePublication(Publication publication) {
		Publication oldpublication = publicationRepository.findById(publication.getId()).orElse(null);
		oldpublication.setContent(publication.getContent());
		oldpublication.setNbDisLike(publication.getNbDisLike());
		oldpublication.setNbLike(publication.getNbLike());
		oldpublication.setPublicationDate(publication.getPublicationDate());
		oldpublication.setUser(publication.getUser());
		publicationRepository.save(oldpublication);
		return 0;
	}
	
}
