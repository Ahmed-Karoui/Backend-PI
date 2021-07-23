package tn.esprit.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.Reclamation;

public interface ReclamationRepository extends CrudRepository<Reclamation, Integer>{
	
	
	
	@Query(value = "SELECT COUNT(*) FROM Reclamation")
	int getNumberReclamtion();
	
	@Query(value = "SELECT status,publicationDate FROM Reclamation")
	List<Object> getStatusReclamtionByDate();
	
	
	List<Reclamation> findByStatusStartsWith(String status);
	
	@Query("SELECT p FROM Reclamation p WHERE p.message LIKE %:message%")
	List<Reclamation> searchByMessageLike(@Param("message") String message);
	
	@Transactional
	@Modifying
	@Query("update Reclamation r set r.status=:status where r.id=:id")
	 void updateJustStatusById(@Param("id")int id, @Param("status")String status);

}
