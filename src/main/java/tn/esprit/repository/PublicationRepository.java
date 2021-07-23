package tn.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.entities.Publication;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface PublicationRepository extends CrudRepository<Publication, Integer>{

	
	@Query("SELECT count(*) FROM Publication")
    public int countpublications();
	
	@Query("select p from Publication p where p.validated=true")
	public List<Publication> getValidatedPublications();
	
	@Query("select p from Publication p where p.validated=false")
	public List<Publication> getNotValidatedPublications();
	
	@Query("select p.validated , count(p) from Publication p group by p.validated")
	public List getnbValidated();
	

	
	@Query("select p from Publication p inner join User u on u.id=p.user.id where u.id=:id")
	public List<Publication> findPublicationByUserId(@Param("id") int id);

// @Query("select c.blocked as blocked ,count(1) as nbr from User c group by
// c.blocked ")
@Query(" SELECT "
		+ "CASE p.validated "
		+ "WHEN true THEN 'validated publication' "
		+ "WHEN false THEN 'unvalidated publication' "
		+ "ELSE p.validated "
		+ "END " 
		+", count(1) as y "
		+ "FROM Publication p group by p.validated")
public List statPublications();

}
	